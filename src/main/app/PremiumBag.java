package app;
import java.time.LocalDate;
import java.time.Period;
import java.util.Stack;


public class PremiumBag extends Bag{

    PremiumBag(){
    super();
    }


    public PremiumBag(String description, String brand, double basePrice,
    Carrier carrier, double conditionScore, Stack<Integer> previousOwners, double dimension,
    String material, LocalDate releaseDate, int userId) {
    super( description, brand,  basePrice,
     carrier,  conditionScore,previousOwners, dimension,
     material, releaseDate,userId);

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
    public PremiumBag clone(){

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
                ", carrier='" + getCarrier() + '\'' +
                ", conditionScore=" + getConditionScore() +
                ", previousOwners=" + getPreviousOwners() +
                ", dimension=" + getDimension() +
                ", material=" + getMaterial() +
                ", releaseDate=" + getReleaseDate() + '\''+
                ", Price=" + getPrice() + '\''+
                ", Premium Status" +
                '}';
    }

    @Override
    public double getPrice(){
     LocalDate now = LocalDate.now();
    int yearDiff = Period.between(this.getReleaseDate(), now).getYears();

            return this.getBasePrice() * (1 + (yearDiff * 0.025));
    }
}