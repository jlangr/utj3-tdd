package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class APortfolio {
    @Test
    void isEmptyWhenCreated() {
        var portfolio = new Portfolio();

        assertTrue(portfolio.isEmpty());
    }

    @Test
    void isNotEmptyAfterPurchase() {
        var portfolio = new Portfolio();

        portfolio.purchase("AAPL", 1);

        assertFalse(portfolio.isEmpty());
    }
}
