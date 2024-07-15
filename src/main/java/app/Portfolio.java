package app;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> purchases = new HashMap<>();

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    // START:purchase
    public void purchase(String symbol, int shares) {
        // START_HIGHLIGHT
        purchases.put(symbol, sharesOf(symbol) + shares);
        // END_HIGHLIGHT
    }
    // END:purchase

    public int size() {
        return purchases.size();
    }

    public int sharesOf(String symbol) {
        return purchases.getOrDefault(symbol, 0);
    }
}
