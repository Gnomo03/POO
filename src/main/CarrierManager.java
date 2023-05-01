import java.util.TreeMap;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CarrierManager implements Serializable {
    private TreeMap<String, Carrier> carrierMap;

    public CarrierManager() {
        this.carrierMap = new TreeMap<String, Carrier>();
    }

    public Carrier getCarrier(String carrierName) throws NullPointerException {
        if (this.carrierMap.containsKey(carrierName)) {
            return this.carrierMap.get(carrierName);
        }  else {
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
    public void addCarrier(Carrier carrier) throws CarrierAlreadyExistsException{
        if (this.carrierMap.containsKey(carrier.getName()))
            throw new CarrierAlreadyExistsException();

        this.carrierMap.put(carrier.getName(), carrier.clone());
    }


    public List<Carrier> getCarriers() {
        List<Carrier> carriers = new LinkedList<Carrier>();
        for (String key : this.carrierMap.keySet()) {
            Carrier value = this.carrierMap.get(key);
            carriers.add(value.clone());
        }
        return carriers;
    }

    public void removeCarrier(String name) {
        this.carrierMap.remove(name);
    }
}