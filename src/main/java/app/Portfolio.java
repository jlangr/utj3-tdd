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
        purchases.put(symbol, sharesOf(symbol) + shares);
    }

    // START_HIGHLIGHT
    public void sell(String symbol, int shares) {
        purchases.put(symbol, sharesOf(symbol) - shares);
    }
    // END_HIGHLIGHT
    // END:sell

    public int size() {
        return purchases.size();
    }

    public int sharesOf(String symbol) {
        if (!purchases.containsKey(symbol)) return 0;

        return purchases.get(symbol);
    }
}
// END:impl
