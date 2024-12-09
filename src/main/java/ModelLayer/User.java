package ModelLayer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    public int ID ;
    public String UserName ;

    public int Password ;
    public String Role ;

}
