import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            return this.soldItemsMap.get(id);
        } else if (this.listedItemsMap.containsKey(id)) {
            return this.listedItemsMap.get(id);
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

    public Integer getNewItemId(){
        Integer newId = 0;
        for (Integer i : this.listedItemsMap.keySet()) {
            if( i > newId ){
                newId = i;
            }
        }
        return newId+1;
    }

    public void save( ObjectOutputStream os ){
        try{
            os.writeObject( this.listedItemsMap );
        }
        catch( Exception ex){
            //TODO: Remove
            System.out.println(ex.getMessage());
        }
    }

    public void load( ObjectInputStream is ){
        HashMap<Integer, Item> temp = null;
        try{
            temp =  (HashMap<Integer, Item>) is.readObject();
            this.listedItemsMap = temp;
        }
        catch( Exception ex){
            //TODO: Remove
            System.out.println(ex.getMessage());
        }
    }
}