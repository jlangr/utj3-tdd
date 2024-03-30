package app;

// START:impl
public class Portfolio {
    // START_HIGHLIGHT
    private boolean isEmpty = true;
    // END_HIGHLIGHT

    public boolean isEmpty() {
        // START_HIGHLIGHT
        return isEmpty;
        // END_HIGHLIGHT
    }

    public void purchase(String symbol, int shares) {
        // START_HIGHLIGHT
        isEmpty = false;
        // END_HIGHLIGHT
    }
}
// END:impl
