public class Main {
    public static void main(String[] args) {
        
        Module m = new Module();
        Controller c = new Controller(m);
        c.loadData();

        //----------Carriers-----------------

        Carrier forBag = new Carrier("forBag", 2.50, 5, 7.50, 10);
        Carrier forTS = new Carrier("forTS", 5, 5.50, 6, 10);
        Carrier forSneak = new Carrier("forSneak", 3, 6, 9, 10);
        m.getCarrierManager().addCarrier(forBag);
        m.getCarrierManager().addCarrier(forTS);
        m.getCarrierManager().addCarrier(forSneak);

        //-----------------------------------

        Menu menu = new Menu(c);
        menu.mainMenu();
        
    }
}