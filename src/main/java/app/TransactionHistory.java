package app;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static app.TransactionType.BUY;
import static java.util.stream.Collectors.groupingBy;

public class TransactionHistory {
    private LinkedList<Transaction> transactions = new LinkedList<>();

    public void add(Transaction transaction) {
        transactions.addFirst(transaction);
    }

    public List<Transaction> transactions() {
        return transactions;
    }

    public Transaction lastTransaction() {
        return transactions.peekFirst();
    }

    public boolean isEmpty() {
        return transactions.isEmpty();
    }

    public int sharesOf(String symbol) {
        return sum(transactionStreamBySymbol(symbol));
    }

    public int size() {
        var bySymbol = transactions.stream()
            .collect(groupingBy(Transaction::symbol));
        return (int)bySymbol.values()
            .stream()
            .mapToInt(ts -> sum(ts.stream()))
            .filter(t -> t > 0)
            .count();
    }

    int sum(Stream<Transaction> transactions) {
        return transactions
            .mapToInt(t -> t.shares() * (t.type() == BUY ? 1 : -1))
            .sum();
    }

    public Stream<Transaction> transactionStreamBySymbol(String symbol) {
        return transactions.stream()
            .filter(t -> t.symbol().equals(symbol));
    }
}
