public class Main {
    
    public static void main(String[] args) {

        Model m = new Model();
        Controller c = new Controller(m);


        // ----------Carriers-----------------

        Carrier forBag = new Carrier("Ups", 0.15, 0.13, 0.10, 0);
        Carrier forTS = new Carrier("CTT", 0.12, 0.15, 0.20, 0);
        Carrier forSneak = new Carrier("Dhl", 0.11, 0.3, 0.25, 0);
        m.getCarrierManager().addCarrier(forBag);
        m.getCarrierManager().addCarrier(forTS);
        m.getCarrierManager().addCarrier(forSneak);

        // -----------------------------------

        View menu = new View(c);
        menu.mainMenu();
    }
}