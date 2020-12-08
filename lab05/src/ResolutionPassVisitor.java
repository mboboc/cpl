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
    var id = assign.id;
    var expr = assign.expr;
    var sym = id.getSymbol();

    if (sym instanceof FunctionSymbol) {
      ASTVisitor.error(id.getToken(), id.getToken().getText() + " is not a variable"
      );
      return null;
    }

    if (sym == null) {
      ASTVisitor.error(id.getToken(),
              "variable " + id.getToken().getText() + " in assignment doesn't exist"
      );
      return null;
    }

    id.accept(this);
    expr.accept(this);
    return null;
  }

  @Override
  public Void visit(Call call) {
    // Verificăm dacă funcția există în scope. Nu am putut face
    // asta în prima trecere din cauza a forward declarations.
    //
    // De asemenea, verificăm că Id-ul pe care se face apelul de funcție
    // este, într-adevăr, o funcție și nu o variabilă.
    var id = call.id;

    var sym = id.getScope().lookup(id.getToken().getText());

    if (!(id.getSymbol() instanceof FunctionSymbol)) {
      ASTVisitor.error(id.getToken(), id.getToken().getText() + " not a function"
      );
      return null;
    }

    if (sym == null) {
      ASTVisitor.error(id.getToken(), id.getToken().getText() + " function undefined"
      );
      return null;
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