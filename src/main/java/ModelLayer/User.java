package ModelLayer;

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

    public void setPassword(String Password){
        this.Password = Password ;
    }
    public void setRole(Role role){
        this.role = role;
    }
}
