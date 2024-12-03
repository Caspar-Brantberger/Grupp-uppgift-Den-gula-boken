import java.util.Scanner;

public class DeleteFunctions {

    App app = new App();
    Scanner scanner = new Scanner(System.in);

    public void chooseContactToDelete(){

        CheckFunctions checkFunctions = new CheckFunctions();

        System.out.println(" ");
        System.out.println("Input corresponding number to contact you want to delete");
        System.out.println(" ");

        int index = 1;

        for (Contact c : app.contacts){

            System.out.print(index + ". ");
            c.displayContact();
            index++;
        }

        String userInput = checkFunctions.isNumber(scanner.nextLine());


        if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > app.contacts.length){

            System.out.println("Number not valid");
            chooseContactToDelete();
        }
        else{

            deleteContact(Integer.parseInt(userInput) - 1);
        }
    }

    private void deleteContact(int index){

        Contact[] newList = new Contact[app.contacts.length - 1];

        boolean indexFound = false;

        for (int i = 0; !(i >= app.contacts.length); i++) {

            if (i == index){
                indexFound = true;
            }

            if(!indexFound){
                newList[i] = app.contacts[i];
            }
            else if(i == newList.length){

                break;

            }else{

                newList[i] = app.contacts[i + 1];
            }
        }

        System.out.println(app.contacts[index].getFirstName() + " " + app.contacts[index].getLastName() + " has been deleted!");

        app.contacts = newList;
    }

}
