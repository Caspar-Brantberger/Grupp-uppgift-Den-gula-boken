import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    static Contact[] contacts = new Contact[0];
    public static Scanner scanner = new Scanner(System.in);

    public static boolean runProgram = true;

    public static boolean isAdmin = true;
    public static boolean isLoggedIn = false;

    public static void main(String[] args) {


        createContact("Elin", "Jirefalk", "0739709078", "20", "Tistelgatan 11");
        createContact("Jonathan", "Jirefalk", "0767747162", "23", "Tistelgatan 11");
        createContact("Elias", "Sjöstedt", "0706419359", "22", "Maskrosgatan 2");

        while(runProgram) {

            while(!isLoggedIn) {

                loginPrompt();
            }

            if(isAdmin && runProgram) {

                adminInput();
            }
            else if(runProgram) {

                guestInput();
            }
        }

    }

    public static void loginPrompt(){

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

    public static void adminLogin(){

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

    public static void adminInput(){

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

                if (contacts.length == 0){
                    System.out.println("Contact list is empty!");
                    break;
                }

                displayContacts();
                break;
            case "2":

                if (contacts.length == 0){
                    System.out.println("Contact list is empty!");
                    break;
                }

                chooseContactToDelete();
                break;
            case "3":
                newContact();
                break;
            case "4":
                if (contacts.length == 0){
                    System.out.println("Contact list is empty!");
                    break;
                }

                searchContact();
                break;
            case "5":
                if (contacts.length == 0){
                    System.out.println("Contact list is empty!");
                    break;
                }

                editContact();
                break;
            case "6":
                isAdmin = false;
                isLoggedIn = false;
                break;
            default:
                System.out.println("Not a valid input");
        }
    }

    public static void editContact(){

        System.out.println(" ");
        System.out.println("Which contact would you like to edit?");
        System.out.println(" ");


        for (int i = 0; i < contacts.length; i++) {

            System.out.print((i + 1) + ". ");
            contacts[i].displayContact();
        }

        String userInput = isNumber(scanner.nextLine());

        if(Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > contacts.length){

            System.out.println("Number not valid");
            editContact();
        }
        else{

            chosenContactToEdit(Integer.parseInt(userInput) - 1);
        }
    }

    public static void chosenContactToEdit(int index){

        System.out.println(" ");

        System.out.println(contacts[index].getFirstName() + " " + contacts[index].getLastName() + " is chosen for editing!");
        System.out.println("What property would you like to edit?");

        System.out.println(" ");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone Number");
        System.out.println("4. Age");
        System.out.println("5. Address");

        switch(scanner.nextLine()){
            case "1":
                System.out.println("What would you like to replace " + contacts[index].getFirstName() + " with?");
                contacts[index].setFirstName(scanner.nextLine());
                break;
            case "2":
                System.out.println("What would you like to replace " + contacts[index].getLastName() + " with?");
                contacts[index].setLastName(scanner.nextLine());
                break;
            case "3":
                System.out.println("What would you like to replace " + contacts[index].getNumber() + " with?");
                contacts[index].setNumber(isNumber(scanner.nextLine()));
                break;
            case "4":
                System.out.println("What would you like to replace " + contacts[index].getAge() + " with?");
                contacts[index].setAge(isNumber(scanner.nextLine()));
                break;
            case "5":
                System.out.println("What would you like to replace " + contacts[index].getAddress() + " with?");
                contacts[index].setAddress(scanner.nextLine());
                break;
            default:
                System.out.println("Not a valid input!");
                chosenContactToEdit(index);
        }

        System.out.println(" ");
        System.out.println("Contact updated successfully!");
        System.out.print("Updated contact info: ");
        contacts[index].displayContact();
    }

    public static void searchContact(){

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

    public static void searchFirstName(String firstName){


        boolean[] contactFound = new boolean[contacts.length];
        boolean print = false;

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getFirstName().toLowerCase().equals(firstName)){

                contactFound[i] = true;
                print = true;
            }
        }

        if (print){

            System.out.println(" ");
            System.out.println("All contacts that have the first name " + firstName);

            for (int i = 0; i < contactFound.length; i++) {

                if (contactFound[i]){

                    contacts[i].displayContact();
                }
            }
        }
        else{

            System.out.println(" ");
            System.out.println("No contact found with first name " + firstName);
        }
    }

    public static void searchLastName(String lastName){

        boolean print = true;

        for (Contact contact : contacts) {

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

    public static void searchAddress(String address){

        boolean[] contactFound = new boolean[contacts.length];
        boolean print = false;

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getAddress().toLowerCase().equals(address)){

                contactFound[i] = true;
                print = true;
            }
        }

        if (print){

            System.out.println(" ");
            System.out.println("All contacts that live on " + address);

            for (int i = 0; i < contactFound.length; i++) {

                if (contactFound[i]){

                    contacts[i].displayContact();
                }
            }
        }
        else{

            System.out.println(" ");
            System.out.println("No contact found that lives on address " + address);
        }
    }

    public static void freeSearch(String text){

        boolean[] contactMatching = new boolean[contacts.length];
        boolean anyMatch = false;

        for (int i = 0; i < contacts.length; i++) {

            contactMatching[i] = false;
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getFirstName().toLowerCase().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getLastName().toLowerCase().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getAddress().toLowerCase().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getNumber().contains(text)) {

                contactMatching[i] = true;
                anyMatch = true;
            }
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getAge().contains(text)) {

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

            for (int i = 0; i < contacts.length; i++) {

                if(contactMatching[i]) {

                    contacts[i].displayContact();
                }
            }
        }

    }

    public static void guestInput(){
        System.out.println(" ");
        System.out.println("What action would you like to do?");
        System.out.println("1. Display phonebook");
        System.out.println("2. Search Contact");
        System.out.println("3. Logout");

        switch(scanner.nextLine()){
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

    public static String isNumber(String user){

        while (!user.matches("(\\d*)") || user.isEmpty()){
            System.out.println(" ");
            System.out.println("Invalid input, please insert a number");
            user = scanner.nextLine();
        }

        return user;
    }

    public static void newContact(){

        System.out.println(" ");
        System.out.println("Please insert firstname of contact");
        String firstName = scanner.nextLine();

        System.out.println(" ");
        System.out.println("Please insert lastname of contact");
        String lastName = scanner.nextLine();

        System.out.println(" ");
        System.out.println("Please insert phone number of contact");
        String phoneNumber = isNumber(scanner.nextLine());

        System.out.println(" ");
        System.out.println("Please insert age of contact");
        String age = isNumber(scanner.nextLine());


        System.out.println(" ");
        System.out.println("Please insert address of contact");
        String address = scanner.nextLine();

        createContact(firstName, lastName, phoneNumber, age, address);
        System.out.println(" ");
        System.out.println("Contact Added!");
    }

    public static void createContact(String firstName, String lastName, String phoneNumber, String age, String address) {

        Contact[] oldList = contacts;

        contacts = new Contact[oldList.length + 1];

        for (int i = 0; i < oldList.length; i++) {

            contacts[i] = oldList[i];
        }

        contacts[oldList.length] = new Contact(firstName, lastName, phoneNumber,age, address);
    }

    public static void displayContacts(){

        System.out.println(" ");

        for (Contact c : contacts){
            c.displayContact();
        }
    }

    public static void chooseContactToDelete(){

        System.out.println(" ");
        System.out.println("Input corresponding number to contact you want to delete");
        System.out.println(" ");

        int index = 1;

        for (Contact c : contacts){

            System.out.print(index + ". ");
            c.displayContact();
            index++;
        }

        String userInput = isNumber(scanner.nextLine());


        if (Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > contacts.length){

            System.out.println("Number not valid");
            chooseContactToDelete();
        }
        else{

            deleteContact(Integer.parseInt(userInput) - 1);
        }
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

        System.out.println(contacts[index].getFirstName() + " " + contacts[index].getLastName() + " has been deleted!");

        contacts = newList;
    }
}

class Contact{

    private String firstName;

    private String lastName;

    private String number;

    private String age;

    private String address;


    public Contact(String firstName, String lastName, String number, String age, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.age = age;
        this.address = address;
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

    public String getAge(){
        return age;
    }

    public String getAddress(){
        return address;
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

    public void setAge(String age){
        this.age = age;
    }

    public void setAddress(String address){
        this.address = address;
    }


    public void displayContact(){
        System.out.println(firstName + " " + lastName + " " + number + " " + age + " years " + address);
    }
}