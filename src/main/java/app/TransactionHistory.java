package app;

import java.util.LinkedList;
import java.util.List;

public class TransactionHistory {
    private LinkedList<Transaction> transactions = new LinkedList<>();

    void add(Transaction transaction) {
        transactions.addFirst(transaction);
    }

    public List<Transaction> transactions() {
        return transactions;
    }

    Transaction lastTransaction() {
        return transactions.peekFirst();
    }
}
