package app;

public class Portfolio {
    private boolean isEmpty = true;

    public boolean isEmpty() {
        return isEmpty;
    }

    public void purchase(String symbol, int shares) {
        isEmpty = false;
    }

    // START:impl
    public int size() {
        return isEmpty ? 0 : 1;
    }
    // END:impl
}
