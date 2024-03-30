package app;

import java.util.HashSet;
import java.util.Set;

public class Portfolio {
    private Set symbols = new HashSet<String>();
    private int shares;

    public boolean isEmpty() {
        return symbols.isEmpty();
    }

    public void purchase(String symbol, int shares) {
        symbols.add(symbol);
        this.shares = shares;
    }

    // START:impl
    public int size() {
        return symbols.size();
    }

    public int sharesOf(String symbol) {
        return shares;
    }
    // END:impl
}
