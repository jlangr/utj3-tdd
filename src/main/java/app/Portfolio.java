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
        updateShares(symbol, shares);
    }

    // START:impl
    public void sell(String symbol, int shares) {
        abortOnOversell(symbol, shares);
        updateShares(symbol, -shares);
        // START_HIGHLIGHT
        removeSymbolIfSoldOut(symbol);
        // END_HIGHLIGHT
    }

    // START_HIGHLIGHT
    private void removeSymbolIfSoldOut(String symbol) {
    // END_HIGHLIGHT
        if (sharesOf(symbol) == 0)
            purchases.remove(symbol);

    }
    // END:impl

    private void abortOnOversell(String symbol, int shares) {
        if (sharesOf(symbol) < shares)
            throw new InvalidTransactionException();
    }

    private void updateShares(String symbol, int shares) {
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
