package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;

@Configuration
public class AddUserFromDatabase {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Autowired
    public AddUserFromDatabase(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }
    @PostConstruct
    public void addUserFromDb()  {
        User user = new User("user","user",encoder.encode("user"),30,"user@mail.ru","ROLE_USER");
        User admin = new User("admin","admin",encoder.encode("admin"),30,"admin@mail.ru","ROLE_ADMIN");

        userRepository.save(user);
        userRepository.save(admin);
    }
}
