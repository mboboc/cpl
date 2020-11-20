public interface ASTVisitor<T> {
    T visit(AstId astId);
    T visit(AstInt astInt);
    T visit(AstIf astIf);
    T visit(AstFloat astFloat);
    T visit(AstBool astBool);
    T visit(AstVarDef astVarDef);
    T visit(AstProg astProg);
    T visit(AstCall call);
    T visit(AstUnaryMinus astUnaryMinus);
    T visit(AstMultDiv astMultDiv);
    T visit(AstPlusMinus astPlusMinus);
    T visit(AstRelational astRelational);
    T visit(AstAssign astAssign);
    T visit(AstFuncDef astFuncDef);
    T visit(AstFormal astFormal);
}
