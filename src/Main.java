//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {

    static Contact[] contacts = new Contact[0];
    public static Scanner scanner = new Scanner(System.in);

    public static boolean runProgram = true;

    public static void main(String[] args) {


        createContact("Elin", "Jirefalk", "0739709078");
        createContact("Jonathan", "Jirefalk", "0767747162");
        createContact("Sven", "Eriksson", "07134578");

        while(runProgram) {
            userInput();
        }

    }

    public static void userInput(){
        System.out.println("What action would you like to do?");
        System.out.println("1. Display phonebook");
        System.out.println("2. Remove Contact");
        System.out.println("3. Create New Contact");
        System.out.println("4. Exit Program");

        switch(scanner.nextLine()){
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
                runProgram = false;
                break;
            default:
                System.out.println("Not a valid input");
        }
    }

    public static void  newContact(){

        System.out.println("Please insert firstname of contact");
        String firstName = scanner.nextLine();

        System.out.println("Please insert lastname of contact");
        String lastName = scanner.nextLine();

        System.out.println("Please insert phone number of contact");
        String phoneNumber = scanner.nextLine();

        createContact(firstName, lastName, phoneNumber);

        System.out.println("Contact Added!");
    }

    public static void createContact(String firstName, String lastName, String phoneNumber) {

        Contact[] oldList = contacts;

        contacts = new Contact[oldList.length + 1];

        for (int i = 0; i < oldList.length; i++) {

            contacts[i] = oldList[i];
        }

        contacts[oldList.length] = new Contact(firstName, lastName, phoneNumber);
    }

    public static void displayContacts(){

        for (Contact c : contacts){
            c.displayContact();
        }
    }

    public static void chooseContactToDelete(){

        int index = 1;

        for (Contact c : contacts){

            System.out.print(index + ". ");
            c.displayContact();
            index++;
        }

        System.out.println("Input corresponding number to contact you want to delete");

        int userInput = Integer.parseInt(scanner.nextLine());

        deleteContact(userInput - 1);
    }

    public static void deleteContact(int index){

        Contact[] newList = new Contact[contacts.length - 1];

        boolean indexFound = false;

        for (int i = 0; i < contacts.length; i++) {

            if (i == index){
                indexFound = true;
            }

            if(!indexFound){
                newList[i] = contacts[i];
            }
            else if(i == newList.length){

                break;

            }else{
                newList[i] = contacts[i + 1];
            }
        }

        contacts = newList;

        System.out.println("Contact Removed!");
    }
}




class Contact{

    private String firstName;

    private String lastName;

    private String number;

    public Contact(String firstName, String lastName, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getNumber(){
        return number;
    }



    public void setFirstName(String firstName){

        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public void displayContact(){
        System.out.println(firstName + " " + lastName + " " + number);
    }
}