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

    // START:test
    @Test
    void incrementsSizeWithEachPurchaseDifferentSymbol() {
        portfolio.purchase("AAPL", 1);

        portfolio.purchase("SONO", 1);

        assertEquals(2, portfolio.size());
    }
    // END: test
}
