import java.util.LinkedHashMap;
import java.util.Map;

// O functie este atât simbol, cât și domeniu de vizibilitate pentru parametrii
// săi formali.
public class FunctionSymbol extends IdSymbol implements Scope {
  Map<String, Symbol> symbols = new LinkedHashMap<>();
  Scope parent;

  public FunctionSymbol(String name, Scope parent) {
    super(name);
    this.parent = parent;
  }

  @Override
  public boolean add(Symbol s) {
    if (symbols.containsKey(s.getName()))
      return false;

    symbols.put(s.getName(), s);

    return true;
  }

  @Override
  public Symbol lookup(String s) {
    var symbol = symbols.get(s);

    if (symbol != null)
      return parent.lookup(s);

    return null;
  }

  @Override
  public Scope getParent() {
    return parent;
  }
}