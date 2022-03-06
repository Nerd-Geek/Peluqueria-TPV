package ies.luisvives.peluqueriadamtpv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LoginUser implements TableEntity {
    @JsonIgnore
    private String id;
    private String username;
    private String password;

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
