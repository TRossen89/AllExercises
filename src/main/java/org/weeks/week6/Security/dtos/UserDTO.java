package org.weeks.week6.Security.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.weeks.week6.Security.model.User;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {


    private String username;
    private String password;

    private Set<String> roles;

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
