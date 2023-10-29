package ru.kata.spring.boot_security.demo.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User findUser(Long id);
    List<User> allUsers();
    void saveUser(User user);
    void updateUser(User user, long id);

    void deleteUser(long id);

    List<Role> findAllRoles();



}
