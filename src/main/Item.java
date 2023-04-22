/**
 * Represents an item with a description, brand, reference, base price,
 * price correction, carrier, condition score, previous owners,
 * premium status and ID.
 */
public abstract class Item {
    private String description;
    private String brand;
    private String reference;
    private double basePrice;
    private double priceCorrection;
    private Carrier carrier;
    private double conditionScore;
    private int previousOwners;
    private boolean premiumStat;
    private int id;
    private int userId;

    private static int currentID = 0;

    /**
     * Default constructor for Item class.
     * Initializes all fields to default values.
     */
    public Item() {
        this.description = "n/d";
        this.brand = "n/d";
        this.reference = "n/d";
        this.basePrice = 0;
        this.priceCorrection = 0;
        this.conditionScore = 0;
        this.previousOwners = 0;
        this.premiumStat = false;
        this.id = currentID;
        currentID++;
        this.userId = 0; // admin id
    }

    /**
     * Constructor for Item class with parameters.
     *
     * @param description     the description of the item
     * @param brand           the brand of the item
     * @param reference       the reference of the item
     * @param basePrice       the base price of the item
     * @param priceCorrection the price correction of the item
     * @param carrier         the carrier of the item
     * @param conditionScore  the condition score of the item
     * @param previousOwners  the number of previous owners of the item
     * @param premiumStat     whether or not the item has premium status
     */
    public Item(String description, String brand, String reference, double basePrice, double priceCorrection,
            Carrier carrier, double conditionScore, int previousOwners, boolean premiumStat, int userId) {
        this.description = description;
        this.brand = brand;
        this.reference = reference;
        this.basePrice = basePrice;
        this.priceCorrection = priceCorrection;
        this.carrier = carrier;
        this.conditionScore = conditionScore;
        this.previousOwners = previousOwners;
        this.premiumStat = premiumStat;
        this.id = currentID;
        currentID++;
        this.userId = userId;
    }

    /**
     * Copy constructor for Item class.
     *
     * @param oneItem an instance of Item to copy from
     */
    public Item(Item oneItem) {
        this.description = oneItem.getDescription();
        this.brand = oneItem.getBrand();
        this.reference = oneItem.getReference();
        this.basePrice = oneItem.getBasePrice();
        this.priceCorrection = oneItem.getPriceCorrection();
        this.carrier = oneItem.getCarrier();
        this.conditionScore = oneItem.getConditionScore();
        this.previousOwners = oneItem.getPreviousOwners();
        this.premiumStat = oneItem.isPremium();
        this.id = oneItem.getID();
        currentID++; // Acho que isto está a mais
        this.userId = oneItem.getUserId();
    }

    /**
     * Returns the id of the user listing the item.
     *
     * @return the id of the user listing the item.
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Returns the description of the item.
     *
     * @return the description of the item
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the brand of the item.
     *
     * @return the brand of the item
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Returns the reference of the item.
     *
     * @return the reference of the item
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * Returns the base price of the item.
     *
     * @return the base price of the item
     */
    public double getBasePrice() {
        return this.basePrice;
    }

    /**
     * Returns the price correction of the item.
     *
     * @return the price correction of the item
     */
    public double getPriceCorrection() {
        return this.priceCorrection;
    }

    /**
     * Returns the final price of the item.
     *
     * @return the final price of the item
     */
    public abstract double getPrice();

    /**
     * Returns the carrier of the item.
     *
     * @return the carrier of the item
     */
    public Carrier getCarrier() {
        return this.carrier;
    }

    /**
     * Returns the condition score of the item.
     *
     * @return the condition score of the item
     */
    public double getConditionScore() {
        return this.conditionScore;
    }

    /**
     * Returns the number of previous owners of the item.
     *
     * @return the number of previous owners of the item
     */
    public int getPreviousOwners() {
        return this.previousOwners;
    }

    /**
     * Returns the ID of the item.
     *
     * @return the ID of the item
     */
    public int getID() {
        return this.id;
    }

    /**
     * Returns whether or not the item has premium status.
     *
     * @return true if the item has premium status, false otherwise
     */
    public boolean isPremium() {
        return this.premiumStat;
    }

    /**
     * Sets the description of the item.
     *
     * @param description the new description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description the new description of the item
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the brand of the item.
     *
     * @param brand the new brand of the item
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Sets the reference of the item.
     *
     * @param reference the new reference of the item
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Sets the base price of the item.
     *
     * @param basePrice the new base price of the item
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Sets the price correction of the item.
     *
     * @param priceCorrection the new price correction of the item
     */
    public void setPriceCorrection(double priceCorrection) {
        this.priceCorrection = priceCorrection;
    }

    /**
     * Sets the carrier of the item.
     *
     * @param carrier the new carrier of the item
     */
    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    /**
     * Sets the condition score of the item.
     *
     * @param conditionScore the new condition score of the item
     */
    public void setConditionScore(double conditionScore) {
        this.conditionScore = conditionScore;
    }

    /**
     * Sets the number of previous owners of the item.
     *
     * @param previousOwners the new number of previous owners of the item
     */
    public void setPreviousOwners(int previousOwners) {
        this.previousOwners = previousOwners;
    }

    /**
     * Returns a string representation of the item.
     *
     * @return a string representation of the item
     */
    public abstract String toString();

    /**
     * This method checks if the object o is equal to the current object.
     * 
     * @param o the object to compare with the current object
     * @return true if the objects are equal and false otherwise
     */
    public abstract boolean equals(Object o);

    /**
     * This method creates and returns a copy of the current object.
     * 
     * @return a copy of the current object
     */
    public abstract Item clone();

}