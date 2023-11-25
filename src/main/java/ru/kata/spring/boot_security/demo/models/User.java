package ru.kata.spring.boot_security.demo.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 1, max = 100, message = "Имя от 1 до 100 символов длиной")
    @Column(name = "username")
    private String username;

    @Column(name = "lastname")
    @NotEmpty(message = "Поле не должно быть пустым")
    private String lastName;

    @Column(name = "age")
    @Min(value = 0, message = "Возраст должен быть больше 0")
    private int age;

    @Column(name = "email")
    @Email
    @NotEmpty
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

     public User(){}

    public User(int id, String username, String lastName, int age, String email, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String roleToString() {
        StringBuilder sb = new StringBuilder();

        this.roles.forEach(x -> sb.append(x.getName()).append(" "));

        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

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
        return roles;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
