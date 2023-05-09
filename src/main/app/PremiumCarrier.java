package app;
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
    @Override
    public String showCarrier() {

        StringBuilder sb = new StringBuilder();
        int boxWidth = 30;

        // Create the top border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        // Append the student information
        sb.append("|" + Util.formatCell("Name: " + getName(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Small tax value: " + getTaxSmall(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Medium tax value: " + getTaxMedium(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Big tax value: " + getTaxBig(), boxWidth) + "|\n");
        String Iva = String.format("%.2f", getIva());
        sb.append("|" + Util.formatCell("Iva: " + Iva, boxWidth) + "|\n");
        // Create the bottom border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        return sb.toString();
    }


}
