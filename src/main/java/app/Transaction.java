package app;

import java.time.Instant;

public record Transaction(String symbol, int shares, TransactionType type, Instant now) {
}
