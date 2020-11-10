public interface ASTVisitor<T> {
    T visit(Id id);
    T visit(Int intt);
    T visit(If iff);
    T visit(ASTProg prog);
    T visit(ASTType type);
    T visit(ASTVarDef varDef);
    T visit(ASTFormal formal);
    T visit(ASTFuncDef funcDef);
    T visit(ASTFuncCall funcCall);
    T visit(ASTPlus plus);
    T visit(ASTMinus minus);
    T visit(ASTMul mult);
    T visit(ASTDiv div);
    T visit(ASTLowerThan lt);
    T visit(ASTLowerEqual le);
    T visit(ASTEqual eq);
    T visit(ASTUnaryMinus unaryMinus);
    T visit(ASTAssign assign);
    T visit(ASTFloat f);
    T visit(ASTBool bool);
}
