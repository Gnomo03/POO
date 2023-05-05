package test;
import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;

public class OrderTest{
    @Test
    public void smallorder(){
        var order = new Order();
        var t1 = new Carrier();
        Bag bag = new Bag("mala", "null", 10, t1, 5, 
                          null, 1500,"null", null, 0);
        bag.setPriceCorrection(0.7);
        var u1 = new User("test", "test", "t", 1, "test");
        order.addItem(bag,u1);
        t1.setTaxSmall(.25);
        assertEquals("", 1.5, bag.getPrice(),  0);
        assertEquals("", 0.38, t1.getTaxSmallWithIva(), 0);
        assertEquals("", 2.32, order.calculateFinalPrice(), 0.02);
    }

    @Test
    public void mediumorder(){
        var order = new Order();
        var t1 = new Carrier();
        var t2 = new Carrier();
        var pO = new Stack<Integer>();
        pO.add(1);

        Bag bag = new Bag(null, null, 10, t1, 5, 
                          null, 1500,"null", null, 0);
        bag.setPriceCorrection(0.7);

        var sneak = new Sneaker(null, null, 20, t2, 1,
                                null, 42, Util.toSneakerType("LACES"), "black",
                                null, 0);
        sneak.setPriceCorrection(.55);
        sneak.setPreviousOwners(pO);

        var camisa = new Tshirt("null", "null", 30, t1, 2.5,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);
        camisa.setPriceCorrection(1.7);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        t2.setTaxSmall(.15);
        t2.setTaxMedium(.3);
        order.addItem(bag,u1);
        order.addItem(sneak,u1);
        order.addItem(camisa,u1);
        assertEquals("", 1.5, bag.getPrice(),  0);
        assertEquals("", 14.5, sneak.getPrice(),  0);
        assertEquals("", 30, camisa.getPrice(),  0);
        assertEquals("", 46, order.getItemPrice(), 0);
        //----------------------------------------------------
        assertEquals("", 0.945, bag.getPrice() * bag.getCarrier().getTaxMediumWithIva(), 0.01);
        assertEquals("", 6.235, sneak.getPrice() * sneak.getCarrier().getTaxMediumWithIva(), 0.01);
        assertEquals("", 18.9, camisa.getPrice() * camisa.getCarrier().getTaxMediumWithIva(), 0.01);
        //----------------------------------------------------
        assertEquals("", 73.08, order.calculateFinalPrice(), 0.01);
    }
}