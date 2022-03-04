package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.Service;
import ies.luisvives.peluqueriadamtpv.model.TableEntity;
import ies.luisvives.peluqueriadamtpv.restcontroller.APIRestConfig;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AppointmentController implements BaseController{
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
    @FXML
    private HourViewController hourViewController;

    @FXML
    private Label labelService;

    private ObservableList<Service> services;

    private int actualServiceSelected = 0;

    public AppointmentController() {
    }

    @FXML
    protected void initialize() {
        tableViewController.setSearchQuery("");
        tableViewController.setEntityForTable(TableViewController.APPOINTMENT);
        calendarViewController.setTableViewController(tableViewController);
        hourViewController.setTableViewController(tableViewController);
        initServices();
    }

    private void initServices() {
        Response<List<Service>> response = null;
        try {
            response = APIRestConfig.getServicesService().serviceGetAll().execute();
            if (response.body() != null) {
                services = FXCollections.observableArrayList(response.body());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateService();
    }

    /**
     * Update the service data
     * (This method establishes first service by default if exists)
     */
    private void updateService() {
        if (!services.isEmpty()){
            labelService.setText(String.valueOf(services.get(actualServiceSelected).getName()));
        }else{
            labelService.setText(Util.getString("text.noServices"));
        }
    }

    @FXML
    protected void prevServiceAction(){
        if (!services.isEmpty() && actualServiceSelected > 0){
            actualServiceSelected -=1;
        }
        updateService();
    }
    @FXML
    protected void nextServiceAction(){
        if (!services.isEmpty() && actualServiceSelected < services.size() - 1){
            actualServiceSelected +=1;
        }
        updateService();
    }

    @FXML
    public void createAppointment() {
        Dialog dialog = new Dialog();
        DialogPane pane = new DialogPane();
        Optional<Node> opt = Util.fxmlLoaderSetController("appointment-create-view", new CreateAppointmentController());
        if (opt.isPresent()){
            pane.setContent(opt.get());
            dialog.setDialogPane(pane);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
        }else{
            System.err.println(Util.getString("error.loading"));
        }
    }

    public void setSearchQuery(String searchQuery) {
        tableViewController.setSearchQuery(searchQuery);
    }

}
