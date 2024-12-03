import java.io.FileWriter;
import java.io.IOException;

public class FileSearch {
        public static void saveContactsToFile(String fileName) {
            try (FileWriter writer = new FileWriter(fileName)) {
                for (Contact contact : Contact.contacts) {
                    writer.write(contact.getFirstName() + "," + contact.getLastName() + "," +
                            contact.getNumber() + "," + contact.getAge() + "," + contact.getAddress() + "\n");
                }
                System.out.println("Contacts saved to " + fileName);
            } catch (IOException e) {
                System.out.println("Error saving contacts to file: " + e.getMessage());
            }
        }

}
