public class DefinitionPassVisitor implements ASTVisitor<Void> {
  Scope currentScope = null;

  @Override
  public Void visit(Program prog) {
    currentScope = new DefaultScope(null);
    currentScope.add(new FunctionSymbol("print_float", currentScope));
    currentScope.add(new FunctionSymbol("print_int", currentScope));
    currentScope.add(new FunctionSymbol("print_bool", currentScope));
    for (var stmt : prog.stmts) {
      stmt.accept(this);
    }
    return null;
  }

  @Override
  public Void visit(Id id) {
    var symbol = currentScope.lookup(id.getToken().getText());

    // Semnalăm eroare dacă nu există.
    if (symbol == null) {
      ASTVisitor.error(id.getToken(),
              id.getToken().getText() + " undefined");
      return null;
    }

    // Atașăm simbolul nodului din arbore.
    id.setSymbol((IdSymbol) symbol);

    return null;
  }

  @Override
  public Void visit(VarDef varDef) {
    var id = varDef.id;
    var sym = new IdSymbol(varDef.id.getToken().getText());
    id.setSymbol(sym);

    if (!currentScope.add(sym))
      ASTVisitor.error(id.getToken(),
              id.getToken().getText() + " redefined");

    id.setSymbol(sym);

    if (varDef.initValue != null)
      varDef.initValue.accept(this);

    return null;
  }

  @Override
  public Void visit(FuncDef funcDef) {
    var id = funcDef.id;
    var sym = new FunctionSymbol(id.getToken().getText(), currentScope);

    if (!currentScope.add(sym))
      ASTVisitor.error(funcDef.id.getToken(),
              funcDef.id.getToken().getText() + " function redefined");

    id.setSymbol(sym);

    currentScope = sym;
    for (var formal : funcDef.formals) {
      formal.accept(this);
    }

    funcDef.body.accept(this);
    currentScope = sym.getParent();
    return null;
  }

  @Override
  public Void visit(Formal formal) {
    // Adăugăm simbolul în domeniul de vizibilitate curent(practic domeniul de vizibilitate dat
    // de funcția al cărei parametru este parametrul formal curent).
    var id = formal.id;
    var symbol = new IdSymbol(id.getToken().getText());

    if (!currentScope.add(symbol)) {
      ASTVisitor.error(id.getToken(), id.getToken().getText() + " formal undefined"
      );
      return null;
    }

    id.setSymbol(symbol);
    id.setScope(currentScope);

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
  public Void visit(Assign assign) {
    assign.id.accept(this);
    assign.expr.accept(this);
    assign.id.setScope(currentScope);
    return null;
  }

  @Override
  public Void visit(Call call) {
    var id = call.id;
    for (var arg : call.args) {
      arg.accept(this);
    }
    id.setScope(currentScope);
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