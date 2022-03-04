package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.mapper.AppointmentListMapper;
import ies.luisvives.peluqueriadamtpv.model.Appointment;
import ies.luisvives.peluqueriadamtpv.model.Service;
import ies.luisvives.peluqueriadamtpv.model.TableEntity;
import ies.luisvives.peluqueriadamtpv.model.User;
import ies.luisvives.peluqueriadamtpv.restcontroller.APIRestConfig;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.Response;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewController implements BaseController, Initializable, Callback {

    public static final int APPOINTMENT = 0;
    public static final int USER = 1;
    public static final int SERVICE = 2;

    private int type = -1;

    @FXML
    TableView<TableEntity> table;

    private List<TableEntity> elements;

    private final TableColumn<TableEntity, ?> column1;
    private final TableColumn<TableEntity, ?> column2;
    private final TableColumn<TableEntity, ?> column3;
    private final TableColumn<TableEntity, ?> column4;
    private final TableColumn<TableEntity, ?> column5;
    private final TableColumn<TableEntity, ?> column6;
    private final TableColumn<TableEntity, ?> column7;

    @FXML
    private Button details_button;
    @FXML
    private Button view_all;
    @FXML
    private Button delete_button;

	private String searchQuery;

    private String serviceId;

    private LocalDate date;

    private LocalTime time;

	public TableViewController() {
		searchQuery = "";
        serviceId = null;
        date = null;
        time = null;
        elements = new ArrayList<>();
        column1 = new TableColumn<>();
        column2 = new TableColumn<>();
        column3 = new TableColumn<>();
        column4 = new TableColumn<>();
        column5 = new TableColumn<>();
        column6 = new TableColumn<>();
        column7 = new TableColumn<>();
        column1.setSortType(TableColumn.SortType.DESCENDING);
        column2.setSortType(TableColumn.SortType.DESCENDING);
        column3.setSortType(TableColumn.SortType.DESCENDING);
        column4.setSortType(TableColumn.SortType.DESCENDING);
        column5.setSortType(TableColumn.SortType.DESCENDING);
        column6.setSortType(TableColumn.SortType.DESCENDING);
        column7.setSortType(TableColumn.SortType.DESCENDING);
	}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void viewAll() {
        date = null;
        time = null;
        searchQuery = "";
        serviceId = null;
        refreshTable();
    }

    @FXML
    public void delete(ActionEvent e) {
        if (table.getSelectionModel().getSelectedCells().size() == 1) {
            if (confirmDeleteDialog()) {
                String elementId = table.getItems().get(table.getSelectionModel().getFocusedIndex()).getId();
                try {
                    switch (type) {
                        case APPOINTMENT:
                            APIRestConfig.getAppointmentsService().deleteAppointmentById(elementId).execute();
                            break;
                        case USER:
                            APIRestConfig.getUsersService().deleteUser(elementId).execute();
                            break;
                        case SERVICE:
                            APIRestConfig.getServicesService().deleteService(elementId).execute();
                            break;
                    }
                    table.getItems().remove(table.getSelectionModel().getFocusedIndex());
                    deleteDoneDialog();
                } catch (IOException ioException) {
                    System.err.println("Delete not done");
                }
            }
        }
    }

    private boolean confirmDeleteDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Borrado");
        alert.setContentText("Desea borrar?");
        alert.showAndWait();
        if (alert.getResult().getButtonData().isDefaultButton())
            return true;
        else
            return false;
    }

    private void deleteDoneDialog() {
        Alert confirm = new Alert(Alert.AlertType.INFORMATION);
        confirm.setTitle("Borrado");
        confirm.setContentText("Borrado");
        confirm.showAndWait();
    }

    @FXML
    public void details() {
        if (table.getSelectionModel().getSelectedCells().size() == 1) {
            Dialog dialog = new Dialog();
            try {
                DialogPane pane = new DialogPane();
                pane.setContent(Util.getParentRoot("service_item_view"));
                dialog.setDialogPane(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
		}
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
        refreshTable();
	}

    public void setDate(LocalDate date) {
        this.date = date;
        refreshTable();
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

	public void refreshTable() {
        switch(this.type) {
            case APPOINTMENT:
                updateAppointmentItems();
                break;
            case USER:
                updateUserItems();
                break;
            case SERVICE:
                updateServiceItems();
                break;
        }
	}



    public void setEntityForTable(int type) {
        switch(type) {
            case APPOINTMENT:
                loadAppointmentTypeTable();
                this.type = APPOINTMENT;
                break;
            case USER:
                loadUserTypeTable();
                this.type = USER;
                break;
            case SERVICE:
                loadServiceTypeTable();
                this.type = SERVICE;
                break;
        }
    }

    private void updateAppointmentItems() {
        try {
            Response<List<Appointment>> response = APIRestConfig
                    .getAppointmentsService()
                    .appointmentFindByDateAndUser_UsernameContainsIgnoreCaseAndService_Id(searchQuery, date, serviceId)
                    .execute(); //TODO: add the time query
            if (response.body() != null) {
                AppointmentListMapper mapper = new AppointmentListMapper();
                ObservableList<TableEntity> appointments =
                        FXCollections.observableArrayList(mapper.toList(response.body()));
                table.setItems(appointments);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateServiceItems() {
        try {
            Response<List<Service>> response = APIRestConfig.getServicesService().serviceGetAllWithService_name(searchQuery).execute();
            if (response.body() != null) {
                ObservableList<TableEntity> services = FXCollections.observableArrayList((response.body()));
                table.setItems(services);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUserItems() {
        try {
            Response<List<User>> response = APIRestConfig.getUsersService().userGetAllWithUser_name(searchQuery).execute();
            if (response.body() != null) {
                ObservableList<TableEntity> users = FXCollections.observableArrayList((response.body()));
                table.setItems(users);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAppointmentTypeTable() {
        column1.setCellValueFactory(new PropertyValueFactory<>("user"));
        column1.setText("username");
        column2.setCellValueFactory(new PropertyValueFactory<>("service"));
        column2.setText("service");
        column3.setCellValueFactory(new PropertyValueFactory<>("time"));
        column3.setText("time");
        column4.setCellValueFactory(new PropertyValueFactory<>("date"));
        column4.setText("date");
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        updateAppointmentItems();
    }

    private void loadServiceTypeTable() {
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column1.setText("name");
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));
        column2.setText("description");
        column3.setCellValueFactory(new PropertyValueFactory<>("price"));
        column3.setText("price");
        column4.setCellValueFactory(new PropertyValueFactory<>("stock"));
        column4.setText("stock");
        column5.setCellValueFactory(new PropertyValueFactory<>("image"));
        column5.setText("image");
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.getColumns().add(column5);
        updateServiceItems();
    }

    private void loadUserTypeTable() {
        column1.setCellValueFactory(new PropertyValueFactory<>("username"));
        column1.setText("username");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setText("name");
        column3.setCellValueFactory(new PropertyValueFactory<>("surname"));
        column3.setText("surname");
        column4.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        column4.setText("telephone");
        column5.setCellValueFactory(new PropertyValueFactory<>("email"));
        column5.setText("email");
        column6.setCellValueFactory(new PropertyValueFactory<>("gender"));
        column6.setText("gender");
        column7.setCellValueFactory(new PropertyValueFactory<>("image"));
        column7.setText("image");
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.getColumns().add(column5);
        table.getColumns().add(column6);
        table.getColumns().add(column7);
        updateUserItems();
    }

    public void setTime(LocalTime time) {
        this.time = time;
        refreshTable();
    }
}
