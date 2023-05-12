package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.format.DateTimeParseException;

public class Controller {

    private Model m;

    public Controller(Model m) {
        this.m = m;
    }

    public User getCurrentUser() throws NullPointerException, UserIsAdminException {
        return this.m.CurrentUser();
    }

    public void login(String email, String password) throws NullPointerException, MissedIdException {

        m.loginModel(email, password);
    }

    public void logout() {
        m.nullCurrentUser();
    }

    private void parserExecuter(String buffer, int line) throws InvalidCommand, IllegalArgumentException {
        try {
            String[] substrings = buffer.split(",");
            if (substrings[0].equals("SetupDate")) {
                m.setCurrentDate(Util.toDate(substrings[1]));
                return;
            }

            LocalDate dateParse = Util.toDate(substrings[0]);
            if (dateParse.isAfter(Util.toDate(m.getDate()))) {
                m.TimeSkip(dateParse);
            }

            switch (substrings[1]) {

                case "RegistarUtilizador":
                    String[] arguments = substrings[2].split(";");
                    try {
                        m.registsUser(arguments[0], arguments[1], arguments[2], Integer.parseInt(arguments[3]),
                                arguments[4]);
                    } catch (UserAlreadyExistsException e) {
                        throw new InvalidCommand(substrings[1], line);
                    }
                    break;

                case "Login":
                    String[] arguments1 = substrings[2].split(";");
                    try {
                        m.loginModel(arguments1[0], arguments1[1]);
                    } catch (MissedIdException e) {
                        throw new InvalidCommand(substrings[1], line);
                    } catch (NullPointerException e) {
                        throw new InvalidCommand(substrings[1], line);
                    }
                    break;

                case "RegistarItem":
                    String[] arguments2 = substrings[2].split(";");
                    try {
                        switch (arguments2[0]) {
                            case "Bag":

                                if (arguments2[10].equals("No")) {
                                    double dimension = Double.parseDouble(arguments2[5])
                                            * Double.parseDouble(arguments2[6]) * Double.parseDouble(arguments2[7]);
                                    m.registBag(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                            arguments2[11], Double.parseDouble(arguments2[4]) / 5,
                                            dimension, arguments2[8], Util.toDate(arguments2[9]), "n");
                                } else {
                                    double dimension = Double.parseDouble(arguments2[5])
                                            * Double.parseDouble(arguments2[6]) * Double.parseDouble(arguments2[7]);
                                    m.registBag(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                            arguments2[10], Double.parseDouble(arguments2[4]) / 5,
                                            dimension, arguments2[8], Util.toDate(arguments2[9]), "y");
                                }
                                break;
                            case "Sneaker":

                                if (arguments2[9].equals("No")) {
                                    m.registSneaker(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                            arguments2[10], Double.parseDouble(arguments2[4]) / 5,
                                            Double.parseDouble(arguments2[5]), Util.toSneakerType(arguments2[6]),
                                            arguments2[7], Util.toDate(arguments2[8]), "n");
                                } else {
                                    m.registSneaker(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                            arguments2[10], Double.parseDouble(arguments2[4]) / 5,
                                            Double.parseDouble(arguments2[5]), Util.toSneakerType(arguments2[6]),
                                            arguments2[7], Util.toDate(arguments2[8]), "y");
                                }

                                break;
                            case "Tshirt":

                                m.registTshirt(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                        arguments2[7], Double.parseDouble(arguments2[4]) / 5,
                                        Util.toTshirtSize(arguments2[5]), Util.toTshirtPattern(arguments2[6]));

                                break;

                            default:
                                throw new InvalidCommand(substrings[1], line);

                        }
                    } catch (NullPointerException e) {
                        throw new InvalidCommand(substrings[1], line);
                    }
                    break;
                case "RegistarTransportadora":
                    String[] arguments3 = substrings[2].split(";");
                    try {
                        if (arguments3[1].equals("No"))
                            m.addCarrier(arguments3[0], Double.parseDouble(arguments3[2]),
                                    Double.parseDouble(arguments3[3]), Double.parseDouble(arguments3[4]), "n");
                        else {
                            m.addCarrier(arguments3[0], Double.parseDouble(arguments3[2]),
                                    Double.parseDouble(arguments3[3]), Double.parseDouble(arguments3[4]), "y");
                        }

                    } catch (CarrierAlreadyExistsException e) {

                        throw new InvalidCommand(substrings[1], line);
                    }

                    break;
                case "FazerEncomenda":
                    try {
                        m.makeOrder(Util.toLinkedListParser(substrings[2]));
                    } catch (InvalidId e) {
                        throw new InvalidCommand(substrings[1], line);
                    }
                    break;
                case "AlterarTransportadora":
                    String[] arguments4 = substrings[2].split(";");
                    try {

                        changeCarrier(arguments4[0], Double.parseDouble(arguments4[1]),
                                Double.parseDouble(arguments4[2]), Double.parseDouble(arguments4[3]));

                    } catch (NullPointerException e) {

                        throw new InvalidCommand(substrings[1], line);
                    }

                    break;
                case "PassarTempo":

                    break;

                default:
                    throw new InvalidCommand(substrings[1], line);

            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommand("Unidentified", line);
        } catch (DateTimeParseException e) {
            throw new InvalidCommand("Unidentified", line);
        }

    }

    public void simulation(String path)
            throws FileNotFoundException, IOException, InvalidCommand, IllegalArgumentException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int line = 1;
        while ((st = br.readLine()) != null) {
            if (Util.checkIgnore(st)) {
                parserExecuter(st, line);
            }
            line++;
        }
        br.close();

    }

