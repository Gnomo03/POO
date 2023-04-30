import java.time.LocalDate;
import java.time.Period;


public class PremiumSneaker extends Sneaker {

    public PremiumSneaker(){
    super();
    }

   public double getPrice(){
     LocalDate now = LocalDate.now();
    int yearDiff = Period.between(this.getReleaseDate(), now).getYears();

            return this.getBasePrice() * (1 + (yearDiff * 0.025));
    }

}