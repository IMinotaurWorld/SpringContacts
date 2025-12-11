package org.example;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InitFileIO implements FileIO{
    private final ContactsManager contactsManager;

    @Value("${app.savePath}")
    private String savePath;

    @Value("${app.initPath}")
    private String initPath;

    public InitFileIO(ContactsManager contactsManager){
        this.contactsManager = contactsManager;
    }

    @Override
    public void init() {
        FileReader file;
        try {
            file = new FileReader(initPath + "/contacts.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found.");
            return;
        }
        System.out.println("Initializing.");
        BufferedReader bufferedReader = new BufferedReader(file);
        String string;
        while(true){
            try {
                if (!((string=bufferedReader.readLine())!=null)) break;
                Contact contact = TerminalIO.parseContact(string);
                contactsManager.addContact(contact);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Contacts initialized");
    }

    @Override
    public void save() {
        FileWriter file;
        try {
            file = new FileWriter(initPath + "/contacts.txt");
        } catch (IOException e) {
            System.out.println("Error! File not found.");
            return;
        }
        System.out.println("Saving.");

        BufferedWriter bufferedWriter = new BufferedWriter(file);
        try {
            ArrayList<Contact> contacts = (ArrayList<Contact>) contactsManager.getAllContacts();
            for(Contact contact : contacts) {
                String fullName = contact.getFullName();
                String phoneNumber = contact.getPhoneNumber();
                String email = contact.getEmail();
                bufferedWriter.write(MessageFormat.format("{0};{1};{2}", fullName, phoneNumber, email));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Saved successfully");
    }
}