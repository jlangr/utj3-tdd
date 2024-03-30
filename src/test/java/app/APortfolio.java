package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class APortfolio {
    @Test
    void isEmptyWhenCreated() {
        var portfolio = new Portfolio();

        assertTrue(portfolio.isEmpty());
    }
}
