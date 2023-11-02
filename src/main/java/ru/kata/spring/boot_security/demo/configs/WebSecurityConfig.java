package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServiceImp userServiceImp;
    private final PasswordEncoder passwordEncoder;
    private final SuccessUserHandler userHandler;

    @Autowired
    public WebSecurityConfig(UserServiceImp userServiceImp, PasswordEncoder passwordEncoder, SuccessUserHandler userHandler) {
        this.userServiceImp = userServiceImp;
        this.passwordEncoder = passwordEncoder;
        this.userHandler = userHandler;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(userHandler)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login");


    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImp)
                .passwordEncoder(passwordEncoder);

    }

}