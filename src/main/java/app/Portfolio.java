package app;

// START:impl
import java.time.Clock;
import java.util.LinkedList;
import java.util.List;
// ...
// END:impl
import static app.TransactionType.BUY;
import static app.TransactionType.SELL;
import static java.lang.Math.abs;
import java.util.HashMap;
import java.util.Map;

// START:impl
public class Portfolio {
    // START_HIGHLIGHT
    private Transaction lastTransaction;
    private LinkedList transactions = new LinkedList();
    // END_HIGHLIGHT
    // ...
    // END:impl
    private Clock clock = Clock.systemUTC();
    private Map<String, Integer> purchases = new HashMap<>();

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    public void purchase(String symbol, int shares) {
        updateShares(symbol, shares, BUY);
    }

    public void sell(String symbol, int shares) {
        throwOnOversell(symbol, shares);
        updateShares(symbol, -shares, SELL);
        removeSymbolIfSoldOut(symbol);
    }

    // START:impl
    private void updateShares(String symbol,
                              int shares,
                              TransactionType type) {
        lastTransaction =
            new Transaction(symbol, abs(shares), type, clock.instant());
        // START_HIGHLIGHT
        transactions.addFirst(lastTransaction);
        // END_HIGHLIGHT
        purchases.put(symbol, sharesOf(symbol) + shares);
    }

    public Transaction lastTransaction() {
        return lastTransaction;
    }

    // START_HIGHLIGHT
    public List<Transaction> transactions() {
        return transactions;
    }
    // END_HIGHLIGHT

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

    public void setClock(Clock clock) {
        this.clock = clock;
    }
    // START:impl
}
// END:impl
