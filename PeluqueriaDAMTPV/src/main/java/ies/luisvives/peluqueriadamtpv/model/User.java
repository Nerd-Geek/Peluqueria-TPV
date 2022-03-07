package ies.luisvives.peluqueriadamtpv.model;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
public class User implements TableEntity{
    private String id;
    private String image;
    private boolean superUser;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String token;
    private UserGender gender;
    private Set<UserRoles> userRoles;

    public User(String image, String username, String name, String surname, String password, String phoneNumber,
                String email, UserGender gender){
        this.image = image;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }
}