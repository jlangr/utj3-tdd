package app;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static app.TransactionType.BUY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATransactionHistory {
    TransactionHistory history = new TransactionHistory();
    Instant now = Instant.now();

    @Test
    void returnsTransactionsForSymbol() {
        history.add(new Transaction("A", 10, BUY, now));
        history.add(new Transaction("B", 20, BUY, now));
        history.add(new Transaction("B", 30, BUY, now));

        var results = history.transactionStreamBySymbol("B");

        assertEquals(results.toList(), List.of(
            new Transaction("B", 30, BUY, now),
            new Transaction("B", 20, BUY, now)));
    }

    @Test
    void returnsSharesOfSymbol() {
        history.add(new Transaction("A", 10, BUY, now));
        history.add(new Transaction("B", 20, BUY, now));
        history.add(new Transaction("B", 30, BUY, now));

        assertEquals(50, history.sharesOf("B"));
    }
}
