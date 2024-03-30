package app;

import java.util.HashMap;
import java.util.Map;

// START:impl
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

    public int sharesOf(String symbol) {
        return purchases.get(symbol);
    }
}
// END:impl