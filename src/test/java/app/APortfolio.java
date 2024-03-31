package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    // START:test
    @Test
    void reducesSharesOnSell() {
        portfolio.purchase("AAPL", 100);

        portfolio.sell("AAPL", 25);

        assertEquals(75, portfolio.sharesOf("AAPL"));
    }
    // END: test
}
