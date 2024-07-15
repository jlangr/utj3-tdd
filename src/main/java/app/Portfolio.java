package app;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> purchases = new HashMap<>();

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    // START:sell
    public void purchase(String symbol, int shares) {
        // START_HIGHLIGHT
        updateShares(symbol, shares);
        // END_HIGHLIGHT
    }

    public void sell(String symbol, int shares) {
        // START_HIGHLIGHT
        updateShares(symbol, -shares);
        // END_HIGHLIGHT
    }

    // START_HIGHLIGHT
    private void updateShares(String symbol, int shares) {
        // END_HIGHLIGHT
        purchases.put(symbol, sharesOf(symbol) + shares);
    }
    // END:sell

    public int size() {
        return purchases.size();
    }

    public int sharesOf(String symbol) {
        return purchases.getOrDefault(symbol, 0);
    }
}
// END:impl
