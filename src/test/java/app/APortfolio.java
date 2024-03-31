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

    // START:test
    @Test
    void separatesSharesBySymbol() {
        portfolio.purchase("SONO", 42);

        portfolio.purchase("AAPL", 1);

        assertEquals(42, portfolio.sharesOf("SONO"));
    }
    // END: test
}
