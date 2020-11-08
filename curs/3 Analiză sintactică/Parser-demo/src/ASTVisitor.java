public interface ASTVisitor<T> {
    T visit(Id id);
    T visit(Int intt);
    T visit(If iff);
}
