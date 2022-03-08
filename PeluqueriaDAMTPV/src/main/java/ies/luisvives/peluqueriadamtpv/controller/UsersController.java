package ies.luisvives.peluqueriadamtpv.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements BaseController, Initializable, Callback {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField imageTextField;
    @FXML
    private ChoiceBox<String> genderChoiceBox;
    @FXML
    private HBox tableView;
    @FXML
    private TableViewController tableViewController;
    @FXML
    private BorderPane insertEntityView;
    @FXML
    private InsertEntityViewController insertEntityViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewController.setSearchQuery("");
        tableViewController.setEntityForTable(TableViewController.USER);
        insertEntityViewController.setEntityType(InsertEntityViewController.USER_TYPE);
    }

    public void setSearchQuery(String searchQuery) {
        this.tableViewController.setSearchQuery(searchQuery);
    }
}