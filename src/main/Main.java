import java.util.Scanner;
import javax.sound.midi.ControllerEventListener;

public class Main {
    public static void main(String[] args) {

        Module m = new Module();
        ItemManager iM = new ItemManager();
        UserManager uM = new UserManager();
        Controller c = new Controller(m, uM, iM);
        c.loadData();
        Menu menu = new Menu(c);
        menu.mainMenu();
    }
}