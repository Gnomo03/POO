public class PremiumSneaker extends Sneaker implements Premium {

    public PremiumSneaker() {
        super();
    }

    public double getPrice() {
        return (10 + (2023 - this.getReleaseDate().getYear())) / 10 * this.getBasePrice();
    }
}
