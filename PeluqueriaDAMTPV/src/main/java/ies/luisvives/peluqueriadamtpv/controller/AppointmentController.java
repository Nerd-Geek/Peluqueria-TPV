package ies.luisvives.peluqueriadamtpv.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public AppointmentController() {
    }

    @FXML
    protected void initialize() {
        tableViewController.setSearchQuery("");
        tableViewController.setEntityForTable(TableViewController.APPOINTMENT);
        calendarViewController.setTableViewController(tableViewController);
    }

    public void setSearchQuery(String searchQuery) {
        tableViewController.setSearchQuery(searchQuery);
    }

}
