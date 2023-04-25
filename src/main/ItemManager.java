import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ItemManager {
    private HashMap<Integer, Item> soldItemsMap;
    private HashMap<Integer, Item> listedItemsMap;
    

    public ItemManager() {
        this.soldItemsMap = new HashMap<Integer, Item>();
        this.listedItemsMap = new HashMap<Integer, Item>();
        
    }

    public Item getItem(int id) {
        if (this.soldItemsMap.containsKey(id)) {
            return this.soldItemsMap.get(id).clone();
        } else if (this.listedItemsMap.containsKey(id)) {
            return this.listedItemsMap.get(id).clone();
        } else {
            return null;
        }
    }

    public void addSoldItem(Item item) {
        this.soldItemsMap.put(item.getID(), item);
    }

    public void addListedItem(Item item) {
        this.listedItemsMap.put(item.getID(), item.clone());
    }

    public Item removeListedItem(int id) {
        return this.listedItemsMap.remove(id);
    }

    public Item removeSoldItem(int id) {
        return this.soldItemsMap.remove(id).clone();
    }

    public void updateItem(int id) {
        if (this.listedItemsMap.containsKey(id)) {
            Item item = removeListedItem(id);
            addSoldItem(item);
        }
    }

    public List<Item> getSoldItems() {
        List<Item> items = new LinkedList<Item>();
        for (Integer key : this.soldItemsMap.keySet()) {
            Item value = this.soldItemsMap.get(key);
            items.add(value.clone());
        }
        return items;
    }

    public List<Item> getListedItems() {
        List<Item> items = new LinkedList<Item>();
        for (Integer key : this.listedItemsMap.keySet()) {
            Item value = this.listedItemsMap.get(key);
            items.add(value.clone());
        }
        return items;
    }

    /**
     * Searches for an item with the specified ID.
     *
     * @param id the ID of the item to search for
     * @return the item with the specified ID, or null if it is not found
     */
    public Item searchItem(int id) {
        Item item = this.getItem(id);
        if (item != null) {
            return item;
        }
        return null;
    }

    public String serialize(){
        String result = "";
        for ( Item i : listedItemsMap.values()) {
            result += i.serialize(Consts.DELIM_1) + "\n";
        }
        return result;
    }

    public String deserialize( List<String> Lines ){
        String result = "";

        this.listedItemsMap.clear();
        for (String line : Lines) {
            Item u = Item.deserializeItem(Consts.DELIM_1, line);
            this.addListedItem(u);
        }
        return result;
    }
}