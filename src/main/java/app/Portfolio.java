package app;

import java.time.Clock;
import java.util.List;
import static app.TransactionType.BUY;
import static app.TransactionType.SELL;
import static java.lang.Math.abs;

public class Portfolio {
    private TransactionHistory history = new TransactionHistory();
    private Clock clock = Clock.systemUTC();

    public void purchase(String symbol, int shares) {
        updateShares(symbol, shares, BUY);
    }

    public void sell(String symbol, int shares) {
        throwOnOversell(symbol, shares);
        updateShares(symbol, -shares, SELL);
    }

    private void throwOnOversell(String symbol, int shares) {
        if (sharesOf(symbol) < shares)
            throw new InvalidTransactionException();
    }

    private void updateShares(String symbol,
                              int shares,
                              TransactionType type) {
        var transaction =
            new Transaction(symbol, abs(shares), type, clock.instant());
        history.add(transaction);
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

    public Transaction lastTransaction() {
        return history.lastTransaction();
    }

    public List<Transaction> transactions() {
        return history.transactions();
    }

    public int size() {
        return history.size();
    }

    public int sharesOf(String symbol) {
        return history.sharesOf(symbol);
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
