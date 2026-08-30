import java.util.*;

public class ContactManager {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = new HashMap<>();

        // Step 4: add contacts here
        contacts.put("Ada Lovelace", new Contact("Ada Lovelace", "+1 617 555 0101"));
        contacts.put("Bruno Mars", new Contact("Bruno Mars", "+1 617 555 0102"));
        contacts.put("Taylor Swift", new Contact("Taylor Swift", "+1 617 555 0103"));
        contacts.put("Justin Timberlake", new Contact("Justin Timberlake", "+1 617 555 0104"));
        contacts.put("Britney Spears", new Contact("Britney Spears", "+1 617 555 0105"));

        // Step 5: look up a contact
        Contact found = contacts.get("Ada Lovelace");
        if (found == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println(found);
        }

        Contact notFound = contacts.get("Isaac Newton");
        if (notFound == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println(notFound);
        }

        // Step 6: print sorted list
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("=== All Contacts ===");
        for (Contact c : sorted) {
            System.out.println(c);
        }
    }
}