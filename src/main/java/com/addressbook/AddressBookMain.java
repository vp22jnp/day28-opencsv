package com.addressbook;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.Scanner;
public class AddressBookMain {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("WELCOME TO ADDRESS BOOK SYSTEM.");
        AddressBookManager addressBookManager = new AddressBookManager();
        AddressBook addressBook1 = new AddressBook();
        AddressBookFileIOService addressBookFileIOService = new AddressBookFileIOService();
        boolean flag = true;
        while (flag) {
            System.out.println("LIST");
            System.out.println("[1] :Add New Address Book");
            System.out.println("[2] :View all the contacts from AddressBook");
            System.out.println("[3] :Search Contact from a city");
            System.out.println("[4] :Search Contact from a State");
            System.out.println("[5] :Search City of which u want the count of contacts");
            System.out.println("[6] :Sort by Name");
            System.out.println("[7] :Sort by Zip");
            System.out.println("[8] :view in a .txtfile");
            System.out.println("[9] :read from a .txtfile");
            System.out.println("[10] :view in a .csvfile");
            System.out.println("[11] :read from a .csvfile");
            System.out.println("[12] :Exit");
            System.out.println("Enter choice: ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    addressBook1.createAddBook();
                    addressBook1.addContacts();
                    boolean flag1 = true;
                    while (flag1) {
                        System.out.println("[1]: add a contact");
                        System.out.println("[2]: edit a contact ");
                        System.out.println("[3]: delete a contact");
                        System.out.println("[4]: Exit");
                        System.out.println("Enter choice:");
                        int c = sc.nextInt();
                        switch (c) {
                            case 1:
                                addressBook1.addContacts();
                                break;
                            case 2:
                                addressBook1.edit();
                                break;
                            case 3:
                                System.out.println("enter name of person whose details you want to delete");
                                String fname = sc.next();
                                addressBook1.deleteContact(fname);
                                System.out.println();
                                break;
                            case 4:
                                flag1 = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    addressBook1.getContacts();
                    break;
                case 3:
                    System.out.println("Enter City of which u want data : ");
                    String CityName = sc.next();
                    addressBook1.getPersonNameByCity(CityName);
                    break;

                case 4:
                    System.out.println("enter state of which u want data");
                    String state = sc.next();
                    addressBook1.getPersonNameByState(state);
                    break;
                case 5:
                    System.out.println("enter city of which u want count");
                    String city = sc.next();
                    addressBook1.countByCity(city);
                    break;
                case 6:
                    addressBook1.sortByName();
                    break;
                case 7:
                    addressBook1.sortByZip();
                    break;
                case 8:
                    System.out.println("printing in text file");
                    addressBook1.writeContactPersonData(AddressBook.IOService.FILE_IO);
                    break;
                case 9:
                    AddressBookFileIOService.readData();
                    addressBookFileIOService.printData();
                    break;
                case 10:
                    try {
                        AddressBookCSVService.writeDataToCSV();
                    }catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                        e.printStackTrace();
                    }
                    break;
                case 11:
                    try {
                        AddressBookCSVService.readDataFromCSV();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 12:
                    flag = false;
                    break;
            }
        }
    }
}

