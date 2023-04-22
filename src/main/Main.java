package main;

import java.util.List;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        
        Module m = new Module();
        Carrier c = new Carrier("UPS",0.01,0.02,0.03,0);
        m.registerUser("mail@gmail.com", "Joe Doe", "Ny", 12345678, "qwerty1234");
        m.registerUser("mail2@gmail.com", "Marry Jane", "Ny", 12345678, "qwerty1234");
        m.addCarrier(c);

        Item i = new Tshirt();

        Item i2 = new Sneaker();

        Item i3 = new Bag("mochila de merda", "nike", "as", 15, 5, 
                        c, 7, 0, false, 10, "seilá", 
                        2015, 1);

        Order o = new Order();
        o.addItem(i3);
        
        Boolean b = m.userRegistsItem("mail@gmail.com",i,"UPS");
        List<Integer> l = new LinkedList<Integer>();
        l.add(i.getID());
        m.makeOrder(m.findUserByEmail("mail2@gmail.com").getId(),l);
        System.out.println(m);
    }
}
