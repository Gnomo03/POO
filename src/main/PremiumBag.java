public class PremiumBag extends Bag implements Premium {

    public PremiumBag() {
        super();
    }

    public double getPrice() {
        return (10 + (2023 - this.getReleaseDate().getYear())) / 10 * this.getBasePrice();
    }
}
