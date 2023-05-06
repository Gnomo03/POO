package test;
import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;

public class OrderTest{
    @Test
    public void smallOrder(){
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
    public void mediumOrder(){
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
        assertEquals("", 1, order.getSatisfactionPrice(), 0.01);
        order.removeItem(sneak, u1);
        assertEquals("", 0.5, order.getSatisfactionPrice(), 0.01);
        assertEquals("", 51.845, order.calculateFinalPrice(), 0.001);
        order.removeItem(bag, u1);
        assertEquals("", 0.25, order.getSatisfactionPrice(), 0.01);
        assertEquals("", 41.65, order.calculateFinalPrice(), 0.01);
    }

    @Test
    public void bigOrder(){
        var order = new Order();
        var t1 = new Carrier();
        var t2 = new Carrier();
        var pO = new Stack<Integer>();
        var p1 = new Stack<Integer>();
        pO.add(1);

        Bag bag = new Bag(null, null, 10, t1, 5, 
                          null, 1500,"null", null, 0);
        bag.setPriceCorrection(0.7);

        var sneak = new Sneaker(null, null, 20, t2, 1,
                                null, 42, Util.toSneakerType("LACES"), "black",
                                null, 0);
        sneak.setPriceCorrection(.55);
        sneak.setPreviousOwners(pO);

        var camisa1 = new Tshirt("null", "null", 30, t1, 2.5,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);
        camisa1.setPriceCorrection(1.7);

        var camisa2 = new Tshirt("null", "null", 32, t1, 0.7,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Stripes"), 0);
        camisa2.setPriceCorrection(1.7);

        Bag newbag = new Bag(null, null, 15, t2, 3, 
                          null, 300,"null", null, 0);
        newbag.setPriceCorrection(0.7);

        var sneak2 = new Sneaker(null, null, 25, t1, 2,
                                p1, 40, Util.toSneakerType("LACES"), "white",
                                null, 0);
        sneak2.setPriceCorrection(.95);





        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        t1.setTaxBig(0.75);
        t2.setTaxSmall(.15);
        t2.setTaxMedium(.3);
        t2.setTaxBig(0.45);
        //-------------------------------------------------------
        order.addItem(bag,u1);
        order.addItem(sneak,u1);
        order.addItem(camisa1,u1);
        order.addItem(camisa2, u1);
        order.addItem(sneak2, u1);
        order.addItem(newbag, u1);
        assertEquals("", 1.5, bag.getPrice(),  0);
        assertEquals("", 14.5, sneak.getPrice(),  0);
        assertEquals("", 30, camisa1.getPrice(),  0);
        assertEquals("", 16, camisa2.getPrice(), 0);
        assertEquals("", 4.05, newbag.getPrice(), 0.01);
        assertEquals("", 25, sneak2.getPrice(), 0);
        assertEquals("", 91.05, order.getItemPrice(), 0.01);
        //----------------------------------------------------
        assertEquals("", 167.359, order.calculateFinalPrice(), 0.001);
        order.removeItem(sneak, u1);
        assertEquals("", 125.2165, order.calculateFinalPrice(), 0.0001);
    }

}