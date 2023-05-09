package app;

import java.time.LocalDate;
import java.time.Period;
import java.util.Stack;

public class PremiumSneaker extends Sneaker implements Premium {

  public PremiumSneaker() {
    super();
  }

  public PremiumSneaker(String description, String brand, double basePrice,
      Carrier carrier, double conditionScore, Stack<Integer> previousOwners, double size,
      SneakerType type, String color, LocalDate releaseDate, int userId) {
    super(description, brand, basePrice,
        carrier, conditionScore, previousOwners, size,
        type, color, releaseDate, userId);

  }

  public PremiumSneaker(Sneaker oneSneaker) {
    super(oneSneaker);
  }

  @Override
  public PremiumSneaker clone() {

    return new PremiumSneaker(this);
  }

  @Override
  public double getPrice() {
    LocalDate now = LocalDate.now();
    int yearDiff = Period.between(this.getReleaseDate(), now).getYears();

    return this.getBasePrice() * (1 + (yearDiff * 0.025));
  }

  @Override
  public String toString() {
    return "Sneaker{" +
        "ID=" + this.getID() + '\'' +
        ", description='" + getDescription() + '\'' +
        ", brand='" + getBrand() + '\'' +
        ", reference='" + getReference() + '\'' +
        ", basePrice=" + getBasePrice() +
        ", priceCorrection=" + getPriceCorrection() +
        ", carrier='" + getCarrier().getName() + '\'' +
        ", conditionScore=" + getConditionScore() +
        ", previousOwners=" + getPreviousOwners() +
        ", size=" + getSize() +
        ", type=" + getType() +
        ", color='" + getColor() + '\'' +
        ", releaseDate=" + this.getReleaseDate() + '\'' +
        ", Price=" + getPrice() +
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
    sb.append("|" + Util.formatCell("Type: " + getType(), boxWidth) + "|\n");
    sb.append("|" + Util.formatCell("Size: " + getSize(), boxWidth) + "|\n");
    sb.append("|" + Util.formatCell("Color: " + getColor(), boxWidth) + "|\n");
    sb.append("|" + Util.formatCell("Carrier: " + getCarrier().getName(), boxWidth) + "|\n");
    sb.append("|" + Util.formatCell("This is a Premium Item ", boxWidth) + "|\n");

    // Create the bottom border
    sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

    return sb.toString();
  }

}