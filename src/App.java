import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    static List<Contact> contacts = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static boolean runProgram = true;

    public static boolean isAdmin = true;
    public static boolean isLoggedIn = false;

    public void app() {
        // Pre-defined contacts
        createContact("Elin", "Jirefalk", "0739709078", "20", "Tistelgatan 11");
        createContact("Jonathan", "Jirefalk", "0767747162", "23", "Tistelgatan 11");
        createContact("Elias", "Sjöstedt", "0706419359", "22", "Maskrosgatan 2");
        createContact("Caspar", "Brantberger", "07001231231", "24", "Nyköpingsvägen23");

        appLoop();
    }

    public static void loginPrompt() {
        System.out.println("Hello, how would you like to login?");
        System.out.println("1. Guest");
        System.out.println("2. Admin");
        System.out.println("3. Exit Application");

        switch (scanner.nextLine()) {
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

    public void appLoop() {
        while (runProgram) {
            while (!isLoggedIn) {
                loginPrompt();
            }

            if (isAdmin && runProgram) {
                adminInput();
            } else if (runProgram) {
                guestInput();
            }
        }
    }

    public static void adminLogin() {
        String[] userName = {"Jonathan", "Caspar", "Angelica", "Ali", "William"};
        String[] password = {"jo", "ca", "an", "al", "wi"};

        System.out.println("Please Insert Username:");
        String username = scanner.nextLine();

        for (int i = 0; i < userName.length; i++) {
            if (username.equals(userName[i])) {
                System.out.println("Please insert password: ");
                while (!scanner.nextLine().equals(password[i])) {
                    System.out.println("Password incorrect, please try again!");
                }
                System.out.println("Successfully logged in as " + userName[i] + "!");
                isAdmin = true;
                isLoggedIn = true;
                return;
            }
        }
        System.out.println("Invalid username, please try again!");
    }

    public void adminInput() {
        System.out.println("What action would you like to do?");
        System.out.println("1. Display phonebook");
        System.out.println("2. Remove Contact");
        System.out.println("3. Create New Contact");
        System.out.println("4. Search Contact");
        System.out.println("5. Save Contacts to File");
        System.out.println("6. Logout");

        switch (scanner.nextLine()) {
            case "1":
                displayContacts();
                break;
            case "2":
                chooseContactToDelete();
                break;
            case "3":
                newContact();
                break;
            case "4":
                searchContact();
                break;
            case "5":
                saveContactsToFile("contacts.txt");
                break;
            case "6":
                isAdmin = false;
                isLoggedIn = false;
                break;
            default:
                System.out.println("Not a valid input");
        }
    }

    public static void guestInput() {
        System.out.println("What action would you like to do?");
        System.out.println("1. Display phonebook");
        System.out.println("2. Search Contact");
        System.out.println("3. Logout");

        switch (scanner.nextLine()) {
            case "1":
                displayContacts();
                break;
            case "2":
                searchContact();
                break;
            case "3":
                isLoggedIn = false;
                isAdmin = false;
                break;
            default:
                System.out.println("Not a valid input");
        }
    }

    public static void searchContact() {
        System.out.println("Enter firstname or lastname.");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Contact contact : contacts) {
            if (contact.getFirstName().toLowerCase().contains(query) ||
                    contact.getLastName().toLowerCase().contains(query)) {
                contact.displayContact();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Could not find: " + query);
        }
    }

    public void newContact() {
        System.out.println("Please insert firstname of contact:");
        String firstName = scanner.nextLine();
        System.out.println("Please insert lastname of contact:");
        String lastName = scanner.nextLine();
        System.out.println("Please insert phone number of contact:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Please insert age of contact:");
        String age = scanner.nextLine();
        System.out.println("Please insert address of contact:");
        String address = scanner.nextLine();

        createContact(firstName, lastName, phoneNumber, age, address);
        System.out.println("Contact Added!");
    }

    public void createContact(String firstName, String lastName, String phoneNumber, String age, String address) {
        contacts.add(new Contact(firstName, lastName, phoneNumber, age, address));
    }

    public static void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.print((i + 1) + ". ");
                contacts.get(i).displayContact();
            }
        }
    }

    public static void chooseContactToDelete() {
        displayContacts();
        System.out.println("Input corresponding number to contact you want to delete:");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < contacts.size()) {
                contacts.remove(index);
                System.out.println("Contact Removed!");
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    public static void saveContactsToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Contact contact : contacts) {
                writer.write(contact.toCSV() + "\n");
            }
            System.out.println("Contacts saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    class Contact {
        private String firstName, lastName, number, age, address;

        public Contact(String firstName, String lastName, String number, String age, String address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.number = number;
            this.age = age;
            this.address = address;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String toCSV() {
            return firstName + "," + lastName + "," + number + "," + age + "," + address;
        }

        public void displayContact() {
            System.out.println(firstName + " " + lastName + ", " + number + ", " + age + " years, " + address);
        }
    }
}
