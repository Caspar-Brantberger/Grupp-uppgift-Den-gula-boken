import java.util.Scanner;

public class CreateFunctions {

    Scanner scanner = new Scanner(System.in);
    CheckFunctions checkFunctions = new CheckFunctions();
    App app = new App();

    public void newContact(){

        System.out.println(" ");
        System.out.println("Please insert firstname of contact");
        String firstName = scanner.nextLine();

        System.out.println(" ");
        System.out.println("Please insert lastname of contact");
        String lastName = scanner.nextLine();

        System.out.println(" ");
        System.out.println("Please insert phone number of contact");
        String phoneNumber = checkFunctions.isNumber(scanner.nextLine());

        System.out.println(" ");
        System.out.println("Please insert age of contact");
        String age = checkFunctions.isNumber(scanner.nextLine());


        System.out.println(" ");
        System.out.println("Please insert address of contact");
        String address = scanner.nextLine();

        createContact(firstName, lastName, phoneNumber, age, address);
        System.out.println(" ");
        System.out.println("Contact Added!");
    }

    public void createContact(String firstName, String lastName, String phoneNumber, String age, String address) {

        Contact[] oldList = app.contacts;
        app.contacts = new Contact[oldList.length + 1];

        for (int i = 0; i < oldList.length; i++) {
            app.contacts[i] = oldList[i];
        }

        app.contacts[oldList.length] = new Contact(firstName, lastName, phoneNumber,age, address);
    }
}