    public void registItemBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate, String premium) throws UserIsAdminException, NullPointerException {

        m.registBag(description, brand, basePrice,
                carrier, conditionScore, dimension, material, releaseDate,
                premium);
    }

    public void registItemTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern) throws UserIsAdminException, NullPointerException {
        m.registTshirt(description, brand, basePrice, carrier,
                conditionScore, size, pattern);
    }

    public void registItemSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            double size, Sneaker.SneakerType type, String color, LocalDate releaseDate, String premium)
            throws UserIsAdminException, NullPointerException {
        m.registSneaker(description, brand, basePrice,
                carrier, conditionScore, size, type, color, releaseDate, premium);
    }

    public void registerUser(String email, String name, String address, int nif, String password)
            throws UserAlreadyExistsException {

        this.m.registsUser(email, name, address, nif, password);

    }

    public void advanceTime(LocalDate date) throws IllegalArgumentException {

        m.TimeSkip(date);
    }

    public void placeOrder(List<Integer> order) throws UserIsAdminException, InvalidId {

        m.makeOrder(order);

    }

    public String displayCarriers(String premium) {
        return m.showCarriers(premium);
    }

    public String showAllCarriers() {
        return m.displayAllCarriers();
    }

    public String showListedItems() throws UserIsAdminException {

        return m.displayListedItems();
    }

    public String getCurrentUserListedItems() throws UserIsAdminException {

        return m.currentUserListedItems();
    }

    public String getCurrentUserSystemItems() throws UserIsAdminException {

        return m.currentUserSystemItems();
    }

    public void listSystemItem(int item_id) throws UserIsAdminException, NullPointerException {

        m.alterItemState(item_id);

    }

    public String getCurrentUserAllOrders() throws UserIsAdminException {

        return m.checkThisUserOrders();

    }

    public String accessDate() {
        return m.getDate();
    }

    public String currentUserBills() {
        return m.userBills();
    }

    public void returnOrderId(int orderId) throws UserIsAdminException, OrderNotReturnable {

        m.deleteOrder(orderId);

    }

    public void save() throws FileNotFoundException, IOException {
        this.m.save("data.ser");
    }

    public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.m = Model.load("data.ser");
    }

    @Override
    public String toString() {
        return m.toString();
    }

    public void registCarrier(String name, double taxSmall, double taxMedium, double taxBig, String premium)
            throws CarrierAlreadyExistsException {

        m.addCarrier(name, taxSmall, taxMedium, taxBig, premium);

    }

    public void changeCarrier(String name, double taxSmall, double taxMedium, double taxBig)
            throws NullPointerException {
        m.changeCarrier(name, taxSmall, taxMedium, taxBig);
    }

    @SuppressWarnings("unchecked")
    public String querrierExecution(int query, LocalDate date1, LocalDate date2, int userID)
            throws NullPointerException {
        String result = "";
        Querier querier;
        switch (query) {
            case (1):
                querier = new BiggestEarnerAllTime(m.getUserManagerCopy());
                User u = (User) querier.execute();
                result = u.toString();
                break;
            case (2):
                querier = new BiggestEarnerAllTimeFrame(m.getUserManagerCopy(), date1, date2);
                User u2 = (User) querier.execute();
                result = u2.toString();
                break;
            case (3):
                querier = new BiggestCarrier(m.getCarrierManagerCopy());
                Carrier c = (Carrier) querier.execute();
                result = c.toString();
                break;
            case (4):
                querier = new EmmitedOrderList(m.getUserManagerCopy(), userID);
                List<Order> l = (LinkedList<Order>) querier.execute();
                result = l.toString();
                break;
            case (5):
                querier = new PodiumSeller(m.getUserManagerCopy(), date1, date2);
                List<User> l1 = (ArrayList<User>) querier.execute();
                result = l1.toString();
                break;
            case (6):
                querier = new PodiumSpenders(m.getUserManagerCopy(), date1, date2);
                List<User> l2 = (ArrayList<User>) querier.execute();
                result = l2.toString();
                break;
            case (7):
                querier = new VintageProfit(m.getVintageProfit());
                double d = (double) querier.execute();
                result = String.format("%f", d);
                break;
        }
        return result;
    }

}