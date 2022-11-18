package com.addressbook;
import java.util.*;
import java.util.stream.Collectors;
public class AddressBookManager {
    AddressBookFileIOService addressBookFileIOService = new AddressBookFileIOService();
    Map<String, LinkedList> addressBookMap = new HashMap<String, LinkedList>();
    Scanner scanner = new Scanner(System.in);


    public void createAddressBook() {
        System.out.println("Enter the name of a AddressBook: ");
        String addressBookName = scanner.next();
        if (addressBookMap.containsKey(addressBookName))
            System.out.println("AddressBook is already present");
        else
            addressBookMap.put(addressBookName, new LinkedList<Contact>());
        System.out.println(addressBookMap.keySet());
    }

    public boolean checkAddressBookandaddContact(String addressBookName, Contact obj) {
        if (addressBookMap.containsKey(addressBookName) == false) {
            throw new RuntimeException("AddressBook not present");
        }
        System.out.println(obj + "AddressBook name: " + addressBookName);
        addressBookMap.get(addressBookName).add(obj);
        return true;
    }

    public boolean editContact(String addressBookName, Contact obj) {
        if (addressBookMap.containsKey(addressBookName) == false) {
            throw new RuntimeException("AddressBook not present");
        }
        System.out.println(obj + "AddressBook name: " + addressBookName);
        addressBookMap.get(addressBookName).add(obj);
        return true;
    }

    public List getContactByAddressBook(String addressBookName) {
        List contacts = addressBookMap.get(addressBookName);
        contacts.forEach(contact -> System.out.println(contact));
        return contacts;
    }
    public void writeDataInFile(){
        addressBookMap.forEach((addressBookName,contacts)->addressBookFileIOService.writeData(contacts,addressBookName));
    }

}

