import java.util.Scanner;

public class EditFunctions {

    Scanner scanner = new Scanner(System.in);
    App app = new App();

    CheckFunctions checkFunctions = new CheckFunctions();

    public void editContact(){

        System.out.println(" ");
        System.out.println("Which contact would you like to edit?");
        System.out.println(" ");


        for (int i = 0; i < app.contacts.length; i++) {

            System.out.print((i + 1) + ". ");
            app.contacts[i].displayContact();
        }

        String userInput = checkFunctions.isNumber(scanner.nextLine());

        if(Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > app.contacts.length){

            System.out.println("Number not valid");
            editContact();
        }
        else{

            chosenContactToEdit(Integer.parseInt(userInput) - 1);
        }
    }

    private void chosenContactToEdit(int index){

        System.out.println(" ");

        System.out.println(app.contacts[index].getFirstName() + " " + app.contacts[index].getLastName() + " is chosen for editing!");
        System.out.println("What property would you like to edit?");

        System.out.println(" ");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone Number");
        System.out.println("4. Age");
        System.out.println("5. Address");

        switch(scanner.nextLine()){
            case "1":
                System.out.println("What would you like to replace " + app.contacts[index].getFirstName() + " with?");
                app.contacts[index].setFirstName(scanner.nextLine());
                break;
            case "2":
                System.out.println("What would you like to replace " + app.contacts[index].getLastName() + " with?");
                app.contacts[index].setLastName(scanner.nextLine());
                break;
            case "3":
                System.out.println("What would you like to replace " + app.contacts[index].getNumber() + " with?");
                app.contacts[index].setNumber(checkFunctions.isNumber(scanner.nextLine()));
                break;
            case "4":
                System.out.println("What would you like to replace " + app.contacts[index].getAge() + " with?");
                app.contacts[index].setAge(checkFunctions.isNumber(scanner.nextLine()));
                break;
            case "5":
                System.out.println("What would you like to replace " + app.contacts[index].getAddress() + " with?");
                app.contacts[index].setAddress(scanner.nextLine());
                break;
            default:
                System.out.println("Not a valid input!");
                chosenContactToEdit(index);
        }

        System.out.println(" ");
        System.out.println("Contact updated successfully!");
        System.out.print("Updated contact info: ");

        app.contacts[index].displayContact();
    }
}
