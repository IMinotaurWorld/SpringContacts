package org.example;

import org.springframework.context.annotation.Primary;

@Primary
public class DefaultFileIO implements FileIO{
    private final ContactsManager contactsManager;

    public DefaultFileIO(ContactsManager contactsManager){
        this.contactsManager = contactsManager;
    }

    @Override
    public void init() {
        System.out.println("Init disabled.");
    }

    @Override
    public void save() {

    }
}
