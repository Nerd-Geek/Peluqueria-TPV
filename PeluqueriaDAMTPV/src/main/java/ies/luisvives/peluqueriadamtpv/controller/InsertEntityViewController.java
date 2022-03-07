package ies.luisvives.peluqueriadamtpv.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ies.luisvives.peluqueriadamtpv.model.Appointment;
import ies.luisvives.peluqueriadamtpv.model.Service;
import ies.luisvives.peluqueriadamtpv.model.User;
import ies.luisvives.peluqueriadamtpv.model.UserGender;
import ies.luisvives.peluqueriadamtpv.restcontroller.APIRestConfig;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;
import retrofit2.Response;

import java.util.*;
import java.util.stream.Collectors;

public class InsertEntityViewController implements BaseController{
    @FXML
    private Label titleLabel;
    @FXML
    private VBox labelsBox;
    @FXML
    private VBox fieldsBox;
    @FXML
    private ImageView uploadImageView;
    @FXML
    private TextField imageURLTextField;
    private Map<String, TextField> entityFields;
    private Map<String, ChoiceBox<?>> entityChoiceBoxes;
    
    public static final int SERVICE_TYPE = 0;
    public static final int USER_TYPE = 1;

    private int currentType = SERVICE_TYPE;
    private Set<TextField> textfields = new LinkedHashSet<>();

    public InsertEntityViewController() {
        entityFields = new HashMap<>();
        entityChoiceBoxes = new HashMap<>();
    }
    
    @FXML
    public void initialize(){
        
    }
    
    public void setEntityType(int type) {
        if(type == USER_TYPE){
            currentType = USER_TYPE;
            loadUserComponents();
        }
        else if (type == SERVICE_TYPE) {
            currentType = SERVICE_TYPE;
            loadServiceComponents();
        }
    }

    private void loadServiceComponents() {
        titleLabel.setText(Util.getString("text.addService"));
        addEntityField(Util.getString("text.name"));
        addEntityField(Util.getString("text.description"));
        addEntityField(Util.getString("text.price"));
        addEntityField(Util.getString("text.stock"));
    }

    private void loadUserComponents() {
        titleLabel.setText(Util.getString("text.addUser"));
        addEntityField(Util.getString("text.username"));
        addEntityField(Util.getString("text.name"));
        addEntityField(Util.getString("text.surname"));
        addEntityField(Util.getString("text.password"));
        addEntityField(Util.getString("text.telephone"));
        addEntityField(Util.getString("text.email"));
        addGenderChoiceBox();
    }

    private void addGenderChoiceBox() {
        String name = "gender";
        labelsBox.getChildren().add(new Label(name));
        ChoiceBox<UserGender> field = new ChoiceBox<>();
        field.setItems(FXCollections.observableList(Arrays.stream(UserGender.values()).collect(Collectors.toList())));
        entityChoiceBoxes.put(name, field);
        fieldsBox.getChildren().add(field);
    }

    private void addEntityField(String name) {
        labelsBox.getChildren().add(new Label(name));
        TextField field = new TextField();
        textfields.add(field);
        entityFields.put(name, field);
        fieldsBox.getChildren().add(field);
    }

    @FXML
    protected void insertEntity() {
        if(currentType == USER_TYPE){
            insertUser();
        }
        else if (currentType == SERVICE_TYPE){
            insertService();
        }
    }

    private void insertService() {
        TextField[] information = getInformationArray();

        String user = information[0].getText();
        String image = ""; //TODO: DO
        String description = information[1].getText();
        Double price = Double.parseDouble(information[2].getText()); //TODO: CHEQUEAR
        Integer stock = Integer.parseInt(information[3].getText()); //TODO: CHEQUEAR
        Service service = new Service(image, user, description, price, stock);

        //TODO: Insertar en BD y avisar de mensajes
        System.out.println(service);
    }

    @SneakyThrows
    private void insertUser() {
        TextField[] information = getInformationArray();

        String image = ""; //TODO: DO

        //TODO: DO CHEQUEAR TODO
        String username = information[0].getText();
        String name = information[1].getText();
        String surname = information[2].getText();
        String password = information[3].getText();
        String telephone = information[4].getText();
        String email = information[5].getText();
        UserGender gender = UserGender.Male; //TODO: DO
        User user = new User(image, username, name, surname,password, telephone, email, gender);
        //TODO: Insertar en BD y avisar de mensajes
        System.out.println(user);

        APIRestConfig.getUsersService().insertUsers(APIRestConfig.token, user).execute();
        System.out.println("DONE"); //TODO: ALERT MSG
        //TODO: CHECK PREVIUS CONDIITONS
    }

    private TextField[] getInformationArray() {
        TextField[] information = new TextField[textfields.size()];
        textfields.toArray(information);
        return information;
    }
}
