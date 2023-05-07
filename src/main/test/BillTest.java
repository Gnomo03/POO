package test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;

public class BillTest{
    @Test
    public void smallBillBought(){
        var order = new Order();
        var hm = new HashMap<Integer,Item>();
        var t1 = new Carrier();
        t1.setTaxSmall(.25);
        Bag bag = new Bag("mala", "null", 10, t1, 5, 
                          null, 1500,"null", null, 0);
        bag.setPriceCorrection(0.7);
        var u1 = new User("test", "test", "t", 1, "test");
        order.addItem(bag,u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setBought();

        bill.addItem(bag, 1);
        bill.calculateTotalCostItems();

        assertEquals("", 3.8, bill.getportsTax(), 0);
        assertEquals("", 1.5, bill.gettotalCost(), 0.02);
        assertEquals("", 5.3, bill.getAmount(), 0);
    }

    @Test
    public void mediumBillSameCarriersBought(){
        var hm = new HashMap<Integer,Item>();
        var order = new Order();
        var t1 = new Carrier();

        Bag bag = new Bag(null, null, 10, t1, 5, 
                          null, 1500,"null", null, 0);
        bag.setPriceCorrection(0.7);

        var camisa = new Tshirt("null", "null", 30, t1, 2.5,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);
        camisa.setPriceCorrection(1.7);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        order.addItem(bag,u1);
        order.addItem(camisa,u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setBought();

        bill.addItem(bag, 2);
        bill.addItem(camisa, 2);
        bill.calculateTotalCostItems();

        assertEquals("", 25.2, bill.getportsTax(), 0);
        assertEquals("", 31.5, bill.gettotalCost(), 0.02);
        assertEquals("", 56.7, bill.getAmount(), 0);
        
        bill.removeItem(camisa, 2);

        assertEquals("", 3.8, bill.getportsTax(), 0.02);
        assertEquals("", 1.5, bill.gettotalCost(), 0.02);
        assertEquals("", 5.3, bill.getAmount(), 0.02);
    }

    @Test
    public void mediumBillDiffCarriersBought(){
        var hm = new HashMap<Integer,Item>();
        var order = new Order();
        var t1 = new Carrier();
        var t2 = new Carrier();

        Bag bag = new Bag(null, null, 10, t1, 5, 
                          null, 1500,"null", null, 0);
        bag.setPriceCorrection(0.7);

        var camisa = new Tshirt("null", "null", 30, t2, 2.5,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);
        camisa.setPriceCorrection(1.7);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t2.setTaxSmall(.5);
        order.addItem(bag,u1);
        order.addItem(camisa,u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setBought();

        bill.addItem(bag, 1);
        bill.addItem(camisa, 1);
        bill.calculateTotalCostItems();

        assertEquals("", 22.7, bill.getportsTax(), 0.02);
        assertEquals("", 31.5, bill.gettotalCost(), 0.02);
        assertEquals("", 54.2, bill.getAmount(), 0.02);
    }

    @Test
    public void BigBillBought(){
        var hm = new HashMap<Integer,Item>();
        var order = new Order();
        var t1 = new Carrier();
        var pO = new Stack<Integer>();
        pO.add(1);

        var bag = new Bag(null, null, 10, t1, 5, null,
                          1500, null, LocalDate.of(2021, 1, 8), 0);
        bag.setPriceCorrection(.5);

        var sneak = new Sneaker(null, null, 20, t1, 1, pO,
                                41, Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);
        sneak.setPriceCorrection(.3);

        var camisa = new Tshirt(null, null, 30, t1, 0, null,
                               null, Util.toTshirtPattern("Stripes"), 0);
        camisa.setPriceCorrection(.25);

        var Pbag = new PremiumBag(null, null, 10, t1, 5, null,
                                  1500, null, LocalDate.of(2021, 1, 8), 0);

        var Psneak = new PremiumSneaker(null, null, 20, t1, 1, pO, 41,
                                        Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);

        var ts = new Tshirt(null, null, 20, t1, 1, null,
                            null, Util.toTshirtPattern("PalmTrees"), 0);
        ts.setPriceCorrection(.69);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        t1.setTaxBig(.75);

        order.addItem(bag,u1);
        order.addItem(camisa,u1);
        order.addItem(camisa,u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setBought();

        bill.addItem(bag, 6);
        bill.addItem(camisa, 6);
        bill.addItem(sneak, 6);
        bill.addItem(Pbag, 6);
        bill.addItem(Psneak, 6);
        bill.addItem(ts, 6);
        bill.calculateTotalCostItems();

        assertEquals("", 3.5, bag.getPrice(), 0);
        assertEquals("", 15, camisa.getPrice(), 0);
        assertEquals("", 17, sneak.getPrice(), 0);
        assertEquals("", 10.5, Pbag.getPrice(),  0);
        assertEquals("", 29, Psneak.getPrice(),  0);
        assertEquals("", 20, ts.getPrice(), 0);

        assertEquals("", 96.8, bill.getportsTax(), 0.1);
        assertEquals("", 95, bill.gettotalCost(), 0.01);
        assertEquals("", 191.8, bill.getAmount(), 0);
        
        bill.removeItem(camisa, 6);

        assertEquals("", 50.4, bill.getportsTax(), 0.02);
        assertEquals("", 80, bill.gettotalCost(), 0.02);
        assertEquals("", 130.4, bill.getAmount(), 0.02);

        bill.removeItem(Psneak, 5);
        bill.removeItem(Pbag, 4);

        assertEquals("", 31.5, bill.getportsTax(), 0.02);
        assertEquals("", 40.5, bill.gettotalCost(), 0.02);
        assertEquals("", 72, bill.getAmount(), 0.02);
    }

    @Test
    public void getAmountSmallSold(){
        var order = new Order();
        var hm = new HashMap<Integer,Item>();
        var t1 = new Carrier();
        t1.setTaxSmall(.25);
        Bag bag = new Bag("mala", "null", 10, t1, 5, 
                          null, 1500,"null", null, 0);
        bag.setPriceCorrection(0.7);
        var u1 = new User("test", "test", "t", 1, "test");
        order.addItem(bag,u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setSold();

        bill.addItem(bag, 1);
        bill.calculateTotalCostItems();

        assertEquals("", 0, bill.getportsTax(), 0);
        assertEquals("", 1.5, bill.gettotalCost(), 0.02);
        assertEquals("", 1.482, bill.getAmount(), 0.001);
    }
    
    @Test
    public void getAmountMediumSold(){
        var hm = new HashMap<Integer,Item>();
        var order = new Order();
        var t1 = new Carrier();

        Bag bag = new Bag(null, null, 10, t1, 5, 
                          null, 1500,"null", null, 0);
        bag.setPriceCorrection(0.7);

        var camisa = new Tshirt("null", "null", 30, t1, 2.5,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);
        camisa.setPriceCorrection(1.7);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        order.addItem(bag,u1);
        order.addItem(camisa,u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setSold();

        bill.addItem(bag, 2);
        bill.addItem(camisa, 2);
        bill.calculateTotalCostItems();

        assertEquals("", 0, bill.getportsTax(), 0);
        assertEquals("", 31.5, bill.gettotalCost(), 0.02);
        assertEquals("", 31.122, bill.getAmount(), 0.001);
        
        bill.removeItem(camisa, 2);

        assertEquals("", 0, bill.getportsTax(), 0.02);
        assertEquals("", 1.5, bill.gettotalCost(), 0.02);
        assertEquals("", 1.482, bill.getAmount(), 0.001);
    } 

    @Test
    public void getAmountBigSold(){
        var hm = new HashMap<Integer,Item>();
        var order = new Order();
        var t1 = new Carrier();
        var pO = new Stack<Integer>();
        pO.add(1);

        var bag = new Bag(null, null, 10, t1, 5, null,
                          1500, null, LocalDate.of(2021, 1, 8), 0);
        bag.setPriceCorrection(.5);

        var sneak = new Sneaker(null, null, 20, t1, 1, pO,
                                41, Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);
        sneak.setPriceCorrection(.3);

        var camisa = new Tshirt(null, null, 30, t1, 0, null,
                               null, Util.toTshirtPattern("Stripes"), 0);
        camisa.setPriceCorrection(.25);

        var Pbag = new PremiumBag(null, null, 10, t1, 5, null,
                                  1500, null, LocalDate.of(2021, 1, 8), 0);

        var Psneak = new PremiumSneaker(null, null, 20, t1, 1, pO, 41,
                                        Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);

        var ts = new Tshirt(null, null, 20, t1, 1, null,
                            null, Util.toTshirtPattern("PalmTrees"), 0);
        ts.setPriceCorrection(.69);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        t1.setTaxBig(.75);

        order.addItem(bag,u1);
        order.addItem(camisa,u1);
        order.addItem(camisa,u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setSold();

        bill.addItem(bag, 6);
        bill.addItem(camisa, 6);
        bill.addItem(sneak, 6);
        bill.addItem(Pbag, 6);
        bill.addItem(Psneak, 6);
        bill.addItem(ts, 6);
        bill.calculateTotalCostItems();

        assertEquals("", 0, bill.getportsTax(), 0.1);
        assertEquals("", 95, bill.gettotalCost(), 0.01);
        assertEquals("", 93.86, bill.getAmount(), 0.01);
        
        bill.removeItem(camisa, 6);

        assertEquals("", 0, bill.getportsTax(), 0.02);
        assertEquals("", 80, bill.gettotalCost(), 0.02);
        assertEquals("", 79.04, bill.getAmount(), 0.02);

        bill.removeItem(Psneak, 5);
        bill.removeItem(Pbag, 4);

        assertEquals("", 0, bill.getportsTax(), 0.02);
        assertEquals("", 40.5, bill.gettotalCost(), 0.02);
        assertEquals("", 40.014, bill.getAmount(), 0.001);
    }

}