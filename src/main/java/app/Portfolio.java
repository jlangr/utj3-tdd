package app;

import java.util.HashSet;
import java.util.Set;

public class Portfolio {
    private int size = 0;
    private Set symbols = new HashSet<String>();

    public boolean isEmpty() {
        return size == 0;
    }

    public void purchase(String symbol, int shares) {
        size++;
        symbols.add(symbol);
    }

    // START:impl
    public int size() {
        return symbols.size();
    }
    // END:impl
}
