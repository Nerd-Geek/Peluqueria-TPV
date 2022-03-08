package ies.luisvives.peluqueriadamtpv.model.createDTOs;

import ies.luisvives.peluqueriadamtpv.model.UserGender;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateUser {
    private String id = UUID.randomUUID().toString();
    private String image;
    private String username;
    private String name;
    private String surname;
    private String password;
    private String passwordConfirm;
    private String phoneNumber;
    private String email;
    private UserGender gender;

    public CreateUser(String image, String username, String name, String surname, String password, String phoneNumber,
                      String email, UserGender gender) {
        this.image = image;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.passwordConfirm = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }
}
