import java.util.LinkedList;
import java.util.Scanner;

import javax.sound.midi.ControllerEventListener;
public class main {
    
public static void main(String[] args) {
    
    Module m = new Module();
    Controller c = new Controller(m);
    
    Scanner scanner = new Scanner(System.in);
    System.out.flush();
    System.out.print("Welcome to Vintage!\n");
    System.out.print("\n");
    System.out.print("\n");
    System.out.print("\n");
    System.out.print("\n");
    System.out.print("Press 'l' to Login:\n");
    System.out.print("Press 'r' to Register a User:\n");

    String login_options = scanner.nextLine();
    if (login_options.equals("r")){
        while(true){
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
        scanner.nextLine();  // consume the newline character
       while(c.registerUser(email,name,address,nif,password)== false){

        System.out.print("Email already taken! Choose a different one\n");
        email = scanner.nextLine();

       }
       System.out.print("\n");
       System.out.print("\n");
       System.out.print("\n");
       System.out.print("Press 'l' to Login:\n");
       System.out.print("Press 'r' to Register a new User:\n");

       String login_options2 = scanner.nextLine();
       if(login_options2.equals("l")){
            break;
       }
        
    }
    }
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
    
       
    
        if (c.login(email_login, password_login)) {
            break;
        } else {
            System.out.print("Wrong Credentials!");
        }
    }
    // após login, deve ser um menu
    while(true) {

        System.out.flush();
        System.out.print("Welcome! " +  m.getCurrentUser().getName() + "\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Press 'o' to Make a order:\n");
        System.out.print("Press 'r' to Register a Item:\n");

        String regist_options = scanner.nextLine();

        if (regist_options.equals("r")){
            while(true){
                System.out.flush();
                System.out.print("Select the Item you wish to register\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("Press 'b' to register a Bag:\n");
                System.out.print("Press 't' to register a Tshirt:\n");
                System.out.print("Press 's' to register a Sneaker:\n");
                String actions = scanner.nextLine();
                switch(actions){
                    case "b":
                        System.out.flush();
                        System.out.print("Write a description of the Bag: ");
                        String description = scanner.nextLine();
                        System.out.print("What's the brand of the Bag: ");
                        String brand = scanner.nextLine();
                        System.out.print("Write a refrence of the Bag: ");
                        String refrence = scanner.nextLine();
                        System.out.print("How much will the Bag cost: ");
                        String basePrice = scanner.nextLine();
                        /*
                        *carrier 
                        */
                        System.out.print("What's the condition score of the Bag: ");
                        String conditionScore = scanner.nextLine();
                        System.out.print("How many previous Owners of the Bag: ");
                        String previousOwners = scanner.nextLine();
                        System.out.print("Is the Bag premium? (write true or false): ");
                        String premium = scanner.nextLine();
                        System.out.print("What's the dimension of the Bag: ");
                        String dimension = scanner.nextLine();
                        System.out.print("What's the material of the Bag: ");
                        String material = scanner.nextLine();
                        System.out.print("In what year was the Bag created: ");
                        String date = scanner.nextLine();
                        scanner.nextLine();
                        /* 
                        while(m.registItemBag(description, brand, refrence, Double.parseDouble(basePrice), null,
                        Integer.parseInt(conditionScore), Integer.parseInt(previousOwners), Boolean.parseBoolean(premium),
                        m.getCurrentUser().id , Integer.parseInt(dimension), material, Integer.parseInt(date)) == false){
                        */                       
                    }                    
                }
            }
        }
    }
}