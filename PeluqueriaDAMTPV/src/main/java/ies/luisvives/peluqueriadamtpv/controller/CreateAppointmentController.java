package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateAppointmentController implements BaseController{

    @FXML
    private ChoiceBox cbHour;

    @FXML
    private ChoiceBox cbMinute;

    @FXML
    private TextField fieldUsername;

    private Appointment appointment;

    public CreateAppointmentController(Appointment appointment) {
        this.appointment = appointment; //TODO: DO DO REMOVE ALL CONTROLLER AND VIEW?
    }

    @FXML
    public void initialize() {
        hourManagement();
        minuteManagement();
    }

    private void hourManagement() {
        ArrayList<Integer> minutes = new ArrayList<>();
        for (int n = 8; n < 23; n++){
            minutes.add(n);
        }
        ObservableList obListHours = FXCollections.observableArrayList(minutes);
        cbHour.setItems(obListHours);
        cbHour.setValue(15); //Valor por defecto

        cbHour.setOnAction(event -> {
            System.out.println("TEST: " + cbHour.getValue()); //TODO: DO
        });
    }

    private void minuteManagement() {
        ArrayList<String> minutes = new ArrayList<>(Arrays.asList("00", "15", "30", "45"));
        ObservableList obListHours = FXCollections.observableArrayList(minutes);
        cbMinute.setItems(obListHours);
        cbMinute.setValue("00"); //Valor por defecto

        cbMinute.setOnAction(event -> {
            System.out.println("TEST: " + cbMinute.getValue()); //TODO: DO
        });
    }
}
