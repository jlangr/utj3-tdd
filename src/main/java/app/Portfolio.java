package app;

import java.util.HashSet;
import java.util.Set;

// START:impl
public class Portfolio {
    private int size = 0;
    // START_HIGHLIGHT
    private Set symbols = new HashSet<String>();
    // END_HIGHLIGHT

    public boolean isEmpty() {
        // START_HIGHLIGHT
        return symbols.isEmpty();
        // END_HIGHLIGHT
    }

    public void purchase(String symbol, int shares) {
        size++;
        // START_HIGHLIGHT
        symbols.add(symbol);
        // END_HIGHLIGHT
    }

    public int size() {
        // START_HIGHLIGHT
        return symbols.size();
        // END_HIGHLIGHT
    }
}
// END:impl
