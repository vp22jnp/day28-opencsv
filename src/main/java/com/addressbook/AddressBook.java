package com.addressbook;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;
public class AddressBook {
    public enum IOService{CONSOLE_IO, FILE_IO, DATABASE_IO,  REST_IO}

    Scanner scanner = new Scanner(System.in);
    static List<Contact> contactList = new ArrayList<>();
    AddressBookManager addressBookManager = new AddressBookManager();

    public void createAddBook() {
        addressBookManager.createAddressBook();
    }
    public List<Contact> addContacts() {
        System.out.println("Add new Contact to ADDRESS BOOK:--");
        System.out.println("Available address books are: "+ addressBookManager.addressBookMap.keySet());

        System.out.println("Enter the AddressBook Name to which you want to add contacts: ");
        String addressBookName = scanner.next();
        System.out.println("enter the number of people you want to add in addressbook: ");
        int numberOfUser =scanner.nextInt();

        for (int i = 0; i < numberOfUser; i++) {
            Contact person = new Contact();
            System.out.println("First Name: ");
            String firstName = scanner.next();
            person.setFirstName(firstName);
            if (checkDuplicate(firstName)) {
                System.out.println("Person name already exist");
            } else {

                System.out.println("Last Name: ");
                String lastName = scanner.next();
                person.setLastName(lastName);

                System.out.println("Address: ");
                String address = scanner.next();
                person.setAddress(address);

                System.out.println("City: ");
                String city = scanner.next();
                person.setCity(city);

                System.out.println("State: ");
                String state = scanner.next();
                person.setState(state);

                System.out.println("EmailID: ");
                String email = scanner.next();
                person.setEmail(email);

                System.out.println("Zip: ");
                int zip = scanner.nextInt();
                person.setZip(zip);

                System.out.println("PhoneNumber: ");
                Long phoneNo = scanner.nextLong();
                person.setPhoneNumber(phoneNo);

                addressBookManager.checkAddressBookandaddContact(addressBookName, person);
                Contact contact = new Contact(firstName, lastName, address, city, state, email,
                        zip, phoneNo);
                contactList.add(person);

            }
        }
        return contactList;

    }
    public List<Contact> edit() {
        System.out.println("Enter the AddressBook Name to which you want to edit contacts: ");
        String addressBookName = scanner.next();

        System.out.println("Editing a contact");
        System.out.println("Enter the name of contact you want to edit: ");
        Scanner input = new Scanner(System.in);
        String editContact = input.next();
        for (Contact editPerson : contactList) {
            if (editPerson.getFirstName().equals(editContact)) {
                System.out.println("which field you want to edit :" + "\n" + "[1] : Edit firstName" + "\n" + "[2] : Edit LastName" + "\n" + "[3]: Edit Address" + "\n" + "[4] : Edit City " + "\n" + "[5]: Enter zipCode" + "\n" + "[6]: Enter MobileNo" + "\n" + "[7]: Enter EmailId");
                switch (input.nextInt()) {
                    case 1:
                        System.out.println("Change the First Name: ");
                        String editfName = input.next();
                        editPerson.setFirstName(editfName);
                        break;
                    case 2:
                        System.out.println("Change the Last Name: ");
                        String editlName = input.next();
                        editPerson.setLastName(editlName);
                        break;
                    case 3:
                        System.out.println("Change the Address: ");
                        String editAddress = input.next();
                        editPerson.setAddress(editAddress);
                        break;
                    case 4:
                        System.out.println("Change the City: ");
                        String editCity = input.next();
                        editPerson.setCity(editCity);
                        break;
                    case 5:
                        System.out.println("Change the zip Code: ");
                        int editZipCode = input.nextInt();
                        editPerson.setZip(editZipCode);
                        break;
                    case 6:
                        System.out.println("Change the Phone Number: ");
                        Long editPhoneNum = input.nextLong();
                        editPerson.setPhoneNumber(editPhoneNum);
                        break;
                    case 7:
                        System.out.println("Change the Email ID: ");
                        String editEmail = input.next();
                        editPerson.setEmail(editEmail);
                        break;
                }
                addressBookManager.editContact(addressBookName, editPerson);
                contactList.add(editPerson);

            }
        }
        return contactList;
    }

    public boolean deleteContact(String name) {
        int flag = 0;
        for (Contact contact : contactList) {
            if (contact.getFirstName().equals(name)) {
                contactList.remove(contact);
                System.out.println("Contact left are " + contactList);
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    public boolean checkDuplicate(String fname) {
        int flag = 0;
        for (Contact p : contactList) {
            if (p.getFirstName().equals(fname)) {
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    public void getPersonNameByState(String State) {
        List<Contact> details = contactList.stream().filter(contactName -> contactName.getState().equals(State))
                .collect(Collectors.toList());
        for (Contact contact : details) {
            for (Map.Entry<String, LinkedList> entry : addressBookManager.addressBookMap.entrySet()) {
                LinkedList value = entry.getValue();
                System.out.println("The Address Book: " + entry.getKey());
            }
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
        }
    }

    public void getPersonNameByCity(String cityName) {
        List<Contact> details2 = contactList.stream().filter(contactName -> contactName.getCity().equals(cityName))
                .collect(Collectors.toList());
        for (Contact contact : details2) {
            for (Map.Entry<String, LinkedList> entry : addressBookManager.addressBookMap.entrySet()) {
                LinkedList value = entry.getValue();
                System.out.println("The Address Book: " + entry.getKey());
            }
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
        }
    }
    public void countByCity(String city) {
        int countPersonInCity = 0;
        for (Map.Entry<String, LinkedList> entry : addressBookManager.addressBookMap.entrySet()) {
            for (int index = 0; index < contactList.size(); index++){
                if (contactList.get(index).getCity().equals(city)){
                    System.out.println(contactList.get(index) );
                    countPersonInCity = index;
                }
            }
        }
        System.out.println("Total number of people in this city " + city + ": " + countPersonInCity);
    }
    public void getContacts() {
        System.out.println("From which addressbook you want to find details?? ");
        String addressBookName = scanner.next();
        addressBookManager.getContactByAddressBook(addressBookName);
    }
    public void sortByName() {
        System.out.println("From which addressbook you want to find details?? ");
        String addressBookName = scanner.next();
        System.out.println("AddressBook name:" +addressBookName);
        List<Contact> sortedList= addressBookManager.addressBookMap.get(addressBookName);
        contactList.stream().sorted(Comparator.comparingInt(Contact::getZip)).collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }
    public void sortByZip() {
        System.out.println("From which addressbook you want to find details?? ");
        String addressBookName = scanner.next();
        System.out.println("AddressBook name:" +addressBookName);
        List<Contact> contactList= addressBookManager.addressBookMap.get(addressBookName);
        contactList.stream().sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList());
        contactList.forEach(System.out::println);
    }

    public void writeContactPersonData(IOService ioService) {
        if(ioService.equals(IOService.CONSOLE_IO))
            System.out.println("CONSOLE: " +contactList);
        else if(ioService.equals(IOService.FILE_IO))
            addressBookManager.writeDataInFile();
    }

}

