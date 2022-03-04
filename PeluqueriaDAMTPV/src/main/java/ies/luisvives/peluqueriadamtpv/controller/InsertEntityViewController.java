package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.User;
import ies.luisvives.peluqueriadamtpv.model.UserGender;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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

    public InsertEntityViewController() {
        entityFields = new HashMap<>();
        entityChoiceBoxes = new HashMap<>();
    }
    
    @FXML
    public void initialize(){
        
    }
    
    public void setEntityType(int type) {
        if(type == USER_TYPE)
            loadUserComponents();
        else if (type == SERVICE_TYPE)
            loadServiceComponents();
    }

    private void loadServiceComponents() {
        titleLabel.setText("Service Insertion");
        addEntityField("name");
        addEntityField("description");
        addEntityField("price");
        addEntityField("stock");
    }

    private void loadUserComponents() {
        titleLabel.setText("User Insertion");
        addEntityField("username");
        addEntityField("name");
        addEntityField("surname");
        addEntityField("password");
        addEntityField("telephone");
        addEntityField("e-mail");
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
        entityFields.put(name, field);
        fieldsBox.getChildren().add(field);
    }
}
