package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.Appointment;
import ies.luisvives.peluqueriadamtpv.model.createDTOs.CreateAppointmentDTO;
import ies.luisvives.peluqueriadamtpv.model.Service;
import ies.luisvives.peluqueriadamtpv.model.User;
import ies.luisvives.peluqueriadamtpv.restcontroller.APIRestConfig;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private TextField usernameField;

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
        hourViewController.setExternalData(tableViewController, calendarViewController.getActualDateStringProperty());
        initServices();
        usernameField.setOnAction(e -> {
            try {
                createAppointment();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void initServices() {
        Response<List<Service>> response = null;
        try {
            response = APIRestConfig.getServicesService().serviceGetAll(APIRestConfig.token).execute();
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
    public void createAppointment() throws IOException {
            Optional<Appointment> appointmentOpt = appointmentCreate();
            if (appointmentOpt.isPresent()){
                Appointment appointment = appointmentOpt.get();
                CreateAppointmentDTO createAppointment = new CreateAppointmentDTO();
                createAppointment.setDate(appointment.getDate());
                createAppointment.setTime(appointment.getTime());
                createAppointment.setServiceId(appointment.getService().getId());
                createAppointment.setUserId(appointment.getUser().getId());

                //Confirm
                User user = appointment.getUser();
                String content = Util.getString("text.makeAppointmentQuestion") + "\n\n" +
                        "• " + Util.getString("text.date") + ": " + appointment.getDate() + "\n" +
                        "• " + Util.getString("text.hour") + ": " + appointment.getTime() + "\n" +
                        "• " + Util.getString("text.service") + ": " + appointment.getService().getName() + "\n" +
                        "• " + Util.getString("text.username") + ": " + user.getUsername()+" ("+user.getName()+" "+user.getSurname() + ")\n\n";

                if (Util.confirmDeleteAlert(Util.getString("text.create"), content)){
                    Appointment inserted = APIRestConfig.getAppointmentsService().insertAppointments(APIRestConfig.token, createAppointment).execute().body();
                    if (inserted != null && APIRestConfig.getAppointmentsService().appointmentGetById(APIRestConfig.token, inserted.getId()) != null){
                        Util.popUpAlert(Util.getString("title.info"), Util.getString("text.appointmentCreated"),Alert.AlertType.INFORMATION);
                    }else{
                        String moreInfo = getErrorAppointment(appointment);
                        Util.popUpAlert(Util.getString("title.error"), Util.getString("error.appointmentNotCreated") + "\n" + moreInfo, Alert.AlertType.ERROR);
                    }
                }
            }
    }

    private String getErrorAppointment(Appointment appointment) {
        LocalDate localDate = LocalDate.parse(appointment.getDate());
        String[] time = appointment.getTime().split(":");
        LocalTime dateTime = LocalTime.of(Integer.parseInt(time[0]),Integer.parseInt(time[1]));
        if (localDate.isAfter(LocalDate.now()) || (dateTime.isAfter(LocalTime.now()) && localDate.isEqual(LocalDate.now()))){
            return Util.getString("error.oldDateTime");
        }else if (appointment.getService().getStock() <= 0){
            return Util.getString("error.noStockService");
        }else{
            //TODO: else que comprueba: if (appointment.getUser().getAppointments().stream().anyMatch(a -> a.getDate().equals(appointment.getDate()) && a.getTime().equals(appointment.getTime()))) {
            return "";
        }
    }

    /**
     * Return an appointment if can be created
     * pop up an alert if appointment can't be created
     * @return Optional Appointment present if can be created
     */
    private Optional<Appointment> appointmentCreate() throws IOException {
        Optional<Appointment> appointment = Optional.empty();
        Optional<String> date = getDate();
        Optional<String> time = Optional.empty();
        Optional<User> user = Optional.empty();
        Optional<Service> service = getService();
        if (date.isPresent()){
            time = getTime();
            if (time.isPresent()){
                user = getUser();
            }
        }
        if (date.isPresent() && time.isPresent() && user.isPresent() && service.isPresent()){
            Appointment ap = new Appointment();
            ap.setId(UUID.randomUUID().toString()); //TODO: why UUID set here? - vulnerability
            ap.setDate(date.get());
            ap.setTime(time.get());
            ap.setUser(user.get());
            ap.setService(service.get());
            appointment = Optional.of(ap);
        }

        return appointment;
    }

    private Optional<String> getTime() {
        Optional<String> str = hourViewController.getActualTimeString();
        if (str.isEmpty()){
            Util.popUpAlert(Util.getString("title.info"), Util.getString("error.noTimeSet"),Alert.AlertType.INFORMATION);
        }
        return str;
    }

    private Optional<String> getDate() {
        Optional<String> str = calendarViewController.getActualDateString();
        if (str.isEmpty()){
            Util.popUpAlert(Util.getString("title.info"), Util.getString("error.noDateSet"), Alert.AlertType.INFORMATION);
        }
        return str;
    }

    /**
     * Return a Service if set and exist
     * Pop up an alert if it doesn't exist
     * @return Optional Present if service exists
     */
    private Optional<Service> getService() {
        Optional<Service> service = Optional.empty();
        if (services.isEmpty()){
            Util.popUpAlert(Util.getString("title.info"), Util.getString("text.noServices"), Alert.AlertType.INFORMATION);
        }else{
            service = Optional.of(services.get(actualServiceSelected));
        }
        return service;
    }

    /**
     * Return a User if set and exist
     * Pop up an alert if it doesn't exist
     * @return Optional Present if user exists
     */
    private Optional<User> getUser() throws IOException {
        Optional<User> userOpt = Optional.empty();
        Optional<String> errorMsg = Optional.empty();

        if (usernameField.getText().isEmpty()){
            errorMsg = Optional.of(Util.getString("error.userNotSet"));
        }else{
            User user = APIRestConfig.getUsersService().findByUsername(APIRestConfig.token, usernameField.getText()).execute().body();
            if (user == null){
                errorMsg = Optional.of(Util.getString("error.userNotFound") + "\n" + getUserSuggestionsMsg());
            }else{
                userOpt = Optional.of(user);
            }
        }

        //Show alert if error msg and return result
        errorMsg.ifPresent(e -> Util.popUpAlert(Util.getString("title.info"), e, Alert.AlertType.INFORMATION));
        return userOpt;
    }

    /**
     * Return user suggestions (empty String if any suggestion)
     * @return User suggestions
     * @throws IOException Input/Output exception
     */
    private String getUserSuggestionsMsg() throws IOException {
        int limit = 10;
        StringBuilder usersSuggestionMsg = new StringBuilder();
        List<User> usersSuggestions = APIRestConfig.getUsersService()
                .userGetAllWithUser_name(APIRestConfig.token, usernameField.getText()).execute().body();
        if (usersSuggestions != null && !usersSuggestions.isEmpty()){
            usersSuggestionMsg.append("\n").append(Util.getString("text.userSuggestions")).append("\n");

            int size = usersSuggestions.size();
            if (size > limit){
                size = limit;
            }

            for (int n = 0; n < size; n++) {
                User user = usersSuggestions.get(n);
                usersSuggestionMsg.append(user.getUsername()).append(" (")
                        .append(user.getName()).append(" ").append(user.getSurname()).append(")").append("\n");
            }
        }
        return usersSuggestionMsg.toString();
    }

    /**
     * Set te search query
     * @param searchQuery search query
     */
    public void setSearchQuery(String searchQuery) {
        tableViewController.setSearchQuery(searchQuery);
    }
}