import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ContactStorageTxt implements ContactsStorageInterface {
    private String fileName;

    public ContactStorageTxt(String fileName) {
        this.fileName = fileName;
    }

    public List<Contact> loadContacts(){
        List<Contact> contactsList = new ArrayList<Contact>();

        try {
            File file = new File(this.fileName);
            Scanner scanner = new Scanner(file);

            String[] contactInfo = {};
            while (scanner.hasNextLine()) {
                contactInfo = scanner.nextLine().split("\t");
                Contact c = new Contact(contactInfo[0], Integer.parseInt(contactInfo[1]));
                contactsList.add(c);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error has ocurred: file doesn't exist.");
            return null;
        }
        return contactsList;
    }

    public boolean saveContacts(List<Contact> list) {
        String data = "";
        for (Contact contact : list){
            data = data + contact.toString() + "\n";
        }
        try {
            Path filePath = Paths.get(this.fileName);
            BufferedWriter bwriter = new BufferedWriter(new FileWriter(filePath.toString()));
            bwriter.write(data);
            bwriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred: couldn't write to file.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}