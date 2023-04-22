import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class ItemManager extends Module {

    /**
     * Searches for an item with the specified ID.
     *
     * @param id the ID of the item to search for
     * @return the item with the specified ID, or null if it is not found
     */
    public Item searchItem(int id) {

        if (this.getSoldItemsMap().containsKey(id)) {

            Item item = this.getSoldItemsMap().get(id).clone();
            return item;

        }
        if (this.getListedItemsMap().containsKey(id)) {

            Item item = this.getListedItemsMap().get(id).clone();
            return item;

        }
        return null;
    }

    /**
     * Adds an item to the sold items map.
     *
     * @param item the item to add to the sold items map
     */
    private void addSoldItem(Item item) {

        this.getSoldItemsMap().put(item.getID(), item.clone());
    }

    /**
     * Adds an item to the listed items map.
     *
     * @param item the item to add to the listed items map
     */
    public void addListedItem(Item item) {

        this.getListedItemsMap().put(item.getID(), item.clone());
    }

    /**
     * Removes an item from the listed items map.
     *
     * @param id the ID of the item to remove from the listed items map
     * @return the removed item
     */
    private Item removeListedItem(int id) {

        return this.getListedItemsMap().remove(id);
    }

    /**
     * Removes an item from the sold items map.
     *
     * @param id the ID of the item to remove from the sold items map
     * @return the removed item
     */
    public Item removeSoldItem(int id) {
        return this.getSoldItemsMap().remove(id).clone();
    }

    /**
     * Updates the item with the specified ID from the listed items map to the sold
     * items map,
     * and updates the vintage profit.
     *
     * @param id the ID of the item to update
     */
    public void updateItem(int id) {

        if (this.getListedItemsMap().containsKey(id)) {

            double vintageProfit = this.getVintageProfit();
            Item item = this.removeListedItem(id);
            vintageProfit += item.getPrice() * 0.08;
            this.addSoldItem(item);
            // updateCarrierProfit
        }
    }

    /**
     * Returns a list of all items in the sold items map.
     *
     * @return a list of all items in the sold items map
     */
    public List<Item> soldItemsList() {

        List<Item> items = new LinkedList<Item>();

        for (Integer key : this.getSoldItemsMap().keySet()) {
            Item value = this.getSoldItemsMap().get(key);
            items.add(value.clone());
        }
        return items;
    }

    /**
     * Returns a list of all items currently listed .
     *
     * @return a list of all items currently listed
     */
    public List<Item> listedItemsList() {

        List<Item> items = new LinkedList<Item>();

        for (Integer key : this.getListedItemsMap().keySet()) {
            Item value = this.getListedItemsMap().get(key);
            items.add(value.clone());
        }
        return items;
    }

}
