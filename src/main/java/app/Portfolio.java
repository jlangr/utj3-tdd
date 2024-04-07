package app;

// START:impl
import java.time.Clock;
import static app.TransactionType.BUY;
import static app.TransactionType.SELL;
import static java.lang.Math.abs;
// ...
// END:impl
import java.util.HashMap;
import java.util.Map;


// START:impl
public class Portfolio {
    // START_HIGHLIGHT
    private Transaction lastTransaction;
    private Clock clock = Clock.systemUTC();
    // END_HIGHLIGHT
    // ...
    // END:impl
    private Map<String, Integer> purchases = new HashMap<>();

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    // START:impl
    public void purchase(String symbol, int shares) {
        updateShares(symbol, shares);
    }

    public void sell(String symbol, int shares) {
        throwOnOversell(symbol, shares);
        updateShares(symbol, -shares);
        removeSymbolIfSoldOut(symbol);
    }

    private void updateShares(String symbol, int shares) {
        // START_HIGHLIGHT
        lastTransaction = new Transaction(
            symbol,
            abs(shares),
            shares > 0 ? BUY : SELL,
            clock.instant());
        // END_HIGHLIGHT
        purchases.put(symbol, sharesOf(symbol) + shares);
    }

    // ...
    // END:impl
    private void removeSymbolIfSoldOut(String symbol) {
        if (sharesOf(symbol) == 0)
            purchases.remove(symbol);
    }

    private void throwOnOversell(String symbol, int shares) {
        if (sharesOf(symbol) < shares)
            throw new InvalidTransactionException();
    }

    public int size() {
        return purchases.size();
    }

    public int sharesOf(String symbol) {
        if (!purchases.containsKey(symbol)) return 0;

        return purchases.get(symbol);
    }

    // START:impl
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    // START_HIGHLIGHT
    public Transaction lastTransaction() {
        return lastTransaction;
    }
    // END_HIGHLIGHT
}
// END:impl
