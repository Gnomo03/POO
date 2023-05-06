package test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;

public class CarrierTest{
    @Test
    public void smallCarrier(){
        var carrier = new Carrier("carrier", 0.25, 0.5, 0.75, 0);
        var bag = new Bag(null, null, 10, carrier, 5, null,
                          1500, null, null, 0);
        bag.setPriceCorrection(.5);
        double final_price = bag.getPrice();
        assertEquals("", 3.5, final_price, 0);
        carrier.updateEarnings(1, final_price);
        assertEquals("", 0.875, carrier.getTotalEarning(), 0);
    }

    @Test
    public void mediumCarrier(){
        var pO = new Stack<Integer>();
        pO.add(1);
        var carrier = new Carrier("carrier", 0.25, 0.5, 0.75, 0);

        var bag = new Bag(null, null, 10, carrier, 5, null,
                          1500, null, null, 0);
        bag.setPriceCorrection(.5);

        var sneak = new Sneaker(null, null, 20, carrier, 1, pO,
                                41, Util.toSneakerType("LACES"), null, null, 0);
        sneak.setPriceCorrection(.3);

        assertEquals("", 3.5, bag.getPrice(), 0);
        assertEquals("", 17, sneak.getPrice(), 0);
        carrier.updateEarnings(2, bag.getPrice());
        carrier.updateEarnings(2, sneak.getPrice());
        assertEquals("", 10.25, carrier.getTotalEarning(), 0);
        carrier.revertProfit(sneak.getPrice(), 2);
        assertEquals("", 0.875, carrier.getTotalEarning(), 0);
    }

    @Test
    public void BigCarrier(){
        var pO = new Stack<Integer>();
        pO.add(1);
        var carrier = new Carrier("carrier", 0.25, 0.5, 0.75, 0);

        var bag = new Bag(null, null, 10, carrier, 5, null,
                          1500, null, LocalDate.of(2021, 1, 8), 0);
        bag.setPriceCorrection(.5);

        var sneak = new Sneaker(null, null, 20, carrier, 1, pO,
                                41, Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);
        sneak.setPriceCorrection(.3);

        var tshirt = new Tshirt(null, null, 30, carrier, 0, null,
                               null, Util.toTshirtPattern("Stripes"), 0);
        tshirt.setPriceCorrection(.25);

        var Pbag = new PremiumBag(bag);

        var Psneak = new PremiumSneaker(sneak);

        var ts = new Tshirt(null, null, 20, carrier, 1, null,
                     null, Util.toTshirtPattern("PalmTrees"), 0);
        ts.setPriceCorrection(.69);

        assertEquals("", 3.5, bag.getPrice(), 0);
        assertEquals("", 17, sneak.getPrice(), 0);
        assertEquals("", 15, tshirt.getPrice(), 0);
        assertEquals("", 10.5, Pbag.getPrice(), 0);
        assertEquals("", 29, Psneak.getPrice(), 0);
        assertEquals("", 20, ts.getPrice(), 0);

        carrier.updateEarnings(6, bag.getPrice());
        carrier.updateEarnings(6, sneak.getPrice());
        carrier.updateEarnings(6, tshirt.getPrice());
        carrier.updateEarnings(6, Pbag.getPrice());
        carrier.updateEarnings(6, Psneak.getPrice());
        carrier.updateEarnings(6, ts.getPrice());
        assertEquals("", 71.25, carrier.getTotalEarning(), 0.01);
        carrier.revertProfit(sneak.getPrice(), 6);
        assertEquals("", 39, carrier.getTotalEarning(), 0);
        carrier.revertProfit(Psneak.getPrice(), 5);
        assertEquals("", 24.5, carrier.getTotalEarning(), 0);
    }
}