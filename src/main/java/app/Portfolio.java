package app;

import java.util.HashSet;
import java.util.Set;

public class Portfolio {
    private Set symbols = new HashSet<String>();

    public boolean isEmpty() {
        return symbols.isEmpty();
    }

    public void purchase(String symbol, int shares) {
        symbols.add(symbol);
    }

    // START:impl
    public int size() {
        return symbols.size();
    }
    // END:impl
}
