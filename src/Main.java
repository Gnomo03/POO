//import Tshirt.TshirtPattern;;

public class Main {
    public static void main(String[] args){
        Item bag = new Bag("Saco Berlin", "Urso", "B0001", 5.0, 
                            0, null, 5, 0, false,
                            12.0, "Cotton", 2014);

        Item tshirt = new Tshirt("Tshirt Berlin", "Urso", "B0002", 10.0, 
                            0, null, 4, 0, false, 
                            Tshirt.TshirtSize.M, Tshirt.TshirtPattern.Smooth);
                    
        Item sneaker = new Sneaker("Sneaker Berlin", "Urso", "B0002", 12.0, 
                            0, null, 7, 2, false, 
                            42, Sneaker.SneakerType.LACES, "Red", 2022);

        Order order = new Order();
        order.addItem(bag);
        order.addItem(tshirt);

        System.out.format(order.toString());

        System.out.format("\n");

        order.removeItem(bag);
        order.addItem(sneaker);

        System.out.format(order.toString());
    }
}
