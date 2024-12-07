import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateFunctions {
    private final List<Contact> contacts;
    private final Scanner scanner = new Scanner(System.in);
    private final CheckFunctions checkFunctions = new CheckFunctions();

    public CreateFunctions() {
        contacts = new ArrayList<>();
        loadContactsFromFile();
    }

    private void loadContactsFromFile() {
    }

    public void newContact() {
        System.out.println(" ");
        System.out.println("Please insert firstname of contact");
        String firstName = scanner.nextLine();

        System.out.println(" ");
        System.out.println("Please insert lastname of contact");
        String lastName = scanner.nextLine();

        System.out.println(" ");
        System.out.println("Please insert phone number of contact");
        String phoneNumber = scanner.nextLine();

        System.out.println(" ");
        System.out.println("Please insert age of contact");
        String age = checkFunctions.isNumber(scanner.nextLine());

        System.out.println(" ");
        System.out.println("Please insert address of contact");
        String address = scanner.nextLine();

        App app = new App();
        app.addNewContact(firstName, lastName, phoneNumber, age, address);

        System.out.println(" ");
        System.out.println("Contact Added!");
    }


    public void createContact(String firstName, String lastName, String phoneNumber, String age, String address) {
        Contact newContact = new Contact(firstName, lastName, phoneNumber, age, address);
        contacts.add(newContact);
        saveContactsToFile();
        System.out.println("Contact added: " + contacts.size() + " total contacts.");

    }

    private void saveContactsToFile() {
    }



}
