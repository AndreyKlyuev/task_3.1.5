package ru.kata.spring.boot_security.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно содержать от 2 до 100 символов")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Фамилия не должна быть пустой")
    @Size(min = 2, max = 100, message = "Фамилия должа содержать от 2 до 100 символов")
    @Column(name = "lastname")
    private String lastname;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Size(min = 4, message = "Не создавайте пароль меньше 4 символов")
    @Column(name = "password")
    private String password;

    @Min(value = 1,message = "Возраст не может быть меньше 1")
    @Max(value = 120,message = "Возраст не может быть больше 5")
    @Column(name = "age")
    private int age;

    @NotEmpty(message = "Укажите email")
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String username, String lastname, String password, int age, String email, String role) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
