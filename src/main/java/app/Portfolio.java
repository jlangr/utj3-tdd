package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// START:impl
public class Portfolio {
    // START_HIGHLIGHT
    private Map<String, Integer> purchases = new HashMap<>();
    // END_HIGHLIGHT
    private Set symbols = new HashSet<String>();
    private int shares;

    public boolean isEmpty() {
        // START_HIGHLIGHT
        return purchases.isEmpty();
        // END_HIGHLIGHT
    }

    public void purchase(String symbol, int shares) {
        symbols.add(symbol);
        this.shares = shares;
        // START_HIGHLIGHT
        purchases.put(symbol, shares);
        // END_HIGHLIGHT
    }

    public int size() {
        // START_HIGHLIGHT
        return purchases.size();
        // END_HIGHLIGHT
    }

    public int sharesOf(String symbol) {
        // START_HIGHLIGHT
        return purchases.get(symbol);
        // END_HIGHLIGHT
    }
}
// END:impl
