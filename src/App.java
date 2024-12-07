public class App {
    static Contact[] contacts = new Contact[0];
    static LoginFunctions loginFunctions = new LoginFunctions();
    static CreateFunctions createFunctions = new CreateFunctions();
    static ContactStorage contactStorage = new ContactStorage();

    public void run() {
        contacts = contactStorage.loadContacts();
        System.out.println("Loading existing contacts...");

//        addNewContact("Elin", "Jirefalk", "0739709078", "20", "Tistelgatan 11");
//        addNewContact("Jonathan", "Jirefalk", "0767747162", "23", "Tistelgatan 11");
//        addNewContact("Elias", "Sjöstedt", "0706419359", "22", "Maskrosgatan 2");
        System.out.println("Contacts after adding initial ones:");
        displayContacts();

        while (loginFunctions.runProgram) {
            while (!loginFunctions.isLoggedIn) {
                loginFunctions.loginPrompt();
            }

            if (loginFunctions.isAdmin && loginFunctions.runProgram) {
                loginFunctions.adminInput();
            } else if (loginFunctions.runProgram) {
                loginFunctions.guestInput();
            }
        }
    }
    public void addNewContact(String firstName, String lastName, String phoneNumber, String age, String address) {
        Contact newContact = new Contact(firstName, lastName, phoneNumber, age, address);
        Contact[] updatedContacts = new Contact[contacts.length + 1];
        System.arraycopy(contacts, 0, updatedContacts, 0, contacts.length);
        updatedContacts[contacts.length] = newContact;
        contacts = updatedContacts;
        contactStorage.saveContacts(contacts);
    }
    public void displayContacts() {
        System.out.println("Contacts list size: " + contacts.length);
        if (contacts.length == 0) {
            System.out.println("No contacts to display.");
        } else {
            for (Contact c : contacts) {
                c.displayContact();
            }
        }
    }
}