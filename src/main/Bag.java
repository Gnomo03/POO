import java.time.LocalDate;

/**
 * Represents a Bag item that extends the Item class.
 * It has instance variables such as dimension, material and releaseDate.
 * The class has constructors, getters, and setters for its instance
 * variables.
 */
public class Bag extends Item {
    private double dimension;
    private String material;
    private LocalDate releaseDate;

    /**
     * Default constructor for the Bag class.
     */
    public Bag() {
        super();
        this.dimension = 0;
        this.material = "n/d";
        this.releaseDate = null;
    }

    /**
     * Constructor for the Bag class with all parameters.
     *
     * @param description     The description of the bag.
     * @param brand           The brand of the bag.
     * @param reference       The reference of the bag.
     * @param basePrice       The base price of the bag.
     * @param priceCorrection The price correction of the bag.
     * @param carrier         The carrier of the bag.
     * @param conditionScore  The condition score of the bag.
     * @param previousOwners  The number of previous owners of the bag.
     * @param premiumStat     Whether or not the bag is premium status.
     * @param dimension       The dimension of the bag.
     * @param material        The material of the bag.
     * @param releaseDate     The release date of the bag.
     */
    public Bag(String description, String brand, String reference, double basePrice, double priceCorrection,
            Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat, double dimension,
            String material, LocalDate releaseDate, int userId) {
        super(description, brand, reference, basePrice, priceCorrection, carrier, conditionScore, previousOwners,
                premiumStat, userId);
        this.dimension = dimension;
        this.material = material;
        this.releaseDate = releaseDate;
    }

    /**
     * Copy constructor for the Bag class.
     * 
     * @param oneBag The Bag object to be copied.
     */
    public Bag(Bag oneBag) {
        super(oneBag);
        this.dimension = oneBag.getDimension();
        this.material = oneBag.getMaterial();
        this.releaseDate = oneBag.getReleaseDate();
    }

    /**
     * Returns the dimension of the bag.
     * 
     * @return The dimension of the bag.
     */
    public double getDimension() {
        return this.dimension;
    }

    /**
     * Returns the material of the bag.
     * 
     * @return The material of the bag.
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * Returns the release date of the bag.
     * 
     * @return The release date of the bag.
     */
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    /**
     * Returns the price of the bag based on its value (premium or not) and on the
     * release date.
     * 
     * @return The price of the bag.
     */
    public double getPrice() {
        if (this.isPremium())
            return (10 + (2023 - this.releaseDate.getYear())) / 10 * this.getBasePrice();
        else {
            return (this.getBasePrice() / this.dimension);
        }
    }

    /**
     * Sets the dimension of the bag.
     * 
     * @param dimension The dimension to set for the bag.
     */
    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    /**
     * Sets the material of the bag.
     * 
     * @param material The material to set for the bag.
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Sets the release date of the bag.
     * 
     * @param releaseDate The release date to set for the bag.
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Returns a string representation of the Bag.
     *
     * @return a string representation of the Bag
     */
    public String toString() {
        return "Bag{" +
                "id=" + getID() + '\'' +
                "description='" + getDescription() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", reference='" + getReference() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", priceCorrection=" + getPriceCorrection() +
                ", carrier='" + getCarrier() + '\'' +
                ", conditionScore=" + getConditionScore() +
                ", previousOwners=" + getPreviousOwners() +
                ", premiumStat=" + isPremium() +
                ", dimension=" + this.dimension +
                ", material=" + this.material +
                ", releaseDate=" + this.releaseDate +
                '}';
    }

    /**
     * This method checks if the object o is equal to the current object.
     * 
     * @param o the object to compare with the current object
     * @return true if the objects are equal and false otherwise
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        Bag s = (Bag) o;
        return this.getDescription().equals(s.getDescription()) && this.getBrand().equals(s.getBrand())
                && this.getReference().equals(s.getReference()) && this.getBasePrice() == s.getBasePrice()
                && this.getPriceCorrection() == s.getPriceCorrection() && this.getCarrier().equals(s.getCarrier())
                && this.getConditionScore() == s.getConditionScore()
                && this.getPreviousOwners() == s.getPreviousOwners() && this.isPremium() == (s.isPremium())
                && this.dimension == s.getDimension() && this.material.equals(s.getMaterial())
                && this.releaseDate == s.getReleaseDate();
    }

    /**
     * This method creates and returns a copy of the current object.
     * 
     * @return a copy of the current object
     */
    public Bag clone() {
        return new Bag(this);
    }

    public String serialize(String delimiter) {
        String result = String.join(delimiter, "b", serializeItem(delimiter));
        result += String.join(delimiter, String.valueOf(this.dimension),
                this.material,
                String.valueOf(this.releaseDate));

        return result;
    }

    public void deserialize(String delimiter, String line) {
        String[] fields = Util.Split(delimiter, line);

        String type = fields[0];
        if (type.equals("b")) {
            String[] bag = deserializeItem(fields, 1);

            this.dimension = Util.ToDouble(bag[0]);
            this.material = bag[1];
            this.releaseDate = Util.ToDate(bag[2]);
        } else {
            // tipo errado!!!!!!
        }
    }
}