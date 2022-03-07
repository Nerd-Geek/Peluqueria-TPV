package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.Appointment;
import ies.luisvives.peluqueriadamtpv.model.Service;
import ies.luisvives.peluqueriadamtpv.model.User;
import ies.luisvives.peluqueriadamtpv.restcontroller.APIRestConfig;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import retrofit2.Response;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReportController implements BaseController, Initializable {
    @FXML
    private PieChart genderChart;
    @FXML
    private BarChart serviceHigherChart;
    @FXML
    private LineChart incomeAmount;
    @FXML
    private BarChart numberAppointments;

    private ObservableList<Service> listService = FXCollections.observableArrayList();
    private ObservableList<Appointment> listAppointments = FXCollections.observableArrayList();
    private ObservableList<User> listUsers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        initCharts();
    }

    private void initCharts() {
        new Thread(() -> Platform.runLater(() -> {
            configGenderChart();
            configServiceChart();
            configIncomeAmountChart();
            configNumberAppointmentsChart();
        })).start();
    }

    private void configNumberAppointmentsChart() {
        numberAppointments.setTitle(Util.getString("title.appointments"));
        //Stock
        XYChart.Series<String, Number> seriesStock = new XYChart.Series<>();
        seriesStock.setName(Util.getString("text.stock"));

        //Appointment amount
        XYChart.Series<String, Number> seriesAppointmentAmount = new XYChart.Series<>();
        seriesAppointmentAmount.setName(Util.getString("text.appointmentAmount"));

        for (Service s : listService) {
            List<String> appointmentsServices = listAppointments.stream().map(e -> e.getService().getName()).collect(Collectors.toList());
            int amountOfAppointments = Collections.frequency(appointmentsServices, s.getName());
            seriesStock.getData().add(new XYChart.Data<>(s.getName(), s.getStock()));
            seriesAppointmentAmount.getData().add(new XYChart.Data<>(s.getName(), amountOfAppointments));
        }

        numberAppointments.getData().add(seriesStock);
        numberAppointments.getData().add(seriesAppointmentAmount);
    }

    private void configIncomeAmountChart() {
        incomeAmount.setTitle(Util.getString("title.incomeAmount"));
        listAppointments.forEach(e -> {
            for (int m = 1; m <= 12; m++) {
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(m + "");
                countIncomeAmountInMonth(m).forEach((day, amount) -> {
                    String dayStr = String.format("%02d", day);
                    series.getData().add(new XYChart.Data<>(dayStr, amount));
                });
                incomeAmount.getData().add(series);
            }
        });
    }

    private int getMaxDayOfMonth(int actualMonth, int actualYear) {
        int maxDays = 31; //1, 3, 5, 7, 8, 10, 12
        if (actualMonth == 4 || actualMonth == 6 || actualMonth == 9 || actualMonth == 11) {
            maxDays = 30;
        }
        if (actualMonth == 2) {
            //Check if is leap year
            if ((actualYear % 4 == 0) && ((actualYear % 100 != 0) || (actualYear % 400 == 0))) {
                maxDays = 29;
            } else {
                maxDays = 28;
            }
        }
        return maxDays;
    }

    private void loadData() {
        loadServices();
        loadAppointments();
        loadUsers();
    }

    private void loadServices() {
        try {
            Response<List<Service>> listServiceResponse = Objects.requireNonNull(APIRestConfig.getServicesService()
                    .serviceGetAll(APIRestConfig.token).execute());
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
                    .appointmentsGetAll(APIRestConfig.token).execute());
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
                    .usersGetAll(APIRestConfig.token).execute());
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
            } else if (user.getGender().toString().equals("Female")) {
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

    public void configServiceChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<String, Integer> map = countAppointmentsService();
        map.forEach((s, q) -> pieChartData.add(new PieChart.Data(s, q)));
        genderChart.setData(pieChartData);
        genderChart.setClockwise(false);
        genderChart.setTitle(Util.getString("text.serviceDistribution"));
    }

    /**
     * Return a map | Key = Services | Value = Number of times it appears in services
     *
     * @return Map of 'service name' as key (String) and 'number of times it appears in services' as value (Integer)
     */
    private Map<String, Integer> countAppointmentsService() {
        Map<String, Integer> map = new HashMap<>();
        List<String> servicesNames = new ArrayList<>();
        listAppointments.forEach(u -> servicesNames.add(u.getService().getName()));

        for (Service service : listService) {
            int count = Collections.frequency(servicesNames, service.getName());
            map.put(service.getName(), count);
        }
        return map;
    }

    /**
     * Return a map | Key = Day | Value = Amount of money
     *
     * @return Map of 'days in actual month' as key (Integer) and 'number of amount of income money in that day' as value (Double)
     */
    private Map<Integer, Double> countIncomeAmountInMonth(int month) {
        Map<Integer, Double> map = new HashMap<>();
        int actualYear = LocalDate.now().getYear();

        //Add day keys
        for (int n = 1; n <= getMaxDayOfMonth(month, actualYear); n++) {
            map.put(n, 0.0);
        }

        //Add amount of money
        listAppointments.forEach(e -> {
            String[] appointmentDate = e.getDate().split("-");
            //Check actual year and month
            if (Integer.parseInt(appointmentDate[0]) == actualYear && Integer.parseInt(appointmentDate[1]) == month) {
                int dayKey = Integer.parseInt(appointmentDate[2]);
                map.put(dayKey, map.get(dayKey) + e.getService().getPrice());
            }
        });
        return map;
    }
}
