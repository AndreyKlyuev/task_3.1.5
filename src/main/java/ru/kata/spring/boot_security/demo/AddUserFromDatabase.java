package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Configuration
public class AddUserFromDatabase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    @Autowired
    public AddUserFromDatabase(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }
    @PostConstruct
    public void addUserFromDb()  {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleRepository.save(admin);
        roleRepository.save(user);

        userRepository.save(new User("admin", "admin", encoder.encode("admin"),(byte) 30 ,"admin@mail.ru",
                new HashSet<>() {{
                    add(admin);
                    add(user);
                }}));
        userRepository.save(new User("user", "user", encoder.encode("user"),(byte)30 ,"user@mail.ru",
                new HashSet<>() {{
                    add(user);
                }}));
    }
}
