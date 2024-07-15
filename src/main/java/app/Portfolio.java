package app;

import java.time.Clock;
import java.util.LinkedList;
import java.util.List;
import static app.TransactionType.BUY;
import static app.TransactionType.SELL;
import static java.lang.Math.abs;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private LinkedList<Transaction> transactions = new LinkedList<>();
    private Clock clock = Clock.systemUTC();
    private Map<String, Integer> purchases = new HashMap<>();

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    public void purchase(String symbol, int shares) {
        updateShares(symbol, shares, BUY);
    }

    public void sell(String symbol, int shares) {
        abortOnOversell(symbol, shares);
        updateShares(symbol, -shares, SELL);
        removeSymbolIfSoldOut(symbol);
    }

    // START:impl
    private void updateShares(String symbol,
                              int shares,
                              TransactionType type) {
        // START_HIGHLIGHT
        var transaction =
            // END_HIGHLIGHT
            new Transaction(symbol, abs(shares), type, clock.instant());
        // START_HIGHLIGHT
        transactions.addFirst(transaction);
        // END_HIGHLIGHT
        purchases.put(symbol, sharesOf(symbol) + shares);
    }

    public Transaction lastTransaction() {
        // START_HIGHLIGHT
        return transactions.peekFirst();
        // END_HIGHLIGHT
    }
    // END:impl

    public List<Transaction> transactions() {
        return transactions;
    }

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
}
