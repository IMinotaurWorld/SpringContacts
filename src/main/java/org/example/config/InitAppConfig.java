package org.example.config;

import org.example.*;
import org.springframework.context.annotation.*;

@ComponentScan("org.example")
@Configuration
@PropertySource("classpath:application.properties")
@Profile("init")
public class InitAppConfig {

    @Bean
    public FileIO fileIO(ContactsManager contactsManager){
        return new InitFileIO(contactsManager);
    }

}
