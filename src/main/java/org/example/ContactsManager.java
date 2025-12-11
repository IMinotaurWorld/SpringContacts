package org.example;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsManager {
    private final HashMap<String, Contact> contacts;

    public ContactsManager(){
        contacts = new HashMap<>();
    }

    public void addContact(Contact contact){
        contacts.put(contact.getEmail(), contact);
    }

    public Contact getContactByEmail(String email){
        return contacts.get(email);
    }

    public int removeByEmail(String email){
        Object ret = contacts.remove(email);
        if(ret == null){
            return 1;
        }
        return 0;
    }

    public List<Contact> getAllContacts(){
        ArrayList<Contact> ret = new ArrayList<>();
        for(Map.Entry<String, Contact> entry : contacts.entrySet()){
            ret.add(entry.getValue());
        }
        return ret;
    }

    public int printContactByEmail(String email){
        Contact contact = getContactByEmail(email);
        if(contact == null) return 1;
        String fullName = contact.getFullName();
        String number = contact.getPhoneNumber();
        System.out.println(MessageFormat.format("{0}; {1}; {2}", fullName, number, email));
        return 0;
    }

    public void printContacts(){
        for(Map.Entry<String, Contact> entry : contacts.entrySet()){
            System.out.println(MessageFormat.format("{0}; {1}; {2}", entry.getValue().getFullName(), entry.getValue().getPhoneNumber(), entry.getValue().getEmail()));
        }
    }
}
