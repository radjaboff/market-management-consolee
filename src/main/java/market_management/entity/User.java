package market_management.entity;

import lombok.*;
import market_management.enums.UserRole;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = {"id","password"})
public class User  implements Serializable {
    private  String  id;
    private String fullName;
    private String email;
    private String password;
    private UserRole role;
}
