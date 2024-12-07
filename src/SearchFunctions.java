import java.util.Scanner;

public class SearchFunctions {

    App app = new App();
    Scanner scanner = new Scanner(System.in);

    public void searchContact(){

        System.out.println(" ");
        System.out.println("What attribute would you like to search?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Address");
        System.out.println("4. Free Search");

        switch(scanner.nextLine()){
            case "1":
                System.out.println(" ");
                System.out.println("Enter first name: ");
                searchFirstName(scanner.nextLine().toLowerCase().trim());
                break;
            case "2":
                System.out.println(" ");
                System.out.println("Enter last name: ");
                searchLastName(scanner.nextLine().toLowerCase().trim());
                break;
            case "3":
                System.out.println(" ");
                System.out.println("Enter address: ");
                searchAddress(scanner.nextLine().toLowerCase().trim());
                break;
            case "4":
                System.out.println(" ");
                System.out.println("Enter any text: ");
                freeSearch(scanner.nextLine().toLowerCase().trim());
                break;
            default:
                System.out.println(" ");
                System.out.println("Not a valid input!");
                searchContact();
        }

    }

    public void searchFirstName(String firstName){

        boolean[] contactFound = new boolean[app.contacts.length];
        boolean print = false;

        for (int i = 0; i < app.contacts.length; i++) {

            if(app.contacts[i].getFirstName().toLowerCase().equals(firstName)){

                contactFound[i] = true;
                print = true;
            }
        }

        if (print){

            System.out.println(" ");
            System.out.println("All contacts that have the first name " + firstName);

            for (int i = 0; i < contactFound.length; i++) {

                if (contactFound[i]){

                    app.contacts[i].displayContact();
                }
            }
        }
        else{

            System.out.println(" ");
            System.out.println("No contact found with first name " + firstName);
        }
    }

    public void searchLastName(String lastName){

        boolean print = true;

        for (Contact contact : app.contacts) {

            if (contact.getLastName().toLowerCase().equals(lastName)) {

                System.out.println(" ");
                System.out.println("First contact that matches the last name " + lastName);
                contact.displayContact();
                print = false;
                break;
            }
        }

        if (print){

            System.out.println(" ");
            System.out.println("No contact found with " + lastName + " as their last name");
        }

    }

    public void searchAddress(String address){

        boolean[] contactFound = new boolean[App.contacts.length];
        boolean print = false;

        for (int i = 0; i < App.contacts.length; i++) {

            if(App.contacts[i].getAddress().toLowerCase().equals(address)){

                contactFound[i] = true;
                print = true;
            }
        }

        System.out.println(" ");
        if (print){

            System.out.println("All contacts that live on " + address);

            for (int i = 0; i < contactFound.length; i++) {

                if (contactFound[i]){

                    App.contacts[i].displayContact();
                }
            }
        }
        else{

            System.out.println("No contact found that lives on address " + address);
        }
    }

    public void freeSearch(String text){

        boolean[] contactMatching = new boolean[App.contacts.length];
        boolean anyMatch = false;

        for (int i = 0; i < App.contacts.length; i++) {

            contactMatching[i] = false;
        }

        for (int i = 0; i < App.contacts.length; i++) {

            if(App.contacts[i].getFirstName().toLowerCase().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        for (int i = 0; i < App.contacts.length; i++) {

            if(App.contacts[i].getLastName().toLowerCase().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        for (int i = 0; i < App.contacts.length; i++) {

            if(App.contacts[i].getAddress().toLowerCase().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        for (int i = 0; i < App.contacts.length; i++) {

            if(App.contacts[i].getNumber().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        for (int i = 0; i < App.contacts.length; i++) {

            if(App.contacts[i].getAge().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        if (!anyMatch) {

            System.out.println("No contact found matching your input");
        }
        else{

            System.out.println(" ");
            System.out.println("Here are all contacts matching the input text: ");
            System.out.println(" ");

            for (int i = 0; i < App.contacts.length; i++) {

                if(contactMatching[i]) {

                    App.contacts[i].displayContact();
                }
            }
        }
    }
}
