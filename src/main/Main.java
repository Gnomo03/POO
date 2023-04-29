public class Main {
    
    public static void main(String[] args) {

        Model m = new Model();
        Controller c = new Controller(m);
        // ----------Carriers-----------------

        Carrier forBag = new Carrier("forBag", 0.013, 0.05, 0.02, 0);
        Carrier forTS = new Carrier("forTS", 0.002, 0.015, 0.035, 0);
        Carrier forSneak = new Carrier("forSneak", 0.0034, 0.062, 0.05, 0);
        m.getCarrierManager().addCarrier(forBag);
        m.getCarrierManager().addCarrier(forTS);
        m.getCarrierManager().addCarrier(forSneak);

        // Load
        c.load();

        // -----------------------------------
        View menu = new View(c);
        menu.mainMenu();
    }
}