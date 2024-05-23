package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static app.TransactionType.BUY;
import static app.TransactionType.SELL;
import static org.junit.jupiter.api.Assertions.*;

public class APortfolio {
    Portfolio portfolio;

    @BeforeEach
    void create() {
        portfolio = new Portfolio();
    }

    @Test
    void isEmptyWhenCreated() {
        assertTrue(portfolio.isEmpty());
    }

    @Test
    void isNotEmptyAfterPurchase() {
        portfolio.purchase("AAPL", 1);

        assertFalse(portfolio.isEmpty());
    }

    @Test
    void hasSize0WhenCreated() {
        assertEquals(0, portfolio.size());
    }

    @Test
    void hasSize1OnPurchase() {
        portfolio.purchase("AAPL", 1);

        assertEquals(1, portfolio.size());
    }

    @Test
    void incrementsSizeWithEachPurchaseDifferentSymbol() {
        portfolio.purchase("AAPL", 1);

        portfolio.purchase("SONO", 1);

        assertEquals(2, portfolio.size());
    }

    @Test
    void doesNotIncrementSizeWithPurchaseSameSymbol() {
        portfolio.purchase("AAPL", 1);

        portfolio.purchase("AAPL", 1);

        assertEquals(1, portfolio.size());
    }

    @Test
    void returnsSharesGivenSymbol() {
        portfolio.purchase("AAPL", 42);

        assertEquals(42, portfolio.sharesOf("AAPL"));
    }

    @Test
    void separatesSharesBySymbol() {
        portfolio.purchase("SONO", 42);

        portfolio.purchase("AAPL", 1);

        assertEquals(42, portfolio.sharesOf("SONO"));
    }

    @Test
    void returns0SharesForSymbolNotPurchased() {
        assertEquals(0, portfolio.sharesOf("SONO"));
    }

    @Test
    void accumulatesSharesOfSameSymbolPurchase() {
        portfolio.purchase("SONO", 42);

        portfolio.purchase("SONO", 100);

        assertEquals(142, portfolio.sharesOf("SONO"));
    }

    @Test
    void reducesSharesOnSell() {
        portfolio.purchase("AAPL", 100);

        portfolio.sell("AAPL", 25);

        assertEquals(75, portfolio.sharesOf("AAPL"));
    }

    @Test
    void throwsWhenSellingMoreSharesThanHeld() {
        portfolio.purchase("AAPL", 10);

        assertThrows(InvalidTransactionException.class, () ->
            portfolio.sell("AAPL", 10 + 1));
    }

    @Test
    void reducesSizeWhenLiquidatingSymbol() {
        portfolio.purchase("AAPL", 50);

        portfolio.sell("AAPL", 50);

        assertEquals(0, portfolio.size());
    }

    @Nested
    class LastTransaction {
        Instant now = Instant.now();

        @BeforeEach
        void injectFixedClock() {
            Clock clock = Clock.fixed(now, ZoneId.systemDefault());
            portfolio.setClock(clock);
        }

        @Test
        void returnsLastTransactionAfterPurchase() {
            portfolio.purchase("SONO", 20);

            assertEquals(portfolio.lastTransaction(),
                new Transaction("SONO", 20, BUY, now));
        }

        @Test
        void returnsLastTransactionAfterSale() {
            portfolio.purchase("SONO", 200);

            portfolio.sell("SONO", 40);

            assertEquals(portfolio.lastTransaction(),
                new Transaction("SONO", 40, SELL, now));
        }

        @Test
        void returnsNullWhenNoPreviousTransactionMade() {
            assertNull(portfolio.lastTransaction());
        }
    }

    // START:test
    @Nested
    class TransactionHistory {
        Instant now = Instant.now();

        @BeforeEach
        void injectFixedClock() {
            Clock clock = Clock.fixed(now, ZoneId.systemDefault());
            portfolio.setClock(clock);
        }

        @Test
        void returnsListOfTransactionsReverseChronologically() {
            portfolio.purchase("A", 1);
            portfolio.purchase("B", 2);
            portfolio.purchase("C", 3);

            assertEquals(portfolio.transactions(), List.of(
                new Transaction("C", 3, BUY, now),
                new Transaction("B", 2, BUY, now),
                new Transaction("A", 1, BUY, now)
            ));
        }
    }
    // END:test
}
