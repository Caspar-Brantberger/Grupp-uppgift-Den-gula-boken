import java.util.Scanner;

public class Input {
    FileSearch fh = new FileSearch();
    Contact cs = new Contact();
    Search sh = new Search();
    Input in = new Input();
    Scanner scanner = new Scanner(System.in);
    App app = new App();

    public static boolean isAdmin = true;
    public static boolean isLoggedIn = false;

    public Input() {
        this.in = in;
    }

    public Input getIn() {
        return in;
    }


    public  void adminInput(){

        System.out.println(" ");
        System.out.println("What action would you like to do?");
        System.out.println("1. Display phonebook");
        System.out.println("2. Remove Contact");
        System.out.println("3. Create New Contact");
        System.out.println("4. Search Contact");
        System.out.println("5. Edit Existing Contact");
        System.out.println("6. Logout");

        switch(scanner.nextLine()){
            case "1":

                if (cs.contacts.length == 0){
                    System.out.println("Contact list is empty!");
                    break;
                }
                cs.displayContacts();
                break;
            case "2":

                if (Contact.contacts.length == 0){
                    System.out.println("Contact list is empty!");
                    break;
                }

                cs.chooseContactToDelete();
                break;
            case "3":
                cs.newContact();
                break;
            case "4":
                if (Contact.contacts.length == 0){
                    System.out.println("Contact list is empty!");
                    break;
                }

                sh.searchContact();
                break;
            case "5":
                fh.saveContactsToFile("contacts.txt");
                if (Contact.contacts.length == 0){
                    System.out.println("Contact list is empty!");
                    break;
                }

                cs.editContact();
                break;
            case "6":
                isAdmin = false;
                isLoggedIn = false;
                break;
            default:
                System.out.println("Not a valid input");
        }
    }
    public void guestInput(){
        System.out.println(" ");
        System.out.println("What action would you like to do?");
        System.out.println("1. Display phonebook");
        System.out.println("2. Search Contact");
        System.out.println("3. Logout");

        switch(scanner.nextLine()){
            case "1":
                cs.displayContacts();
                break;
            case "2":
                sh.searchContact();
                break;
            case "3":
                isLoggedIn = false;
                isAdmin = false;
                break;
            default:
                System.out.println("Not a valid input");
        }
    }
}
