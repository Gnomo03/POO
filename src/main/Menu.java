import java.util.Scanner;
import javax.sound.midi.ControllerEventListener;

public class Menu {
    private Controller _cont = null;
    Scanner scanner = null;
    
    public Menu( Controller c ) {
        _cont = c;
        scanner = new Scanner(System.in);
    }


    private boolean registerUser(){
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
        scanner.nextLine();  // consume the newline character
        if( ! _cont.registerUser(email,name,address,nif,password) ){
            System.out.print("Email already taken! Choose a different one\n");
            result = false;
            //email = scanner.nextLine();
        }
        /*
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
        */

        return result;
    }

    public boolean doLogin(){
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
        
            if ( _cont.login(email_login, password_login) ) {
                break;
            } else {
                System.out.print("Wrong Credentials!\n");
            }
        }
        return login_status;
    }

    private void registerItem(){
        if( _cont.getCurrentUser() == null ){
            System.out.print("You're not logged in. Pls log in first.\n");    
        }
        else{
            //....
            System.out.print("Not implemented... Yet!\n");
        }
    }

    private void registerOrder(){
        System.out.print("Not implemented... Yet!\n");
    }


    public void mainMenu() {
        boolean quit = false;

        while( !quit ){
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
            System.out.print("\n");
            if(_cont.getCurrentUser() != null ){
                System.out.print("User: " + _cont.getCurrentUser().getName());
            }
            else{
                System.out.print("No user logged in.");
            }
            System.out.print("\n");
            System.out.print("Press 'q' to Quit\n");


            String option = scanner.nextLine();

            switch(option){
                case "q":
                    _cont.saveData();
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
            }
        }

        /*
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
            System.out.print("Press 'q' to Quit\n");

            String regist_options = scanner.nextLine();

            switch(regist_options){
                case "r":
                    registerItem(scanner);
                
            }
        }
        */
    }
}
