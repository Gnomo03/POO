import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

public class CarrierManager {
    private TreeMap<String, Carrier> carrierMap;

    public CarrierManager() {
        this.carrierMap = new TreeMap<String, Carrier>();
    }

    public Carrier getCarrier(String carrierName) {
        if (this.carrierMap.containsKey(carrierName)) {
            return this.carrierMap.get(carrierName);
        } else if (this.carrierMap.containsKey(carrierName)) {
            return this.carrierMap.get(carrierName);
        } else {
            return null;
        }
    }

    /**
     * Returns a specific Carrier
     *
     * @param id of the carrier
     * @return copy of the carrier
     */
    public Carrier searchCarrier(String id) {
        return this.carrierMap.get(id);
    }

    /**
     * Add´s a carrier to the carrier map
     *
     * @param carrier
     */
    public void addCarrier(Carrier carrier) {
        this.carrierMap.put(carrier.getName(), carrier.clone());
    }

    public void updateCarrierProfit(String name) {
        // to be defined
    }

    public List<Carrier> getCarriers() {
        List<Carrier> carriers = new LinkedList<Carrier>();
        for (String key : this.carrierMap.keySet()) {
            Carrier value = this.carrierMap.get(key);
            carriers.add(value.clone());
        }
        return carriers;
    }
}