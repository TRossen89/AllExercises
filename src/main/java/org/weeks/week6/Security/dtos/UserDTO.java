package org.weeks.week6.Security.dtos;

import lombok.*;
import org.weeks.week6.Security.model.User;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {


    private String username;
    private String password;

    @ToString.Exclude
    private Set<String> roles;

    public UserDTO (String username, String password){
        this.username = username;
        this.password = password;

    }

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRolesAsString();
    }
    public UserDTO(String username, Set<String> roleSet){
        this.username = username;
        this.roles = roleSet;
    }



}
