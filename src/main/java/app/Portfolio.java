package app;

public class Portfolio {
    private boolean isEmpty = true;
    private int size = 0;

    public boolean isEmpty() {
        return isEmpty;
    }

    public void purchase(String symbol, int shares) {
        isEmpty = false;
        size++;
    }

    // START:impl
    public int size() {
        return size;
    }
    // END:impl
}
