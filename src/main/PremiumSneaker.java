public class PremiumSneaker extends Sneaker {

    public PremiumSneaker() {
        super();
    }

    public double getPrice() {
        return (10 + (2023 - this.getReleaseDate().getYear())) / 10 * this.getBasePrice();
    }
}
