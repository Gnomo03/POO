public class PremiumCarrier extends Carrier implements Premium{

    public PremiumCarrier(String name, double taxSmall, double taxMedium, double taxBig, int profit) {

        super(name,taxSmall,taxMedium,taxBig,profit);
    }
    public PremiumCarrier(PremiumCarrier oneCarrier) {

        super(oneCarrier);
    }
    public PremiumCarrier() {

        super();
    }
    @Override
    public PremiumCarrier clone(){

        return new PremiumCarrier(this);
    }

    @Override
    public String toString() {
        
        return "Carrier{" +
                "name='" + getName() + '\'' +
                ", Small tax value='" + getTaxSmall() + '\'' +
                ", Medium tax value='" + getTaxMedium() + '\'' +
                ", Big tax value=" + getTaxBig() +
                ", Total Earning=" + getTotalEarning() +
                ", Premium Status }";
    }


}
