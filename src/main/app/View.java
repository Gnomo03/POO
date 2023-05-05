package app;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private Controller _cont = null;
    Scanner scanner = null;

    public View(Controller c) {
        _cont = c;
        scanner = new Scanner(System.in);
    }

    private void registerUser() {
        
        try {
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
        _cont.registerUser(email, name, address, nif, password);
        
    }catch(UserAlreadyExistsException e) {
        System.out.print("Email already taken!\n");
        scanner.nextLine(); // consume the newline character
    }catch(InputMismatchException e) {

    }

    }

    public void doLogin() {
        System.out.flush();
        System.out.print("Login page\n");
        System.out.print("\n");
        System.out.print("\n");
        
        
        try {
            System.out.print("Enter your email: ");
            String email_login = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password_login = scanner.nextLine();
            _cont.login(email_login, password_login);
            
        }catch(MissedIdException e){
            System.out.print("Wrong password for the user!\n");
            scanner.nextLine();
        }   
        catch(NullPointerException e){
            System.out.print("No user with those credentials!\n");
            scanner.nextLine();
        }
    
        
        
    }

    private void registerItem() {
        String des, brand,carrier,date,type,color,Tsizes,pattern,material,premium;
        double price, score;
        int size,itemId, width,height,depth,dimension;
        // User cUser = _cont.getCurrentUser();
        // int cID = cUser.getId();
        try {
            _cont.getCurrentUser();
        
            // ....
            System.out.flush();
            System.out.print("Select the Item you wish to register\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("b: Bag:\n");
            System.out.print("t: Tshirt:\n");
            System.out.print("s: Sneaker:\n");
            System.out.print("a: In case the item was bought in the store:\n");
            String item_regist = scanner.nextLine();
            switch (item_regist) {
                case "b":
                    System.out.print("Enter Bag description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Bag brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Bag Base Price:");
                    price = scanner.nextDouble();
                    System.out.print("On a scale of 1 to 5, how good is the condition of the item. Consider 5 to be still on the container bag:");
                    score = scanner.nextDouble();
                    System.out.print("Enter Bag dimension:");
                    System.out.print("Width in cm:");
                    width = scanner.nextInt();
                    System.out.print("Height in cm:");
                    height = scanner.nextInt();
                    System.out.print("Depth in cm:");
                    depth = scanner.nextInt();
                    dimension = width * height * depth;
                    System.out.print("Enter Bag material:");
                    material = scanner.nextLine();
                    System.out.print("Enter Bag Release Date (yyyy-mm-dd):");
                    date = scanner.nextLine();
                    System.out.print("Is this a Premium Item ? ('y' for yes or 'n' for no)");
                    premium = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:"); 
                    System.out.print(_cont.displayCarriers(premium));
                    System.out.print("\n");
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemBag(des, brand, price,
                            carrier,score/5,
                            dimension, material, Util.toDate(date),premium);
                    break;
                // ------------------------------------------------------------------------------------------
                case "t":
                    System.out.print("Enter Tshirt description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Tshirt brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Tshirt Base Price:");
                    price = scanner.nextInt();
                    System.out.print("On a scale of 1 to 5, how good is the condition of the item. Consider 5 to be still on the container bag:");
                    score = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Tshirt Size(S, M, L, XL):");
                    Tsizes = scanner.nextLine();
                    System.out.print("Enter Tshirt Pattern (Smooth, Stripes, PalmTrees):");
                    pattern = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:");
                    System.out.print(_cont.displayCarriers("n"));
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemTshirt(des, brand, price,
                            carrier,score/5,
                            Util.toTshirtSize(Tsizes), Util.toTshirtPattern(pattern));
                    break;
                // ------------------------------------------------------------------------------------------
                case "s":
                    System.out.print("Enter Sneaker description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Sneaker brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Sneaker Base Price:");
                    price = scanner.nextDouble();
                    System.out.print("On a scale of 1 to 5, how good is the condition of the item. Consider 5 to be still on the container bag:");
                    score = scanner.nextDouble();
                    System.out.print("Enter Sneaker Size (Eur):");
                    size = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Sneaker Type(LACES, NOLACES):");
                    type = scanner.nextLine();
                    System.out.print("Enter Sneaker Color:");
                    color = scanner.nextLine();
                    System.out.print("Enter Sneaker Release Date (yyyy-mm-dd):");
                    date = scanner.nextLine();
                    System.out.print("Is this a Premium Item ? ('y' for yes or 'n' for no)");
                    premium = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:");
                    System.out.print(_cont.displayCarriers(premium));
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemSneaker(des, brand, price,
                            carrier,score/5,
                            size, Util.toSneakerType(type), color, Util.toDate(date),premium);
                    break;
                    case "a":
                    System.out.print("System Items:");
                    System.out.print(this._cont.getCurrentUserSystemItems());
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("Choose the id of the item you wish to list:\n");
                    itemId = scanner.nextInt();
                    _cont.listSystemItem(itemId);
                    break;
            }
        }
        catch (NullPointerException e){
            System.out.print("No user is Logged in!\n");
            scanner.nextLine();
        }
        catch (UserIsAdminException e){
            System.out.print("Admin cannot register a item!\n");
            scanner.nextLine();
        }
        catch (InputMismatchException e){
            System.out.print("The input was not valid!\n");
            scanner.nextLine();
        }
        catch (IllegalArgumentException e){
            scanner.nextLine();
        }
    }

    private void registerOrder() {
        try{
        System.out.print("Select the Item you wish to register\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(this._cont.showListedItems());
        System.out.print("\n");
        System.out.print("Type the id´s of the item you wish to order seperated by a ','\n");
        System.out.print("\n");
        System.out.print("\n");
        String items_list = scanner.nextLine();
        this._cont.placeOrder(Util.toLinkedList(items_list));}
        catch(UserIsAdminException e){
            System.out.println("Admin cannot make an order!\n");
            scanner.nextLine();
        }
        catch(InvalidId e){
            System.out.println("Invalid Id of the item\n");
            scanner.nextLine();
        }
        catch(NumberFormatException e){
            System.out.println("Id numbers only!\n");
            scanner.nextLine();
        }
        
    }
    private void changeCarrierMenu(){
        try{
        System.out.print("Let´s change a carrier to the system!\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(_cont.showAllCarriers());
        System.out.print("Insert the carrier Name: ");
        String name = scanner.nextLine();
        System.out.print("\n");
        System.out.print("Insert the carrier comission for small orders:");
        double taxSmall = scanner.nextDouble();
        System.out.print("\n");
        System.out.print("Insert the carrier comission for medium orders:");
        double taxMedium = scanner.nextDouble();
        System.out.print("\n");
        System.out.print("Insert the carrier comission for big orders:");
        double taxBig = scanner.nextDouble();
        
            _cont.changeCarrier(name, taxSmall, taxMedium, taxBig);
        }catch(NullPointerException e ){
            System.out.println("Carrier is not in the database!\n");
            scanner.nextLine();
        }catch (InputMismatchException e) {
            System.out.print("Wrong Input type\n");
            scanner.nextLine();
        }


    }
    private void addCarrierMenu(){
        try{
        System.out.print("Let´s add a carrier to the system!\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Insert the carrier Name: ");
        String name = scanner.nextLine();
        System.out.print("Is it a premium Carrier ? ('y' for yes): ");
        String premium = scanner.nextLine();
        System.out.print("\n");
        System.out.print("Insert the carrier comission for small orders:");
        double taxSmall = scanner.nextDouble();
        System.out.print("\n");
        System.out.print("Insert the carrier comission for medium orders:");
        double taxMedium = scanner.nextDouble();
        System.out.print("\n");
        System.out.print("Insert the carrier comission for big orders:");
        double taxBig = scanner.nextDouble();
        _cont.registCarrier(name, taxSmall, taxMedium, taxBig,premium);

        } catch (InputMismatchException e) {
            System.out.print("Wrong Input type\n");
            scanner.nextLine();
        }
         catch(CarrierAlreadyExistsException e ){
            System.out.println("Carrier already in the database!\n");
            scanner.nextLine();
        }


    }
    private void querierMenu() {
        try{
        System.out.print("Querier Menu! Type one of the following numbers to execute a query!\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("1.Check the User that made the most amount of Money all time!\n");
        System.out.print("2.Check the User that made the most amount of Money in a time frame!\n");
        System.out.print("3.Check the Carrier that made the most amount of Money!\n");
        System.out.print("4.Check User Emitted Orders!\n");
        System.out.print("5.Check the list of Sellers in a time frame!\n");
        System.out.print("6.Check the list of Spenders in a time frame!\n");
        System.out.print("7.Check Vintage Profit!\n");
        int query = scanner.nextInt();
        scanner.nextLine();
        int id = 0;
        String date1,date2;
        date1 = date2 = "1999-01-01";
        if (query==4){
            System.out.print("User you which to see id: ");
            id = scanner.nextInt();
            scanner.nextLine();
        }
        if (query==2){
            System.out.print("Choose the Time frame\n");
            System.out.print("First date:");
            date1 = scanner.nextLine();
            System.out.print("\n");
            System.out.print("Second date:");
            date2 = scanner.nextLine();
        }
        if (query==5){
            System.out.print("Choose the Time frame\n");
            System.out.print("First date:");
            date1 = scanner.nextLine();
            System.out.print("\n");
            System.out.print("Second date:");
            date2 = scanner.nextLine();
        }
        if (query==6){
            System.out.print("Choose the Time frame\n");
            System.out.print("First date:");
            date1 = scanner.nextLine();
            System.out.print("\n");
            System.out.print("Second date:");
            date2 = scanner.nextLine();
        }
        String out = _cont.querrierExecution(query, Util.toDate(date1), Util.toDate(date2),id);
        System.out.println(out);
        scanner.nextLine();
        }
        catch(NullPointerException e){}
    }
    private void checkOrder() {
        try{
        System.out.print(_cont.getCurrentUser().getName() + "'s current Orders");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(this._cont.getCurrentUserAllOrders());
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Do you wich to return an order ? ('y' for yes or 'n' for no)\n");
        String option = scanner.nextLine();
        if (option.equals("y")) {

            System.out.print("Type the id of the order:");
            int orderId = scanner.nextInt(); 
            _cont.returnOrderId(orderId);
            scanner.nextLine();
        }
    }catch(UserIsAdminException e){
        System.out.println("Admin dont have orders!\n");
        scanner.nextLine();
    }catch(OrderNotReturnable e){
        System.out.print("This Order cannot be returned!\n");
        scanner.nextLine();
    }
    catch(InputMismatchException e){
        System.out.print("Type the id of the order!\n");
        scanner.nextLine();
    }
    }
    private void checkMyItems() {
        try {
        System.out.print(_cont.getCurrentUser().getName() + "'s Items");
        System.out.print("\n");
        System.out.print("Listed Items:");
        System.out.print(this._cont.getCurrentUserListedItems());
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("System Items:");
        System.out.print(this._cont.getCurrentUserSystemItems());
        System.out.print("\n");
        scanner.nextLine();
    }catch(UserIsAdminException e){
        System.out.println("Admin dont have items!\n");
    }
    }
    private void checkBills() {
        try{
        System.out.print(_cont.getCurrentUser().getName() + "' current Bills");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print(this._cont.getCurrentUser().getBills());
        System.out.print("\n");
        System.out.print("\n");
        scanner.nextLine();
        }catch(UserIsAdminException e){
            System.out.println("Admin dont have bills!\n");
        }
    }

    private void skipTime(){
        try{
        System.out.print("This is the current date of the Simulation:"+_cont.accessDate()+"\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Choose a date in the future to advance the simulation!\n");
        System.out.print("Format: yyyy-mm-dd\n");
        String date = scanner.nextLine();
        _cont.advanceTime(Util.toDate(date));
        System.out.print("\n");
        System.out.print("Date updated!\n");
        }
        catch (IllegalArgumentException e) {
            System.out.print("Invalid Date!\n");
        }
    }
    
    public void mainMenu() {
        boolean quit = false;

        while (!quit) {
            System.out.flush();
            try{
            _cont.getCurrentUser();
            System.out.print("Welcome to Vintage!\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("l: Login to another User:\n");
            System.out.print("u: Register a User:\n");
            System.out.print("o: Make a order:\n");
            System.out.print("i: Register a Item:\n");
            System.out.print("c: To check orders:\n");
            System.out.print("t: To skip time:\n");
            System.out.print("b: To check bills:\n");
            System.out.print("m: To check my items:\n");
            System.out.print("x: Logout:\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("Press 'q' to Quit\n");
            System.out.print("User: " + _cont.getCurrentUser().getName());
            System.out.print("\n");
            System.out.print("\n");
            String option = scanner.nextLine();

            switch (option) {
                case "q":
                System.out.print(_cont.toString());
                try{_cont.save();}
                catch(FileNotFoundException e){
                    System.out.println("Save File not Found!");
                }
                catch(IOException e){
                    System.out.println("Error Acessing File!");
                }
                
                    quit = true;
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

                case "b":
                    checkBills();
                    break;
                case "m":
                    checkMyItems();
                    break;
                case "x":
                    _cont.logout();
                    
                    break;
            }
            }catch(NullPointerException e){
            System.out.print("Welcome to Vintage!\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("l: Login:\n");
            System.out.print("u: Register a User:\n");
            System.out.print("t: To skip time:\n");
            System.out.print("s: To execute a simulation (will require a .txt file and a path):\n");
            System.out.print("\n");
            System.out.print("No user logged in.");
            System.out.print("\n");
            System.out.print("Press 'q' to Quit\n");
            String option = scanner.nextLine();

            switch (option) {
                case "q":
                System.out.print(_cont.toString());
                try{_cont.save();}
                catch(FileNotFoundException esc){
                    System.out.println("Save File not Found!");
                }
                catch(IOException esc){
                    System.out.println("Error Acessing File!");
                }
                
                    quit = true;
                    break;

                case "u":
                    registerUser();
                    break;

                case "l":
                    doLogin();
                    break;
                case "t":
                    skipTime();
                break;
                case "s":
                try{
                    System.out.print("Write the path for the simulation file: ");
                    String path = scanner.nextLine();
                    _cont.simulation(path);  
                }catch(FileNotFoundException esc){
                    System.out.println("Error reaching .txt File!");
                }
                catch(IOException esc){
                    System.out.println("Error reading .txt File!");
                }catch (InvalidCommand esc) {
                    System.out.println(esc.getMessage()); // prints the custom error message
                }
                break;

            }
            }
            catch(UserIsAdminException e){
            System.out.print("Welcome to Vintage Special Admin Menu!\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("l: Login to a diffrent User:\n");
            System.out.print("u: Register a User:\n");
            System.out.print("t: To skip time:\n");
            System.out.print("r: To add Carriers:\n");
            System.out.print("c: To change Carriers:\n");
            System.out.print("a: To access the system Queries:\n");
            System.out.print("x: Logout:\n");
            System.out.print("\n");
            System.out.print("No user logged in.");
            System.out.print("\n");
            System.out.print("Press 'q' to Quit\n");
            String option = scanner.nextLine();
            switch (option) {
                case "q":
                System.out.print(_cont.toString());
                try{_cont.save();}
                catch(FileNotFoundException esc){
                    System.out.println("Save File not Found!");
                }
                catch(IOException esc){
                    System.out.println("Error Acessing File!");
                }
                
                    quit = true;
                    break;

                    case "u":

                    registerUser();
                    break;

                     case "l":

                    doLogin();

                    break;

                    case "t":

                    skipTime();

                    break;

                    case "r":

                    addCarrierMenu();

                    break;
                    case "c":

                    changeCarrierMenu();

                    break;
                    case "x":
                    _cont.logout();
                    
                    break;
                    case "a":

                    querierMenu();

                    break;
            }
            
           
        }

    }
}

    
}
