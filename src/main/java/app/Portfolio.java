package app;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> purchases = new HashMap<>();

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    public void purchase(String symbol, int shares) {
        purchases.put(symbol, shares);
    }

    public int size() {
        return purchases.size();
    }

    // START:impl
    public int sharesOf(String symbol) {
        // START_HIGHLIGHT
        return purchases.getOrDefault(symbol, 0);
        // END_HIGHLIGHT
    }
    // END:impl
}
