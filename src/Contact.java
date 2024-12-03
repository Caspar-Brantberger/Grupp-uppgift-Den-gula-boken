import java.util.Scanner;

public class Contact {

    static Scanner scanner = new Scanner(System.in);
    static Contact[] contacts = new Contact[0];



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

    public Contact() {
        this.firstName = "";
        this.lastName = "";
        this.number = "";
        this.age = "";
        this.address = "";
    }


    public void getContact (String elin, String jirefalk, String number, String number1, String s){
        }

        public String getFirstName () {
            return firstName;
        }

        public String getLastName () {
            return lastName;
        }

        public String getNumber () {
            return number;
        }

        public String getAge () {
            return age;
        }

        public String getAddress () {
            return address;
        }


        public void setFirstName (String firstName){

            this.firstName = firstName;
        }

        public void setLastName (String lastName){
            this.lastName = lastName;
        }

        public void setNumber (String number){
            this.number = number;
        }

        public void setAge (String age){
            this.age = age;
        }

        public void setAddress (String address){
            this.address = address;
        }
        public void displayContact () {
            System.out.println(firstName + " " + lastName + " " + number + " " + age + " years " + address);
        }
        public void editContact () {

            System.out.println(" ");
            System.out.println("Which contact would you like to edit?");
            System.out.println(" ");


            for (int i = 0; i < contacts.length; i++) {

                System.out.print((i + 1) + ". ");
                contacts[i].displayContact();
            }

            String userInput = scanner.nextLine();

            if (Integer.parseInt(App.isNumber(userInput)) < 1 || Integer.parseInt(App.isNumber(userInput)) > contacts.length) {

                System.out.println("Number not valid");
                editContact();
            } else {

                chosenContactToEdit(Integer.parseInt(userInput) - 1);
            }
        }
        public void chosenContactToEdit ( int index){

            System.out.println(" ");

            System.out.println(contacts[index].getFirstName() + " " + contacts[index].getLastName() + " is chosen for editing!");
            System.out.println("What property would you like to edit?");

            System.out.println(" ");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Phone Number");
            System.out.println("4. Age");
            System.out.println("5. Address");

            switch (scanner.nextLine()) {
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
                    contacts[index].setNumber(App.isNumber(scanner.nextLine()));
                    break;
                case "4":
                    System.out.println("What would you like to replace " + contacts[index].getAge() + " with?");
                    contacts[index].setAge(App.isNumber(scanner.nextLine()));
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

        public void newContact () {

            System.out.println(" ");
            System.out.println("Please insert firstname of contact");
            String firstName = scanner.nextLine();

            System.out.println(" ");
            System.out.println("Please insert lastname of contact");
            String lastName = scanner.nextLine();

            System.out.println(" ");
            System.out.println("Please insert phone number of contact");
            String phoneNumber = App.isNumber(scanner.nextLine());

            System.out.println(" ");
            System.out.println("Please insert age of contact");
            String age = App.isNumber(scanner.nextLine());


            System.out.println(" ");
            System.out.println("Please insert address of contact");
            String address = scanner.nextLine();

            createContact(firstName, lastName, phoneNumber, age, address);
            System.out.println(" ");
            System.out.println("Contact Added!");
        }

        public void createContact (String firstName, String lastName, String phoneNumber, String age, String address){

            Contact[] oldList = contacts;

            contacts = new Contact[oldList.length + 1];

            for (int i = 0; i < oldList.length; i++) {

                contacts[i] = oldList[i];
            }

            contacts[oldList.length] = new Contact(firstName, lastName, phoneNumber, age, address);
        }
        public void displayContacts () {

            System.out.println(" ");

            for (Contact c : contacts) {
                c.displayContact();
            }
        }
        public void chooseContactToDelete () {

            System.out.println(" ");
            System.out.println("Input corresponding number to contact you want to delete");
            System.out.println(" ");

            int index = 1;

            for (Contact c : contacts) {

                System.out.print(index + ". ");
                c.displayContact();
                index++;
            }

            String userInput = scanner.nextLine();


            if (Integer.parseInt(App.isNumber(userInput)) < 1 || Integer.parseInt(App.isNumber(userInput)) > contacts.length) {

                System.out.println("Number not valid");
                chooseContactToDelete();
            } else {

                deleteContact(Integer.parseInt(userInput) - 1);
            }
        }
        public static void deleteContact ( int index){

            Contact[] newList = new Contact[contacts.length - 1];

            boolean indexFound = false;

            for (int i = 0; i < contacts.length; i++) {

                if (i == index) {
                    indexFound = true;
                }

                if (!indexFound) {
                    newList[i] = contacts[i];
                } else if (i == newList.length) {

                    break;

                } else {

                    newList[i] = contacts[i + 1];
                }
            }

            System.out.println(contacts[index].getFirstName() + " " + contacts[index].getLastName() + " has been deleted!");

            contacts = newList;
        }






}

