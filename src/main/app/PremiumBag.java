package app;

import java.time.LocalDate;
import java.time.Period;
import java.util.Stack;

public class PremiumBag extends Bag implements Premium {

    PremiumBag() {
        super();
    }

    public PremiumBag(String description, String brand, double basePrice,
            Carrier carrier, double conditionScore, Stack<Integer> previousOwners, double dimension,
            String material, LocalDate releaseDate, int userId) {
        super(description, brand, basePrice,
                carrier, conditionScore, previousOwners, dimension,
                material, releaseDate, userId);

    }

    /**
     * Copy constructor for the Bag class.
     * 
     * @param oneBag The Bag object to be copied.
     */
    public PremiumBag(Bag oneBag) {
        super(oneBag);
    }

    @Override
    public PremiumBag clone() {

        return new PremiumBag(this);
    }

    @Override
    public String toString() {
        return "Bag{" +
                "ID=" + this.getID() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", reference='" + getReference() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", priceCorrection=" + getPriceCorrection() +
                ", carrier='" + getCarrier().getName() + '\'' +
                ", conditionScore=" + getConditionScore() +
                ", previousOwners=" + getPreviousOwners() +
                ", dimension=" + getDimension() +
                ", material=" + getMaterial() +
                ", releaseDate=" + getReleaseDate() + '\'' +
                ", Price=" + getPrice() + '\'' +
                ", Premium Status" +
                '}';
    }

    @Override
    public String showItem() {

        StringBuilder sb = new StringBuilder();
        int boxWidth = 30;

        // Create the top border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        // Append the student information
        sb.append("|" + Util.formatCell("Description: " + getDescription(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Brand: " + getBrand(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Price: " + getPrice(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Material: " + getMaterial(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Dimension: " + getDimension(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Carrier: " + getCarrier().getName(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("This is a Premium Item", boxWidth) + "|\n");

        // Create the bottom border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        return sb.toString();
    }

    @Override
    public double getPrice() {
        LocalDate now = LocalDate.now();
        int yearDiff = Period.between(this.getReleaseDate(), now).getYears();

        return this.getBasePrice() * (1 + (yearDiff * 0.025));
    }
}