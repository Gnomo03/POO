package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import app.*;

public class PointerTest{
    @Test
    public void userManager() {
        int res = 0;

        var bills = new HashMap<Integer,Bill>();
        var sysI = new ArrayList<Item>();
        var sell = new ArrayList<Item>();

        var lista = new UserManager();
        var user1 = new User("a", "a", "a", 0, bills, "a", sysI, sell);
        var user2 = new User("b", "b", "b", 1, bills, "b", sysI, sell);
        var user3 = new User("c", "c", "c", 2, bills, "c", sysI, sell);
        lista.addUser(user1);
        lista.addUser(user2);
        lista.removeUser(user2.getId());
        lista.addUser(user3);

        if (lista.getUser(1).getName().equals("a")) res = 1;

        assertEquals(null, res, 1, 0);
    }
} 