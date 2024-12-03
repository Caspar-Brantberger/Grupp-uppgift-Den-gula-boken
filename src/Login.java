import java.util.Scanner;

public class Login {
    Login ln = new Login();
    Scanner scanner = new Scanner(System.in);
    public static boolean runProgram = true;

    public static boolean isAdmin = true;
    public static boolean isLoggedIn = false;

    public Login getLn() {
        return ln;
    }

    public Login() {
        this.ln = ln;
    }

    public Login(Login ln) {
        this.ln = ln;
    }

    public  void loginPrompt(){

        System.out.println(" ");
        System.out.println("Hello, how would you like to login?");
        System.out.println("1. Guest");
        System.out.println("2. Admin");
        System.out.println("3. Exit Application");

        switch (scanner.nextLine()){
            case "1":
                isLoggedIn = true;
                isAdmin = false;
                break;
            case "2":
                adminLogin();
                break;
            case "3":
                isLoggedIn = true;
                runProgram = false;
                break;
            default:
                System.out.println("Invalid Input!");
        }

    }
    public  void adminLogin(){

        String[] userName = {"Jonathan", "Caspar", "Angelica", "Ali", "William"};
        String[] password = {"jo", "ca", "an", "al", "wi"};

        int indexOfName = 0;
        boolean validUsername = false;

        System.out.println(" ");
        System.out.println("Please Insert Username:");
        String username = scanner.nextLine();

        for (int i = 0; i < userName.length; i++) {

            if(username.equals(userName[i])) {
                indexOfName = i;
                validUsername = true;
            }
        }

        if(!validUsername) {

            System.out.println("Invalid username, please try again!");
            adminLogin();
        }
        else{

            System.out.println(" ");
            System.out.println("User: " + userName[indexOfName]);
            System.out.println("Please insert password: ");

            while(!scanner.nextLine().equals(password[indexOfName])) {

                System.out.println(" ");
                System.out.println("Password incorrect, please try again!");
                System.out.println(" ");
                System.out.println("Please insert password: ");
            }

            System.out.println(" ");
            System.out.println("Successfully logged in as " + userName[indexOfName] + "!");
            isAdmin = true;
            isLoggedIn = true;
        }
    }
}
