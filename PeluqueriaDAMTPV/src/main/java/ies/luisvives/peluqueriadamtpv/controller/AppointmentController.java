package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AppointmentController {
    @FXML
    private Button prevServiceButton;
    @FXML
    private Button nextServiceButton;
    @FXML
    private Button createAppointmentButton;
    @FXML
    private HBox tableView;
    @FXML
    private TableViewController tableViewController;
    @FXML
    private VBox calendarView;
    @FXML
    private CalendarViewController calendarViewController;

    //TODO: QUITAR ESTO Y CAMBIAR POR CONTROLADOR? - en progreso
    @FXML
    private ChoiceBox cbHour;
    @FXML
    private ChoiceBox cbMinute;

    public AppointmentController() {
    }

    @FXML
    protected void initialize() {
        tableViewController.setSearchQuery("");
        tableViewController.setEntityForTable(TableViewController.APPOINTMENT);
        calendarViewController.setTableViewController(tableViewController);
    }

    private void hourManagement() {
        ArrayList<Integer> minutes = new ArrayList<>();
        for (int n = 0; n < 23; n++){
            minutes.add(n + 1);
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



    @FXML
    public void createAppointment() {
//        Dialog dialog = new Dialog();
//        try {
//            DialogPane pane = new DialogPane();
//            pane.setContent(Util.getParentRoot("appointment-create-view"));
//            dialog.setDialogPane(pane);
////            pane.getChildren().forEach(c ->
////                    System.out.println(c.getId()) //TODO: DO
////            );
//
//            hourManagement();
//            minuteManagement();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
//        dialog.showAndWait();
    }

    public void setSearchQuery(String searchQuery) {
        tableViewController.setSearchQuery(searchQuery);
    }

}
