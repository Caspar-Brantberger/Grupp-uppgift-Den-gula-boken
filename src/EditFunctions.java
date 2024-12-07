import java.util.Scanner;

public class EditFunctions {

    Scanner scanner = new Scanner(System.in);
    App app = new App();

    CheckFunctions checkFunctions = new CheckFunctions();

    public void editContact(){

        System.out.println(" ");
        System.out.println("Which contact would you like to edit?");
        System.out.println(" ");


        for (int i = 0; i < App.contacts.length; i++) {

            System.out.print((i + 1) + ". ");
            App.contacts[i].displayContact();
        }

        String userInput = checkFunctions.isNumber(scanner.nextLine());

        if(Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > App.contacts.length){

            System.out.println("Number not valid");
            editContact();
        }
        else{

            chosenContactToEdit(Integer.parseInt(userInput) - 1);
        }
    }

    private void chosenContactToEdit(int index){

        System.out.println(" ");

        System.out.println(App.contacts[index].getFirstName() + " " + App.contacts[index].getLastName() + " is chosen for editing!");
        System.out.println("What property would you like to edit?");

        System.out.println(" ");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone Number");
        System.out.println("4. Age");
        System.out.println("5. Address");

        switch(scanner.nextLine()){
            case "1":
                System.out.println("What would you like to replace " + App.contacts[index].getFirstName() + " with?");
                App.contacts[index].setFirstName(scanner.nextLine());
                break;
            case "2":
                System.out.println("What would you like to replace " + App.contacts[index].getLastName() + " with?");
                App.contacts[index].setLastName(scanner.nextLine());
                break;
            case "3":
                System.out.println("What would you like to replace " + App.contacts[index].getNumber() + " with?");
                App.contacts[index].setNumber(checkFunctions.isNumber(scanner.nextLine()));
                break;
            case "4":
                System.out.println("What would you like to replace " + App.contacts[index].getAge() + " with?");
                App.contacts[index].setAge(checkFunctions.isNumber(scanner.nextLine()));
                break;
            case "5":
                System.out.println("What would you like to replace " + App.contacts[index].getAddress() + " with?");
                App.contacts[index].setAddress(scanner.nextLine());
                break;
            default:
                System.out.println("Not a valid input!");
                chosenContactToEdit(index);
        }

        System.out.println(" ");
        System.out.println("Contact updated successfully!");
        System.out.print("Updated contact info: ");

        App.contacts[index].displayContact();
    }
}
