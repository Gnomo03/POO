public class VintageProfit implements Querier {

    private Model hm;

    public VintageProfit(Model m) {
        hm = m;
    }

    @Override
    public Double execute() throws NullPointerException {

        if (hm.getVintageProfit() <= 0)
            throw new NullPointerException("No Profit");

        return hm.getVintageProfit();
    }
}
