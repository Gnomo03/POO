import java.util.Scanner;

public class View {
    private Controller _cont = null;
    Scanner scanner = null;

    public View(Controller c) {
        _cont = c;
        scanner = new Scanner(System.in);
    }

    private boolean registerUser() {
        boolean result = true;

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your NIF: ");
        int nif = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        if (!_cont.registerUser(email, name, address, nif, password)) {
            System.out.print("Email already taken! Choose a different one\n");
            result = false;
            // email = scanner.nextLine();
        }
        /*
         * System.out.print("\n");
         * System.out.print("\n");
         * System.out.print("\n");
         * System.out.print("Press 'l' to Login:\n");
         * System.out.print("Press 'r' to Register a new User:\n");
         * 
         * String login_options2 = scanner.nextLine();
         * if(login_options2.equals("l")){
         * break;
         * }
         * }
         */

        return result;
    }

    public boolean doLogin() {
        System.out.flush();
        System.out.print("Login page\n");
        System.out.print("\n");
        System.out.print("\n");

        boolean login_status = false;
        while (!login_status) {
            System.out.print("Enter your email: ");
            String email_login = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password_login = scanner.nextLine();

            if (_cont.login(email_login, password_login)) {
                break;
            } else {
                System.out.print("Wrong Credentials!\n");
            }
        }
        return login_status;
    }

    private void registerItem() {
        String des, brand, price, score, date, size, carrier;
        // User cUser = _cont.getCurrentUser();
        // int cID = cUser.getId();
        if (_cont.getCurrentUser() == null) {
            System.out.print("You're not logged in. Pls log in first.\n");
        } else {
            // ....
            System.out.flush();
            System.out.print("Select the Item you wish to register\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("b: Bag:\n");
            System.out.print("t: Tshirt:\n");
            System.out.print("s: Sneaker:\n");
            String item_regist = scanner.nextLine();
            switch (item_regist) {
                case "b":
                    System.out.print("Enter Bag description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Bag brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Bag Base Price:");
                    price = scanner.nextLine();
                    System.out.print("On a scale of 1 to 5, how good is the condition of the item. Consider 5 to be still on the container bag:");
                    score = scanner.nextLine();
                    System.out.print("Enter Bag dimension:");
                    String dimension = scanner.nextLine();
                    System.out.print("Enter Bag material:");
                    String material = scanner.nextLine();
                    System.out.print("Enter Bag Release Date:");
                    date = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:");
                    System.out.print(_cont.displayCarriers());
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemBag(des, brand, Util.ToDouble(price),
                            carrier, Util.ToDouble(score)/5,
                            
                            // cID,
                            Util.ToInteger(dimension), material, Util.ToDate(date));
                    break;
                // ------------------------------------------------------------------------------------------
                case "t":
                    System.out.print("Enter Tshirt description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Tshirt brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Tshirt Base Price:");
                    price = scanner.nextLine();
                    System.out.print("On a scale of 1 to 5, how good is the condition of the item. Consider 5 to be still on the container bag:");
                    score = scanner.nextLine();
                    System.out.print("Enter Tshirt Size:");
                    size = scanner.nextLine();
                    System.out.print("Enter Tshirt Pattern:");
                    String pattern = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:");
                    System.out.print(_cont.displayCarriers());
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemTshirt(des, brand, Util.ToDouble(price),
                            carrier, Util.ToInteger(score)/5,
                            // cID,
                            Util.toTshirtSize(size), Util.toTshirtPattern(pattern));
                    break;
                // ------------------------------------------------------------------------------------------
                case "s":
                    System.out.print("Enter Sneaker description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Sneaker brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Sneaker Base Price:");
                    price = scanner.nextLine();
                    System.out.print("On a scale of 1 to 5, how good is the condition of the item. Consider 5 to be still on the container bag:");
                    score = scanner.nextLine();
                    System.out.print("Enter Sneaker Size:");
                    size = scanner.nextLine();
                    System.out.print("Enter Sneaker Type:");
                    String type = scanner.nextLine();
                    System.out.print("Enter Sneaker Color:");
                    String color = scanner.nextLine();
                    System.out.print("Enter Sneaker Release Date:");
                    date = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:");
                    System.out.print(_cont.displayCarriers());
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemSneaker(des, brand, Util.ToDouble(price),
                            carrier, Util.ToDouble(score)/5,
                            // cID,
                            Util.ToDouble(size), Util.toSneakerType(type), color, Util.ToDate(date));
                    break;
            }
        }
    }

    private void registerOrder() {
        System.out.print("Select the Item you wish to register\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(this._cont.displayListedItems());
        System.out.print("\n");
        System.out.print("Type the id´s of the item you wish to order seperated by a ','\n");
        System.out.print("\n");
        System.out.print("\n");
        String items_list = scanner.nextLine();
        this._cont.placeOrder(Util.toLinkedList(items_list));
    }

    private void checkOrder() {

        System.out.print(_cont.getCurrentUser().getName() + "current Orders");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(this._cont.getCurrentUser().getAcquiredOrder());
        System.out.print("\n");
        System.out.print("\n");
    }

    private void skipTime(){

        System.out.print("Not implemented yet\n");

    }
    
    public void mainMenu() {
        boolean quit = false;

        while (!quit) {
            System.out.flush();
            System.out.print("Welcome to Vintage!\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("l: Login:\n");
            System.out.print("u: Register a User:\n");
            System.out.print("o: Make a order:\n");
            System.out.print("i: Register a Item:\n");
            System.out.print("c: To check orders:\n");
            System.out.print("t: To skip time:\n");
            System.out.print("\n");
            if (_cont.getCurrentUser() != null) {
                System.out.print("User: " + _cont.getCurrentUser().getName());
            } else {
                System.out.print("No user logged in.");
            }
            System.out.print("\n");
            System.out.print("Press 'q' to Quit\n");

            String option = scanner.nextLine();

            switch (option) {
                case "q":                    
                    quit = true;
                    _cont.save();
                    break;

                case "u":
                    registerUser();
                    break;

                case "l":
                    doLogin();
                    break;

                case "i":
                    registerItem();
                    break;

                case "o":
                    registerOrder();
                    break;

                case "c":
                    checkOrder();
                    break;
                case "t":
                    skipTime();
                break;
            }
        }

    }
}
