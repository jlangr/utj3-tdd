package app;

import java.time.Clock;
import java.util.List;
import static app.TransactionType.BUY;
import static app.TransactionType.SELL;
import static java.lang.Math.abs;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private TransactionHistory history = new TransactionHistory();
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

    private void updateShares(String symbol,
                              int shares,
                              TransactionType type) {
        var transaction =
            new Transaction(symbol, abs(shares), type, clock.instant());
        history.add(transaction);
        purchases.put(symbol, sharesOf(symbol) + shares);
    }

    public Transaction lastTransaction() {
        return history.lastTransaction();
    }

    public List<Transaction> transactions() {
        return history.transactions();
    }

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
        return purchases.getOrDefault(symbol, 0);
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
