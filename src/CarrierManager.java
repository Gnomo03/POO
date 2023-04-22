import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class CarrierManager extends Module {

    /**
     * Returns a specific Carrier
     *
     * @param id of the carrier
     * @return copy of the carrier
     */
    public Carrier searchCarrier(String id) {

        return this.getCarrierMap().get(id).clone();
    }

    /**
     * Add´s a carrier to the carrier map
     *
     * @param carrier
     */
    public void addCarrier(Carrier carrier) {
        this.getCarrierMap().put(carrier.getName(), carrier.clone());
    }

    public void updateCarrierProfit(String name) {
        // to be defined
    }
}
