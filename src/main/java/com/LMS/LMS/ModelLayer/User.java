package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String UserName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    @Column(name = "Email")
    private String Email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String Password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

    // Constructors
    public User(Long ID, String userName, String email, String password, Role role) {
        this.ID = ID;
        UserName = userName;
        Email = email;
        Password = password;
        this.role = role;
    }

    public User() {
    }

    // Getters and Setters
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
