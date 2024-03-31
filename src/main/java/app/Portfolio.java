package app;

import java.util.HashSet;
import java.util.Set;

// START:impl
public class Portfolio {
    private Set symbols = new HashSet<String>();
    // START_HIGHLIGHT
    private int shares;
    // END_HIGHLIGHT

    // ...
    // END:impl
    public boolean isEmpty() {
        return symbols.isEmpty();
    }

    public int size() {
        return symbols.size();
    }

    // START:impl
    public void purchase(String symbol, int shares) {
        symbols.add(symbol);
        // START_HIGHLIGHT
        this.shares = shares;
        // END_HIGHLIGHT
    }

    // START_HIGHLIGHT
    public int sharesOf(String symbol) {
        return shares;
    }
    // END_HIGHLIGHT
}
// END:impl
