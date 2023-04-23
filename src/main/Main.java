import java.util.Scanner;
import javax.sound.midi.ControllerEventListener;

public class Main {
    public static void main(String[] args) {

        Module m = new Module();
        Controller c = new Controller(m);
        c.loadData();
        Menu menu = new Menu(c);
        menu.mainMenu();
    }
}