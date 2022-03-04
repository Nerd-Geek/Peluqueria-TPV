package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserItemController implements BaseController {
    @FXML private Label textUsername;
    @FXML private Label textName;
    @FXML private Label textSurname;
    @FXML private Label textPhone;
    @FXML private Label textEmail;
    @FXML private Label textGender;
    private User user;

    public UserItemController(User user) {
        this.user = user;
    }

    @FXML
    public void initialize(){
        //TODO: Arreglar - Da NullPointerException
        this.textUsername.setText(user.getUsername());
        this.textName.setText(user.getName());
        this.textSurname.setText(user.getSurname());
        this.textPhone.setText(user.getPhoneNumber());
        this.textEmail.setText(user.getEmail());
        this.textGender.setText(user.getGender().toString());
    }
}
