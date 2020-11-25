public class ResolutionPassVisitor implements ASTVisitor<Void> {
  @Override
  public Void visit(Program prog) {
    for (var stmt : prog.stmts) {
      stmt.accept(this);
    }
    return null;
  }

  @Override
  public Void visit(Assign assign) {
    if (assign.id.getSymbol() instanceof FunctionSymbol) {
      ASTVisitor.error(assign.id.getToken(), assign.id.getToken().getText() + "cannot assign");

      return null;
    }

    assign.id.accept(this);
    assign.expr.accept(this);
    return null;
  }

  @Override
  public Void visit(Call call) {
    if (call.id.getScope().lookup(call.id.token.getText()) == null) {
      ASTVisitor.error(call.id.getToken(), call.id.getToken().getText());
      return null;
    }

    for (Expression expression : call.args) {
      expression.accept(this);
    }

    return null;
  }

  @Override
  public Void visit(VarDef varDef) {
    if (varDef.initValue != null)
      varDef.initValue.accept(this);
    return null;
  }

  @Override
  public Void visit(FuncDef funcDef) {
    return null;
  }

  @Override
  public Void visit(Id id) {
    return null;
  }

  @Override
  public Void visit(If iff) {
    iff.cond.accept(this);
    iff.thenBranch.accept(this);
    iff.elseBranch.accept(this);
    return null;
  }

  @Override
  public Void visit(Type type) {
    return null;
  }

  @Override
  public Void visit(Formal formal) {
    formal.id.accept(this);
    return null;
  }

  // Operații aritmetice.
  @Override
  public Void visit(UnaryMinus uMinus) {
    uMinus.expr.accept(this);
    return null;
  }

  @Override
  public Void visit(Div div) {
    div.left.accept(this);
    div.right.accept(this);
    return null;
  }

  @Override
  public Void visit(Mult mult) {
    mult.left.accept(this);
    mult.right.accept(this);
    return null;
  }

  @Override
  public Void visit(Plus plus) {
    plus.left.accept(this);
    plus.right.accept(this);
    return null;
  }

  @Override
  public Void visit(Minus minus) {
    minus.left.accept(this);
    minus.right.accept(this);
    return null;
  }

  @Override
  public Void visit(Relational relational) {
    return null;
  }

  // Tipurile de bază
  @Override
  public Void visit(Int intt) {
    return null;
  }

  @Override
  public Void visit(Bool bool) {
    return null;
  }

  @Override
  public Void visit(FloatNum floatNum) {
    return null;
  }

};