package app;

// START:impl
public class Portfolio {
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void purchase(String symbol, int shares) {
        size++;
    }

    public int size() {
        return size;
    }
}
// END:impl
