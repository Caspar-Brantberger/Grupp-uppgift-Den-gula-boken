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
            System.out.println("User: " + userName[indexOfName]);
            System.out.println("Please insert password: ");

            while(!scanner.nextLine().equals(password[indexOfName])) {

                System.out.println("Password incorrect, please try again!");
            }

            System.out.println("Successfully logged in as " + userName[indexOfName] + "!");
            isAdmin = true;
            isLoggedIn = true;
        }
    }

    public static void adminInput(){
        System.out.println("What action would you like to do?");
        System.out.println("1. Display phonebook");
        System.out.println("2. Remove Contact");
        System.out.println("3. Create New Contact");
        System.out.println("4. Search Contact");
        System.out.println("5. Edit Existing Contact");
        System.out.println("6. Logout");

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
                searchContact();
                break;
            case "5":
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

        System.out.println("Which contact would you like to edit?");


        for (int i = 0; i < contacts.length; i++) {

            System.out.print((i + 1) + ". ");
            contacts[i].displayContact();
        }

        int contactToEditIndex = (Integer.parseInt(scanner.nextLine()) - 1);

        System.out.println(contacts[contactToEditIndex].getFirstName() + " " + contacts[contactToEditIndex].getLastName() + " is chosen for editing!");
        System.out.println("What property would you like to edit?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone Number");
        System.out.println("4. Age");
        System.out.println("5. Address");

        switch(scanner.nextLine()){
            case "1":
                System.out.println("What would you like to replace " + contacts[contactToEditIndex].getFirstName() + " with?");
                contacts[contactToEditIndex].setFirstName(scanner.nextLine());
                break;
            case "2":
                System.out.println("What would you like to replace " + contacts[contactToEditIndex].getLastName() + " with?");
                contacts[contactToEditIndex].setLastName(scanner.nextLine());
                break;
            case "3":
                System.out.println("What would you like to replace " + contacts[contactToEditIndex].getNumber() + " with?");
                contacts[contactToEditIndex].setNumber(scanner.nextLine());
                break;
            case "4":
                System.out.println("What would you like to replace " + contacts[contactToEditIndex].getAge() + " with?");
                contacts[contactToEditIndex].setAge(scanner.nextLine());
                break;
            case "5":
                System.out.println("What would you like to replace " + contacts[contactToEditIndex].getAddress() + " with?");
                contacts[contactToEditIndex].setAddress(scanner.nextLine());
                break;
            default:
                System.out.println("Not a valid input!");
                scanner.nextLine();
        }

        System.out.println("Contact updated successfully!");
        System.out.print("Updated contact info: ");
        contacts[contactToEditIndex].displayContact();
    }

    public static void searchContact(){
        System.out.println("What attribute would you like to search?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Address");
        System.out.println("4. Free Search");

        switch(scanner.nextLine()){
            case "1":
                System.out.println("Enter first name: ");
                searchFirstName(scanner.nextLine());
                break;
            case "2":
                System.out.println("Enter last name: ");
                searchLastName(scanner.nextLine());
                break;
            case "3":
                System.out.println("Enter address: ");
                searchAddress(scanner.nextLine());
                break;
            case "4":
                System.out.println("Enter any text: ");
                freeSearch(scanner.nextLine());
                break;
            default:
                System.out.println("Not a valid input!");
                scanner.nextLine();
        }

    }

    public static void searchFirstName(String firstName){

        System.out.println("All contacts that have the first name " + firstName);
        for (Contact contact : contacts) {

            if (contact.getFirstName().equals(firstName)) {

                contact.displayContact();
            }
        }
    }

    public static void searchLastName(String lastName){

        System.out.println("First contact that matches the last name " + lastName);
        for (Contact contact : contacts) {

            if (contact.getLastName().equals(lastName)) {

                contact.displayContact();
                break;
            }
        }
    }

    public static void searchAddress(String address){

        System.out.println("All contacts that live on " + address);
        for (Contact contact : contacts) {

            if (contact.getAddress().equals(address)) {

                contact.displayContact();
            }
        }
    }

    public static void freeSearch(String text){

        boolean[] contactMatching = new boolean[contacts.length];

        for (int i = 0; i < contacts.length; i++) {

            contactMatching[i] = false;
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getFirstName().contains(text)) {

                contactMatching[i] = true;

            }
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getLastName().contains(text)) {

                contactMatching[i] = true;
            }
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getAddress().contains(text)) {

                contactMatching[i] = true;
            }
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getNumber().contains(text)) {

                contactMatching[i] = true;
            }
        }

        for (int i = 0; i < contacts.length; i++) {

            if(contacts[i].getAge().contains(text)) {

                contactMatching[i] = true;
            }
        }

        System.out.println("Here are all contacts matching the input text: ");
        for (int i = 0; i < contacts.length; i++) {

            if(contactMatching[i]) {

                contacts[i].displayContact();
            }
        }
    }

    public static void guestInput(){
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

    public static void  newContact(){

        System.out.println("Please insert firstname of contact");
        String firstName = scanner.nextLine();

        System.out.println("Please insert lastname of contact");
        String lastName = scanner.nextLine();

        System.out.println("Please insert phone number of contact");
        String phoneNumber = scanner.nextLine();

        System.out.println("Please insert age of contact");
        String age = scanner.nextLine();

        System.out.println("Please insert address of contact");
        String address = scanner.nextLine();

        createContact(firstName, lastName, phoneNumber, age, address);

        System.out.println("Contact Added!");
    }

    public static void createContact(String firstName, String lastName, String phoneNumber, String age, String address) {

        Contact[] oldList = contacts;

        contacts = new Contact[oldList.length + 1];

        System.arraycopy(oldList, 0, contacts, 0, oldList.length);

        contacts[oldList.length] = new Contact(firstName, lastName, phoneNumber,age, address);
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