package app;

// START:impl
public class Portfolio {
    private boolean isEmpty = true;
    // START_HIGHLIGHT
    private int size = 0;
    // END_HIGHLIGHT

    public boolean isEmpty() {
        return isEmpty;
    }

    public void purchase(String symbol, int shares) {
        isEmpty = false;
        // START_HIGHLIGHT
        size++;
        // END_HIGHLIGHT
    }

    public int size() {
        // START_HIGHLIGHT
        return size;
        // END_HIGHLIGHT
    }
}
// END:impl
