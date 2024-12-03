import java.util.Scanner;

public class LoginFunctions{

    Scanner scanner = new Scanner(System.in);

    App app = new App();
    CheckFunctions checkFunctions = new CheckFunctions();
    DeleteFunctions deleteFunctions = new DeleteFunctions();
    EditFunctions editFunctions = new EditFunctions();
    CreateFunctions createFunctions = new CreateFunctions();
    SearchFunctions searchFunctions = new SearchFunctions();

    boolean runProgram = true;
    boolean isAdmin = true;
    boolean isLoggedIn = false;

    public void loginPrompt(){

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


    public void adminLogin(){

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

    public void adminInput(){

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
                if (!checkFunctions.isEmpty()) {
                    app.displayContacts();
                }
                break;
            case "2":
                if (!checkFunctions.isEmpty()) {
                    deleteFunctions.chooseContactToDelete();
                }
                break;
            case "3":
                createFunctions.newContact();
                break;
            case "4":
                if (!checkFunctions.isEmpty()) {
                    searchFunctions.searchContact();
                }
                break;
            case "5":
                if (!checkFunctions.isEmpty()) {
                    editFunctions.editContact();
                }
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
                app.displayContacts();
                break;
            case "2":
                searchFunctions.searchContact();
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
