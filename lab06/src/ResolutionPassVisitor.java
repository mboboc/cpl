import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ResolutionPassVisitor implements ASTVisitor<TypeSymbol> {
    @Override
    public TypeSymbol visit(Program prog) {
        for (var stmt: prog.stmts) {
            stmt.accept(this);
        }
        return null;
    }
    
    @Override
    public TypeSymbol visit(Id id) {
        // Verificăm dacă într-adevăr avem de-a face cu o variabilă
        // sau cu o funcție, al doilea caz constituind eroare.
        // Puteți folosi instanceof.
        var symbol = id.getScope().lookup(id.getToken().getText());

        if (symbol instanceof FunctionSymbol) {
            ASTVisitor.error(id.getToken(),
                  id.getToken().getText() + " is not a variable");
            return null;
        }

        // TODO 2: Întoarcem informația de tip salvată deja în simbol încă de la
        // definirea variabilei.

        IdSymbol type = (IdSymbol) symbol;
        return type.getType();
    }
    
    @Override
    public TypeSymbol visit(VarDef varDef) {
        if (varDef.initValue != null) {
            var varType  = varDef.id.getSymbol().getType();
            var initType = varDef.initValue.accept(this);
            
            // TODO 5: Verificăm dacă expresia de inițializare are tipul potrivit
            // cu cel declarat pentru variabilă.

            if (initType == null)
                return varType;

            var type = getAssignType(varType, initType, varDef.token);

            if (type == null) {
                ASTVisitor.error(varDef.token, "Type of initilization expression does not match variable type");
                return null;
            }

            return type;
        }
        
        return null;
    }
    
    @Override
    public TypeSymbol visit(FuncDef funcDef) {
        var returnType = funcDef.id.getSymbol().getType();
        var bodyType = funcDef.body.accept(this);
        
        // TODO 5: Verificăm dacă tipul de retur declarat este compatibil
        // cu cel al corpului.

        if (bodyType == null || returnType == null)
            return null;

        var type = getAssignType(returnType, bodyType, funcDef.token);

        if (type == null) {
            ASTVisitor.error(funcDef.token, "Return type does not match body type");
            return null;
        }

        return type;
    }

    @Override
    public TypeSymbol visit(Call call) {
        // Verificăm dacă funcția există în scope. Nu am putut face
        // asta în prima trecere din cauza a forward references.
        //
        // De asemenea, verificăm că Id-ul pe care se face apelul de funcție
        // este, într-adevăr, o funcție și nu o variabilă.
        //
        // Hint: pentru a obține scope-ul, putem folosi call.id.getScope(),
        // setat la trecerea anterioară.
        var id = call.id;
        var symbol = id.getScope().lookup(id.getToken().getText());

        if (symbol == null) {
            ASTVisitor.error(id.getToken(),
                  id.getToken().getText() + " function undefined");
            return null;
        }

        if (!(symbol instanceof FunctionSymbol)) {
            ASTVisitor.error(id.getToken(),
                  id.getToken().getText() + " is not a function");
            return null;
        }
        
        var functionSymbol = (FunctionSymbol)symbol;
        id.setSymbol(functionSymbol);
        
        // TODO 6: Verificați dacă numărul parametrilor actuali coincide
        // cu cel al parametrilor formali, și că tipurile sunt compatibile.
        
        var formals = functionSymbol.getFormals();

        if (formals.size() != call.args.size()) {
            ASTVisitor.error(id.getToken(),
                    id.getToken().getText() + " applied to wrong number of arguments");
            return null;
        }

        List<Symbol> formalTypes = new ArrayList<>(formals.values());
        int n = 0;
        for(var i : call.args) {
            var formalType = i.accept(this);
            var sym = (IdSymbol) formalTypes.get(n);
            var type = sym.getType();

            if (formalType != type) {
                ASTVisitor.error(id.getToken(), "Argument " +
                        (n + 1) + " of " + id.getToken().getText() + " has wrong type");
                return null;
            }
            n++;
        }
        return functionSymbol.type;
    }   
    
    @Override
    public TypeSymbol visit(Assign assign) {
        var idType   = assign.id.accept(this);
        var exprType = assign.expr.accept(this);
        
        // TODO 5: Verificăm dacă expresia cu care se realizează atribuirea
        // are tipul potrivit cu cel declarat pentru variabilă.

        if(exprType == null)
            return null;

        var type = getAssignType(idType, exprType, assign.token);

        if (type == null) {
            ASTVisitor.error(assign.token, "Assignment with incompatible types");
            return null;
        }

        return type;
    }

    @Override
    public TypeSymbol visit(If iff) {
        var condType = iff.cond.accept(this);
        var thenType = iff.thenBranch.accept(this);
        var elseType = iff.elseBranch.accept(this);
        
        // TODO 4: Verificați tipurile celor 3 componente, afișați eroare
        // dacă este cazul, și precizați tipul expresiei.

        if (!checkIfBool(condType))
            ASTVisitor.error(iff.getToken(), "Condition of if expression has type other than Bool");

        var type = getOpType(thenType, elseType, iff.token);
        if (type == null)
            ASTVisitor.error(iff.token, "Branches of if expression have incompatible types");
        return type;
    }

    @Override
    public TypeSymbol visit(Type type) {
        return null;
    }

    @Override
    public TypeSymbol visit(Formal formal) {
        return formal.id.getSymbol().getType();
    }

    // Operații aritmetice.
    @Override
    public TypeSymbol visit(UnaryMinus uMinus) {

        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        
        return uMinus.expr.accept(this);
    }

    @Override
    public TypeSymbol visit(Div div) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        var leftType = div.left.accept(this);
        var rightType = div.right.accept(this);

        if (checkIfBool(leftType) || checkIfBool(rightType)) {
            ASTVisitor.error(div.token, "Operands of " +
                    div.token.getText() + " have incompatible types");
        }

        var type = getOpType(leftType, rightType, div.token);
        if (type == null)
            ASTVisitor.error(div.token,
                    div.token.getText() + " incompatible types for arithmetic operation");
        return type;
    }

    @Override
    public TypeSymbol visit(Mult mult) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.

        var leftType = mult.left.accept(this);
        var rightType = mult.right.accept(this);

        if (checkIfBool(leftType) || checkIfBool(rightType)) {
            ASTVisitor.error(mult.token, "Operands of " +
                    mult.token.getText() + " have incompatible types");
            return null;
        }

        var type = getOpType(leftType, rightType, mult.token);
        if (type == null)
            ASTVisitor.error(mult.token,
                    mult.token.getText() + " incompatible types for arithmetic operation");
        return type;
    }

    @Override
    public TypeSymbol visit(Plus plus) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.

        var leftType = plus.left.accept(this);
        var rightType = plus.right.accept(this);

        if (checkIfBool(leftType) || checkIfBool(rightType)) {
            ASTVisitor.error(plus.token, "Operands of " +
                    plus.token.getText() + " have incompatible types");
            return null;
        }

        var type = getOpType(leftType, rightType, plus.token);
        if (type == null)
            ASTVisitor.error(plus.token,
                    plus.token.getText() + " incompatible types for arithmetic operation");
        return type;
    }

    public TypeSymbol getOpType(TypeSymbol left, TypeSymbol right, Token token) {
        if (left.name.equals("Bool") && right.name.equals("Bool"))
            return TypeSymbol.BOOL;
        else if (left.name.equals("Bool") || right.name.equals("Bool"))
            return null;
        else if (left.name.equals("Int") && right.name.equals("Int")) {
            return TypeSymbol.INT;
        } else if (left.name.equals("Int") || left.name.equals("Float") &&
                right.name.equals("Int") || right.name.equals("Float")) {
            return TypeSymbol.FLOAT;
        } else
            return null;
    }

    public TypeSymbol getAssignType(TypeSymbol left, TypeSymbol right, Token token) {
        if (left == null || right == null)
            return null;

        if (left == right)
            return left;

        return null;
    }

    public boolean checkIfBool(TypeSymbol val) {
        return val.name.equals("Bool");
    }

    @Override
    public TypeSymbol visit(Minus minus) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        var leftType =  minus.left.accept(this);
        var rightType = minus.right.accept(this);

        if (checkIfBool(leftType) || checkIfBool(rightType)) {
            ASTVisitor.error(minus.token, "Operands of " +
                    minus.token.getText() + " have incompatible types");
        }

        var type = getOpType(leftType, rightType, minus.token);
        if (type == null)
            ASTVisitor.error(minus.token,
                    minus.token.getText() + " incompatible types for arithmetic operation");
        return type;
    }

    @Override
    public TypeSymbol visit(Relational relational) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        // Puteți afla felul operației din relational.getToken().getType(),
        // pe care îl puteți compara cu CPLangLexer.EQUAL etc.
        var leftType = relational.left.accept(this);
        var rightType = relational.right.accept(this);

        if (checkIfBool(leftType) || checkIfBool(rightType)) {
            ASTVisitor.error(relational.token, "Operands of " +
                    relational.token.getText() + " have incompatible types");
        }
        return TypeSymbol.BOOL;
    }

    // Tipurile de bază
    @Override
    public TypeSymbol visit(Int intt) {
        // TODO 2: Întoarcem tipul corect.
        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(Bool bool) {
        // TODO 2: Întoarcem tipul corect.
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(FloatNum floatNum) {
        // TODO 2: Întoarcem tipul corect.
        return TypeSymbol.FLOAT;
    }
    
};