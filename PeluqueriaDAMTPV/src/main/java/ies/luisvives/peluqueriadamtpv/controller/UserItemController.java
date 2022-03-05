package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserItemController implements BaseController {
    @FXML private Label usernameUser;
    @FXML private Label nameUser;
    @FXML private Label surnameUser;
    @FXML private Label phoneUser;
    @FXML private Label emailUser;
    @FXML private Label genderUser;
    private User user;

    public UserItemController(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        // TODO: Arreglar - Da NullPointerException
        usernameUser.setText(user.getUsername());
        nameUser.setText(user.getName());
        surnameUser.setText(user.getSurname());
        phoneUser.setText(user.getPhoneNumber());
        emailUser.setText(user.getEmail());
        genderUser.setText(user.getGender().toString());
    }
}
