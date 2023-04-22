import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    
public static void main(String[] args) {
    
    Module m = new Module();
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
       while(m.registerUser(email,name,address,nif,password)== false){

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
    
       
    
        if (m.login(email_login, password_login)) {
            break;
        } else {
            System.out.print("Wrong Credentials!");
        }
    }
    // após login, deve ser um menu
    while(true) {

        System.out.flush();
        System.out.print("Welcome! " + m.getCurrentUser().getName() + "\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Press 'o' to Make a order:\n");
        System.out.print("Press 'r' to Register a Item:\n");

        String actions = scanner.nextLine();
        

    }
}
    
}