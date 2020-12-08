import java.util.HashMap;
import java.util.LinkedHashMap;

// O functie este atât simbol, cât și domeniu de vizibilitate pentru parametrii
// săi formali.

public class FunctionSymbol extends IdSymbol implements Scope {
  HashMap<String, Symbol> symbols = new LinkedHashMap<>();
  Scope parent;

  public FunctionSymbol(String name, Scope parent) {
    super(name);
    this.parent = parent;
  }

  @Override
  public boolean add(Symbol s) {
    String name = s.getName();

    if (symbols.containsKey(name))
      return false;

    symbols.put(s.getName(), s);

    return true;
  }

  @Override
  public Symbol lookup(String s) {
    var sym = symbols.get(s);

    if (sym != null)
      return sym;

    return this.getParent().lookup(s);
  }

  @Override
  public Scope getParent() {
    return this.parent;
  }
}