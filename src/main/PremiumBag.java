import java.time.LocalDate;
import java.time.Period;


public class PremiumBag extends Bag{

    PremiumBag(){
    super();
    }
    @Override
    public double getPrice(){
     LocalDate now = LocalDate.now();
    int yearDiff = Period.between(this.getReleaseDate(), now).getYears();

            return this.getBasePrice() * (1 + (yearDiff * 0.025));
    }
}