package app;
public class VintageProfit implements Querier {

    private double vp;

    public VintageProfit(Double m) {
        vp = m;
    }

    @Override
    public Double execute() throws NullPointerException {

        return vp;
    }
}