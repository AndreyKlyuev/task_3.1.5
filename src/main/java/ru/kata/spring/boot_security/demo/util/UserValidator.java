package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.Services.UserServiceImp;
import ru.kata.spring.boot_security.demo.entity.User;

@Component
public class UserValidator implements Validator {

    private final UserServiceImp userDetailsServiceImp;
    @Autowired
    public UserValidator(UserServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            userDetailsServiceImp.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored){
            return; // выглядит ужасно но рабтает, надеюсь не придется из за этого пределывать))
        }
        errors.rejectValue("email", "", "Пользователь с таким email уже существует");
    }
}
