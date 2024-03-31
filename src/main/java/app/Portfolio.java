package app;

import java.util.HashSet;
import java.util.Set;

// START:impl
public class Portfolio {
    private Set symbols = new HashSet<String>();

    public boolean isEmpty() {
        return symbols.isEmpty();
    }

    public void purchase(String symbol, int shares) {
        symbols.add(symbol);
    }

    public int size() {
        return symbols.size();
    }
}
// END:impl
