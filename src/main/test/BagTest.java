package test;
import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;
/* 
import Bag;
import Carrier;
import Order;
import Sneaker;
import Tshirt;
import User;
import Tshirt.TshirtPattern;
*/

public class BagTest{
    @Test
    public void bagPrice(){
        var bag = new Bag();
        bag.setBasePrice(10);
        bag.setDimension(1500);
        bag.setPriceCorrection(0.7);
        assertEquals("", 1.5, bag.getPrice(),  0);
    }

    @Test
    public void tshirtPriceSmooth(){
        var tshirt = new Tshirt();
        tshirt.setBasePrice(15);
        tshirt.setPattern(Tshirt.TshirtPattern.Smooth);
        assertEquals("", 15, tshirt.getPrice(),0);
    }
    
    @Test
    public void tshirtPriceNotSmooth(){
        var tshirt = new Tshirt();
        tshirt.setBasePrice(15);
        tshirt.setPattern(Tshirt.TshirtPattern.Stripes);
        assertEquals("", 7.5, tshirt.getPrice(),0);
        tshirt.setPattern(Tshirt.TshirtPattern.PalmTrees);
        assertEquals("", 7.5, tshirt.getPrice(),0);
    }

    @Test
    public void sneakerPrice(){
        var sneaker = new Sneaker();
        var pO = new Stack<Integer>();
        pO.add(1);
        sneaker.setPreviousOwners(pO);
        sneaker.setBasePrice(20);
        sneaker.setPriceCorrection(.7);
        assertEquals("", 13, sneaker.getPrice(), 0);
    }

    @Test
    public void itemID(){
        var mala1 = new Bag();
        var tilha1 = new Sneaker();
        assertEquals("", 1, mala1.getID(), 0);
        assertEquals("", 2, tilha1.getID(), 0);
    }

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

}