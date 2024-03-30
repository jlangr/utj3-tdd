package app;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> purchases = new HashMap<>();

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    public void purchase(String symbol, int shares) {
        purchases.put(symbol, sharesOf(symbol + shares)); // OOPS!
    }

    public int size() {
        return purchases.size();
    }

    public int sharesOf(String symbol) {
        // START_HIGHLIGHT
        if (!purchases.containsKey(symbol)) return 0;
        // END_HIGHLIGHT

        return purchases.get(symbol);
    }
}
// END:impl
