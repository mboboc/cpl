import org.antlr.v4.runtime.Token;

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
