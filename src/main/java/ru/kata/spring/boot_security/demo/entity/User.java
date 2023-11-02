package ru.kata.spring.boot_security.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotEmpty(message = "Имя не должно быть пустым")
//    @Size(min = 2, max = 100, message = "Имя должно содержать от 2 до 100 символов")
    private String firstname;

//    @NotEmpty(message = "Фамилия не должна быть пустой")
//    @Size(min = 2, max = 100, message = "Фамилия должа содержать от 2 до 100 символов")
    private String lastname;

//    @NotEmpty(message = "Пароль не должен быть пустым")
//    @Size(min = 2, message = "Не создавайте пароль меньше 2 символов")
    private String password;

//    @Min(value = 1,message = "Возраст не может быть меньше 1")
//    @Max(value = 120,message = "Возраст не может быть больше 5")
    private byte age;

    @Column(unique = true)
//    @NotEmpty(message = "Укажите email")
//    @Email(message = "Email введен не коректно")
    private String email;

//    @NotEmpty(message = "Выберете роль")
    @ManyToMany
    @JoinTable(name = "users_roles")
    private Set<Role> role;

    public User() {
    }

    public User(String firstname, String lastname, String password, byte age, String email, Set<Role> role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
