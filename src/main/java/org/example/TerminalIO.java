package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

public class TerminalIO {
    private final ContactsManager contactsManager;
    private final FileIO fileIO;

    public TerminalIO(ContactsManager contactsManager, FileIO fileIO){
        this.contactsManager = contactsManager;
        this.fileIO = fileIO;
    }

    public void startSession(){
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Spring contacts. Type \"Help\" for command list.");
            System.out.println("Введите команду: ");
            String command = sc.next();
            if(command.equals("Help")){
                System.out.println("-------------------------------------------------");
                System.out.println("PrintAll - prints all contacts");
                System.out.println("Print - prints contact by email");
                System.out.println("Add - adds new contact");
                System.out.println("Remove - removes contact by email");
                System.out.println("Save - saves all contacts to file");
                System.out.println("Exit - closes program");
                System.out.println("-------------------------------------------------");
            }else if(command.equals("PrintAll")){
                contactsManager.printContacts();
                continue;
            }else if(command.equals("Print")){
                System.out.println("Type email: ");
                String email = sc.next();
                int ret = contactsManager.printContactByEmail(email);
                if(ret == 1){
                    System.out.println("Not found!");
                    continue;
                }
            }else if(command.equals("Add")){
                System.out.println("Type \"fullName;phoneNumber;email\": ");
                String arg = sc.next();
                Contact contact = parseContact(arg);
                contactsManager.addContact(contact);
                System.out.println("Contact added!");
            }else if(command.equals("Remove")){
                System.out.println("Type email: ");
                String email = sc.next();
                int ret = contactsManager.removeByEmail(email);
                if(ret == 1){
                    System.out.println("Not found!");
                    continue;
                }
                System.out.println("Contact removed!");
            }else if(command.equals("Save")){
                fileIO.save();
            }

            if(command.equals("Exit")){
                break;
            }
        }
    }

    public static Contact parseContact(String string){
        String[] fields = string.split(";");
        String fullName = fields[0].trim();
        String phoneNumber = fields[1].trim();
        String email = fields[2].trim();
        Contact contact = new Contact(fullName, phoneNumber, email);
        return contact;
    }
}
