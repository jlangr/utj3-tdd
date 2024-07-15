package app;

import java.time.Clock;
import static app.TransactionType.BUY;
import static app.TransactionType.SELL;
import static java.lang.Math.abs;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Transaction lastTransaction;
    private Clock clock = Clock.systemUTC();
    private Map<String, Integer> purchases = new HashMap<>();

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    // START:impl
    public void purchase(String symbol, int shares) {
        // START_HIGHLIGHT
        updateShares(symbol, shares, BUY);
        // END_HIGHLIGHT
    }

    public void sell(String symbol, int shares) {
        abortOnOversell(symbol, shares);
        // START_HIGHLIGHT
        updateShares(symbol, -shares, SELL);
        // END_HIGHLIGHT
        removeSymbolIfSoldOut(symbol);
    }

    // START_HIGHLIGHT
    private void updateShares(String symbol, int shares, TransactionType type) {
        // END_HIGHLIGHT
        lastTransaction =
            // START_HIGHLIGHT
            new Transaction(symbol, abs(shares), type, clock.instant());
        // END_HIGHLIGHT
        purchases.put(symbol, sharesOf(symbol) + shares);
    }
    // END:impl
    private void removeSymbolIfSoldOut(String symbol) {
        if (sharesOf(symbol) == 0)
            purchases.remove(symbol);
    }

    private void abortOnOversell(String symbol, int shares) {
        if (sharesOf(symbol) < shares)
            throw new InvalidTransactionException();
    }

    public int size() {
        return purchases.size();
    }

    public int sharesOf(String symbol) {
        return purchases.getOrDefault(symbol, 0);
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public Transaction lastTransaction() {
        return lastTransaction;
    }
}
