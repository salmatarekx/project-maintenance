package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID ;
    private String UserName ;
    private String Email;
    private String Password ;
    @Enumerated(EnumType.STRING)

    private Role role ;

    public void setUserName(String userName){
        this.UserName = userName  ;

    }
    public void setEmail(String Email){
        this.Email = Email ;
    }
    public int getID() {
        return ID;
     }
    public void setPassword(String Password){
        this.Password = Password ;
    }
    public String getPassword(){
        return Password ;
    }
    public void setRole(Role role){
        this.role = role;
    }
    public Role getRole(){
        return role;
    }
}
//push