package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.Appointment;
import ies.luisvives.peluqueriadamtpv.model.Service;
import ies.luisvives.peluqueriadamtpv.model.User;
import ies.luisvives.peluqueriadamtpv.restcontroller.APIRestConfig;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import retrofit2.Response;

import java.net.URL;
import java.util.*;

public class ReportController implements BaseController, Initializable{
    @FXML private PieChart genderChart;
    @FXML private BarChart serviceHigherChart;
    private ObservableList<Service> listService = FXCollections.observableArrayList();
    private ObservableList<Appointment> listAppointments = FXCollections.observableArrayList();
    private ObservableList<User> listUsers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        initCharts();
    }

    private void initCharts() {
        configGenderChart();
        configServiceHigherChart();
        //TODO: MORE CHARTS
    }

    private void loadData() {
        loadServices();
        loadAppointments();
        loadUsers();
    }

    private void loadServices() {
        try {
            Response<List<Service>> listServiceResponse = Objects.requireNonNull(APIRestConfig.getServicesService()
                    .serviceGetAll().execute());
            if (listServiceResponse.body() != null) {
                listService.addAll(listServiceResponse.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAppointments() {
        try {
            Response<List<Appointment>> listAppointmentService = Objects.requireNonNull(APIRestConfig.getAppointmentsService()
                    .appointmentsGetAll().execute());
            if (listAppointmentService.body() != null) {
                listAppointments.addAll(listAppointmentService.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        try {
            Response<List<User>> listUserResponse = Objects.requireNonNull(APIRestConfig.getUsersService()
                    .usersGetAll().execute());
            if (listUserResponse.body() != null) {
                listUsers.addAll(listUserResponse.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void configGenderChart() {
        int maleCounter = 0;
        int femaleCounter = 0;
        for (User user : listUsers) {
            if (user.getGender().toString().equals("Male")) {
                maleCounter++;
            }else if (user.getGender().toString().equals("Female")){
                femaleCounter++;
            }
        }

        XYChart.Series male = new XYChart.Series<>();
        XYChart.Series female = new XYChart.Series<>();
        serviceHigherChart.setTitle(Util.getString("text.genders"));
        male.setName(Util.getString("text.male"));
        male.getData().add(new XYChart.Data("", maleCounter));
        female.setName(Util.getString("text.female"));
        female.getData().add(new XYChart.Data("", femaleCounter));
        serviceHigherChart.getData().addAll(male, female);
        serviceHigherChart.verticalGridLinesVisibleProperty();
    }

    public void configServiceHigherChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<String, Integer> map = countAppointmentsService();
        map.forEach((s, q) -> pieChartData.add(new PieChart.Data(s,q)));
        genderChart.setData(pieChartData);
        genderChart.setClockwise(false);
        genderChart.setTitle(Util.getString("text.serviceDistribution"));
    }

    /**
     * Return a map | Key = Services | Value = Number of times it appears in services
     * @return Map of 'service name' as key and 'number of times it appears in services' as Integer
     */
    private Map<String, Integer> countAppointmentsService() {
        Map<String, Integer> map = new HashMap<>();
        List<String> servicesNames = new ArrayList<>();
        listAppointments.forEach(u -> servicesNames.add(u.getService().getName()));

        for (Service service : listService){
            int count = Collections.frequency(servicesNames, service.getName());
            map.put(service.getName(), count);
        }
        return map;
    }

    //TODO: DO HACER LOS REPORTS QUE QUEDAN
}
