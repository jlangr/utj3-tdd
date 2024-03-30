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

    // START:test
    @Test
    void hasSize0WhenCreated() {
        assertEquals(0, portfolio.size());
    }
    // END: test
}
