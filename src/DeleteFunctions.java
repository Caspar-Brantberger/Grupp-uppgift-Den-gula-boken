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

        for (Contact c : App.contacts){

            System.out.print(index + ". ");
            c.displayContact();
            index++;
        }

        String userInput = checkFunctions.isNumber(scanner.nextLine());


        if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > App.contacts.length){

            System.out.println("Number not valid");
            chooseContactToDelete();
        }
        else{

            deleteContact(Integer.parseInt(userInput) - 1);
        }
    }

    private void deleteContact(int index){

        Contact[] newList = new Contact[App.contacts.length - 1];

        boolean indexFound = false;

        for (int i = 0; !(i >= App.contacts.length); i++) {

            if (i == index){
                indexFound = true;
            }

            if(!indexFound){
                newList[i] = App.contacts[i];
            }
            else if(i == newList.length){

                break;

            }else{

                newList[i] = App.contacts[i + 1];
            }
        }

        System.out.println(App.contacts[index].getFirstName() + " " + App.contacts[index].getLastName() + " has been deleted!");

        App.contacts = newList;
    }

}
