package app;

public class Portfolio {
    private boolean isEmpty = true;

    public boolean isEmpty() {
        return isEmpty;
    }

    public void purchase(String symbol, int shares) {
        isEmpty = false;
    }
}
