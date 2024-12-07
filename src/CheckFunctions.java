import java.util.Scanner;

public class CheckFunctions {

    Scanner scanner = new Scanner(System.in);
    App app = new App();

    public String isNumber(String user){

        while (!user.matches("(\\d*)") || user.isEmpty()){
            System.out.println(" ");
            System.out.println("Invalid input, please insert a number");
            user = scanner.nextLine();
        }

        return user;
    }

    public boolean isEmpty(){

        if (App.contacts.length == 0){

            System.out.println(" ");
            System.out.println("Contact list is empty!");
            return true;
        }
        else{

            return false;
        }
    }
}
