package org.example.config;

import org.example.*;
import org.springframework.context.annotation.*;

@ComponentScan("org.example")
@Configuration
@PropertySource("classpath:application.properties")
public class DefaultAppConfig {

    @Bean
    public ContactsManager contactsManager(){
        return new ContactsManager();
    }

    @Bean
    public TerminalIO terminalIO(ContactsManager contactsManager, FileIO fileIO){
        return new TerminalIO(contactsManager, fileIO);
    }

    @Bean
    @Profile("default")
    public FileIO fileIO(ContactsManager contactsManager){
        return new DefaultFileIO(contactsManager);
    }

    @Bean
    public Starter starter(TerminalIO terminalIO, FileIO fileIO){
        return new Starter(terminalIO, fileIO);
    }

}
