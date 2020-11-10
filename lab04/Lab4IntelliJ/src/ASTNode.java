import org.antlr.v4.runtime.Token;
import java.util.*;

// Rădăcina ierarhiei de clase reprezentând nodurile arborelui de sintaxă
// abstractă (AST). Singura metodă permite primirea unui visitor.
public abstract class ASTNode {
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

// Orice expresie.
abstract class Expression extends ASTNode {
    // Reținem un token descriptiv al expresiei, pentru a putea afișa ulterior
    // informații legate de linia și coloana eventualelor erori semantice.
    Token token;

    Expression(Token token) {
        this.token = token;
    }
}

// Identificatori
class Id extends Expression {
    Id(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Literali întregi
class Int extends Expression {
    Int(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Construcția if.
class If extends Expression {
    // Sunt necesare trei câmpuri pentru cele trei componente ale expresiei.
    Expression cond;
    Expression thenBranch;
    Expression elseBranch;

    If(Expression cond,
       Expression thenBranch,
       Expression elseBranch,
       Token start) {
        super(start);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Prog
class ASTProg extends Expression {
    List<Expression> defs;
    List<Expression> exprs;

    ASTProg(List<Expression> defs,
            List<Expression> exprs,
            Token start) {
        super(start);
        this.defs = defs;
        this.exprs = exprs;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Type
class ASTType extends Expression {
    String value;

    ASTType(String value,
            Token start) {
        super(start);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// VarDef
class ASTVarDef extends Expression {
    String value;

    ASTVarDef(String value,
              Token start) {
        super(start);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Formal
class ASTFormal extends Expression {
    String value;

    ASTFormal(String value,
              Token start) {
        super(start);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// FuncCall
class ASTFuncCall extends Expression {
    List<Expression> args;

    ASTFuncCall(List<Expression> args,
              Token start) {
        super(start);
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// FuncCall
class ASTFuncDef extends Expression {
    List<Expression> args;

    ASTFuncDef(List<Expression> args,
               Token start) {
        super(start);
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Literali de tip Float
class ASTFloat extends Expression {
    Double value;

    ASTFloat(Token token,
             Double value) {
        super(token);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Literali de tip Bool
class ASTBool extends Expression {
    Boolean value;

    ASTBool(Token token,
            Boolean value) {
        super(token);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Assign
class ASTAssign extends Expression {
    Expression assignExpr;

    ASTAssign(Expression assignExpr,
             Token start) {
        super(start);
        this.assignExpr = assignExpr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
// Adunare
class ASTPlus extends Expression {
    Expression rightExpr;
    Expression op;

    ASTPlus(Expression leftExpr,
                  Expression rightExpr,
                  Expression op,
                  Token token) {
        super(token);
        this.rightExpr = rightExpr;
        this.op = op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Scadere
class ASTMinus extends Expression {
    Expression leftExpr;
    Expression rightExpr;

    ASTMinus(Expression leftExpr,
           Expression rightExpr,
           Token token) {
        super(token);
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
// Inmultire
class ASTMul extends Expression {
    Expression leftExpr;
    Expression rightExpr;
    Expression op;

    ASTMul(Expression leftExpr,
           Expression rightExpr,
           Expression op,
           Token token) {
        super(token);
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.op = op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
// Impartire
class ASTDiv extends Expression {
    Expression leftExpr;
    Expression rightExpr;
    Expression op;

    ASTDiv(Expression leftExpr,
           Expression rightExpr,
           Expression op,
           Token token) {
        super(token);
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.op = op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

class ASTLowerThan extends Expression {
    Expression leftExpr;
    Expression rightExpr;

    ASTLowerThan(Expression leftExpr,
           Expression rightExpr,
           Token token) {
        super(token);
        this.rightExpr = rightExpr;
        this.leftExpr = leftExpr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ASTEqual extends Expression {
    Expression leftExpr;
    Expression rightExpr;

    ASTEqual(Expression leftExpr,
                 Expression rightExpr,
                 Token token) {
        super(token);
        this.rightExpr = rightExpr;
        this.leftExpr = leftExpr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ASTLowerEqual extends Expression {
    Expression leftExpr;
    Expression rightExpr;

    ASTLowerEqual(Expression leftExpr,
                 Expression rightExpr,
                 Token token) {
        super(token);
        this.rightExpr = rightExpr;
        this.leftExpr = leftExpr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ASTUnaryMinus extends Expression {
    String value;

    ASTUnaryMinus(String value,
                 Token token) {
        super(token);
        this.value = value;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}