/**
 * Represents an item with a description, brand, base price,
 * price correction, carrier, condition score, previous owners,
 * premium status and ID.
 */
public abstract class Item {
    private String description;
    private String brand;
    private double basePrice;
    private Carrier carrier;
    private double conditionScore;
    private int previousOwners;
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
        this.basePrice = 0;
        this.conditionScore = 0;
        this.previousOwners = 0;
        this.id = currentID;
        currentID++;
        this.userId = 0; // admin id
    }

    /**
     * Constructor for Item class with parameters.
     *
     * @param description    the description of the item
     * @param brand          the brand of the item
     * @param basePrice      the base price of the item
     * @param carrier        the carrier of the item
     * @param conditionScore the condition score of the item
     * @param previousOwners the number of previous owners of the item
     */
    public Item(String description, String brand, double basePrice,
            Carrier carrier, double conditionScore, int previousOwners, int userId) {
        this.description = description;
        this.brand = brand;
        this.basePrice = basePrice;
        this.carrier = carrier;
        this.conditionScore = conditionScore;
        this.previousOwners = previousOwners;
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
        this.basePrice = oneItem.getBasePrice();
        this.carrier = oneItem.getCarrier();
        this.conditionScore = oneItem.getConditionScore();
        this.previousOwners = oneItem.getPreviousOwners();
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
     * Returns the base price of the item.
     *
     * @return the base price of the item
     */
    public double getBasePrice() {
        return this.basePrice;
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
     * Sets the base price of the item.
     *
     * @param basePrice the new base price of the item
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
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

    public abstract String serialize(String delimiter);

    public abstract void deserialize(String delimiter, String line);

    protected String serializeItem(String delimiter) {
        String result = String.join(delimiter,
                this.description,
                this.brand,
                String.valueOf(this.basePrice),
                String.valueOf(this.conditionScore),
                String.valueOf(this.previousOwners),
                String.valueOf(this.id),
                String.valueOf(this.userId),
                carrier.serialize(Consts.DELIM_2)) + "\t";
        return result;
    }

    protected String[] deserializeItem(String[] fields, int startIndex) {
        this.description = fields[startIndex];
        this.brand = fields[startIndex + 1];
        this.basePrice = Util.ToDouble(fields[startIndex + 3]);
        this.conditionScore = Util.ToDouble(fields[startIndex + 5]);
        this.previousOwners = Util.ToInteger(fields[startIndex + 6]);
        this.id = Util.ToInteger(fields[startIndex + 8]);
        this.userId = Util.ToInteger(fields[startIndex + 9]);
        // Carrier
        carrier = new Carrier();
        carrier.deserialize(Consts.DELIM_2, fields[startIndex + 10]);
        //
        int arraySz = fields.length - (startIndex + 11);
        String[] remainder = new String[arraySz];
        System.arraycopy(fields, startIndex + 11, remainder, 0, arraySz);

        return remainder;
    }

    // Deserialize any kind of item
    public static Item deserializeItem(String delimiter, String line) {
        Item item = null;

        String[] fields = Util.Split(delimiter, line);
        String type = fields[0];
        switch (type) {
            case "b":
                Bag b = new Bag();
                b.deserialize(delimiter, line);
                item = b;
                break;

            case "t":
                Tshirt t = new Tshirt();
                t.deserialize(delimiter, line);
                item = t;
                break;

            case "s":
                Sneaker s = new Sneaker();
                s.deserialize(delimiter, line);
                item = s;
                break;
        }

        return item;
    }
}