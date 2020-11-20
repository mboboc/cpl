import org.antlr.v4.runtime.Token;
import java.util.*;

// Rădăcina ierarhiei de clase reprezentând nodurile arborelui de sintaxă
// abstractă (AST). Singura metodă permite primirea unui visitor.
public abstract class ASTNode {
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

abstract class Expression extends ASTNode {
    // Reținem un token descriptiv al expresiei, pentru a putea afișa ulterior
    // informații legate de linia și coloana eventualelor erori semantice.
    Token token;

    Expression(Token token) {
        this.token = token;
    }
}

class AstProg extends ASTNode {
   List<Expression> exprs;

    public AstProg(Token token, List<Expression> exprs) {
        this.exprs = exprs;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Identifiers */
class AstId extends Expression {
    String value;

    AstId(Token token) {
        super(token);
        value = token.getText();
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Integers */
class AstInt extends Expression {
    int value;

    AstInt(Token token) {
        super(token);
        value = Integer.parseInt(token.getText());
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Float */
class AstFloat extends Expression {
    Double value;

    AstFloat(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Bool */
class AstBool extends Expression {
    Boolean value;

    AstBool(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* If */
class AstIf extends Expression {
    // Sunt necesare trei câmpuri pentru cele trei componente ale expresiei.
    Expression cond;
    Expression thenBranch;
    Expression elseBranch;

    AstIf(Expression cond,
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

/* Variable definition */
class AstVarDef extends Expression {
    Token type;
    Token name;
    Expression value;

    public AstVarDef(Token type, Token name, Expression value) {
        super(type);
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public AstVarDef(Token type, Token name) {
        super(type);
        this.type = type;
        this.name = name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Function call */
class AstCall extends Expression {
    Token name;
    List<Expression> args;

    public AstCall(Token token) {
        super(token);
        this.name = token;
    }

    public AstCall(Token token, List<Expression> args) {
        super(token);
        this.name = token;
        this.args = args;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Negative number */
class AstUnaryMinus extends Expression {
    Expression e;

    public AstUnaryMinus(Token token, Expression e) {
        super(token);
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Multipliction and Division */
class AstMultDiv extends Expression {
    Expression left;
    Expression right;
    Token op;

    public AstMultDiv(Token op, Expression left, Expression right) {
        super(op);
        this.left = left;
        this.right = right;
        this.op = op;
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Plus and Minus */
class AstPlusMinus extends Expression {
    Expression left;
    Expression right;
    Token op;

    public AstPlusMinus(Token op, Expression left, Expression right) {
        super(op);
        this.left = left;
        this.right = right;
        this.op = op;
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Relational */
class AstRelational extends Expression {
    Expression left;
    Expression right;
    Token op;

    public AstRelational(Token op, Expression left, Expression right) {
        super(op);
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Assign */
class AstAssign extends Expression {
    Token id;
    Expression e;

    AstAssign(Token token, Expression e) {
        super(token);
        id = token;
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Function definition */
class AstFuncDef extends Expression {
    Token returnType;
    Token name;
    List<Expression> formals;
    Expression body;

    public AstFuncDef(Token returnType, Token name, Expression e) {
        super(returnType);
        this.returnType = returnType;
        this.name = name;
        this.body = e;
    }

    public AstFuncDef(Token returnType, Token name, Expression e, List<Expression> formals) {
        super(returnType);
        this.returnType = returnType;
        this.name = name;
        this.body = e;
        this.formals = formals;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

/* Formal */
class AstFormal extends Expression {
    Token type;
    Token id;

    AstFormal(Token type, Token id) {
        super(type);
        this.type = type;
        this.id = id;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}