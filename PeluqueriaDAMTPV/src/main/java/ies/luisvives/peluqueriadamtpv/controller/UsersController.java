package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.User;
import ies.luisvives.peluqueriadamtpv.model.UserGender;
import ies.luisvives.peluqueriadamtpv.restcontroller.APIRestConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import retrofit2.Response;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

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
    private ChoiceBox<String> gender_choice_box;
    @FXML
    private HBox tableView;
    @FXML
    private TableViewController tableViewController;
    @FXML
    private VBox insertEntityView;
    @FXML
    private InsertEntityViewController insertEntityViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewController.setSearchQuery("");
        tableViewController.setEntityForTable(TableViewController.USER);
        insertEntityViewController.setEntityType(InsertEntityViewController.USER_TYPE);
    }

//    @FXML
//    public void deleteUser(ActionEvent event) {
//        try {
//            User toBeDelete = listUsers.getSelectionModel().getSelectedItem();
//            if (toBeDelete != null) {
//                User user = APIRestConfig.getUsersService().deleteUser(toBeDelete.getId()).execute().body();
//                Response<List<User>> usersList = Objects.requireNonNull(APIRestConfig.getUsersService().usersGetAll().execute());
//                ObservableList<User> users =
//                        FXCollections.observableArrayList();
//                if (usersList.body() != null) {
//                    users.addAll(usersList.body());
//                } else {
//                    users.remove(user);
//                }
//                listUsers.setItems(users);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    private void insertUser() throws IOException {

        ObservableList<User> users =
                FXCollections.observableArrayList();
        User user = new User();
        user.setId(UUID.randomUUID().toString()); //TODO: why UUID set here? - vulnerability
        user.setUsername(usernameTextField.getText());
        user.setName(nameTextField.getText());
        user.setSurname(surnameTextField.getText());
        user.setPassword(passwordTextField.getText());
        user.setPhoneNumber(telephoneTextField.getText());
        user.setEmail(emailTextField.getText());
        user.setGender(UserGender.valueOf(gender_choice_box.getValue()));
        user.setImage(imageTextField.getText());

        APIRestConfig.getUsersService().insertUsers(user).execute();
        Response<List<User>> usersList = Objects.requireNonNull(APIRestConfig.getUsersService().usersGetAll().execute());
        if (usersList.body() != null) {
            users.addAll(usersList.body());
//            listUsers.setItems(users);
        }
    }

    public void setSearchQuery(String searchQuery) {
        this.tableViewController.setSearchQuery(searchQuery);
    }
}