import java.io.*;
import java.util.ArrayList;
import java.util.List;
//contact storage
public class ContactStorage {

    private static final String FILE_NAME = "contacts.txt";

    public void saveContacts(Contact[] contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts) {
                writer.write(contact.getFirstName() + "," +
                        contact.getLastName() + "," +
                        contact.getNumber() + "," +
                        contact.getAge() + "," +
                        contact.getAddress());
                writer.newLine();
            }
            System.out.println("Contacts have been saved to " + FILE_NAME + ".");
        } catch (IOException e) {
            System.out.println("An error occurred while saving contacts: " + e.getMessage());
        }
    }

    public Contact[] loadContacts() {
        List<Contact> contactList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    contactList.add(new Contact(data[0], data[1], data[2], data[3], data[4]));
                }
            }
            System.out.println("Contacts have been loaded from " + FILE_NAME + ".");
        } catch (IOException e) {
            System.out.println("An error occurred while loading contacts: " + e.getMessage());
        }
        return contactList.toArray(new Contact[0]);
    }
}
