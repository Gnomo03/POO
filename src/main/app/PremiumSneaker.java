package app;
import java.time.LocalDate;
import java.time.Period;
import java.util.Stack;


public class PremiumSneaker extends Sneaker {

    public PremiumSneaker(){
    super();
    }
    public PremiumSneaker(String description, String brand, double basePrice,
            Carrier carrier, double conditionScore, Stack<Integer> previousOwners, double size,
            SneakerType type, String color, LocalDate releaseDate, int userId) {
         super(description,brand,basePrice,
         carrier,conditionScore,previousOwners, size,
          type,color, releaseDate, userId);


    }

    public PremiumSneaker(Sneaker oneSneaker) {
        super(oneSneaker);
    }

    @Override
    public PremiumSneaker clone(){

      return new PremiumSneaker(this);
    }

   @Override
   public double getPrice(){
     LocalDate now = LocalDate.now();
    int yearDiff = Period.between(this.getReleaseDate(), now).getYears();

            return this.getBasePrice() * (1 + (yearDiff * 0.025));
    }

}