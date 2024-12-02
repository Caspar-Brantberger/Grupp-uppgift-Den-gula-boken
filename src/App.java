import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    static Contact[] contacts = new Contact[0];
    public static Scanner scanner = new Scanner(System.in);

    public static boolean runProgram = true;

    public static boolean isAdmin = true;
    public static boolean isLoggedIn = false;



    public void app(){


        createContact("Elin", "Jirefalk", "0739709078", "20", "Tistelgatan 11");
        createContact("Jonathan", "Jirefalk", "0767747162", "23", "Tistelgatan 11");
        createContact("Elias", "Sjöstedt", "0706419359", "22", "Maskrosgatan 2");
        createContact("Caspar","Brantberger","07001231231","24","Nyköpingsvägen23");
       appLoop();
      

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
    public void appLoop(){
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

    public  void adminInput(){
        System.out.println("What action would you like to do?");
        System.out.println("1. Display phonebook");
        System.out.println("2. Remove Contact");
        System.out.println("3. Create New Contact");
        System.out.println("4. Search Contact");
        System.out.println("5. Save Contacts to File");
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
                saveContactsToFile("contacts.txt");
                break;
            case "6":
                isAdmin = false;
                isLoggedIn = false;
                break;
            default:
                System.out.println("Not a valid input");
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
    public static void searchContact(){

        System.out.println("enter firstname or lastname.");
        String x = scanner.nextLine().toLowerCase();
        boolean found = false;

        for(Contact contact : contacts){
            if (contact.getFirstName().toLowerCase().contains(x) || contact.getLastName().toLowerCase().contains(x)){
                contact.displayContact();
                found = true;
            }
        }
        if(!found){
            System.out.println("could not find " + x);
        }
    }

    public  void  newContact(){

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

    public void createContact(String firstName, String lastName, String phoneNumber, String age, String address) {

        Contact[] oldList = contacts;

        contacts = new Contact[oldList.length + 1];

        for (int i = 0; i < oldList.length; i++) {

            contacts[i] = oldList[i];
        }

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

    public static void saveContactsToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Contact contact : contacts) {
                writer.write(contact.getFirstName() + "," + contact.getLastName() + "," +
                        contact.getNumber() + "," + contact.getAge() + "," + contact.getAddress() + "\n");
            }
            System.out.println("Contacts saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
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
}
