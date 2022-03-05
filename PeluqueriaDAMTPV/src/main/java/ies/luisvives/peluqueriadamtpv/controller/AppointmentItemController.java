package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.Appointment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppointmentItemController implements BaseController {
    @FXML
    private Label labelUsername;

    @FXML
    private Label labelService;

    @FXML
    private Label labelHour;

    @FXML
    private Label labelDate;

    private Appointment appointment;

    public AppointmentItemController(Appointment appointment){
        this.appointment = appointment;
    }

    @FXML
    private void initialize(){
        this.labelUsername.setText(appointment.getUser().getUsername());
        this.labelService.setText(appointment.getService().getName());
        this.labelHour.setText(appointment.getTime());
        this.labelDate.setText(appointment.getDate());
    }
}
