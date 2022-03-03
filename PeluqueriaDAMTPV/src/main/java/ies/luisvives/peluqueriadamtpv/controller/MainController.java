package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.UserConfiguration;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class MainController {
    @FXML
    public SplitPane mainPane;
    @FXML
    StackPane includedViewAppointments, includedViewUsers, includedViewServices, includedViewReports, includedViewSettings;
    @FXML
    ToggleButton mainViewSideMenuButtonAppointments, mainViewSideMenuButtonUsers, mainViewSideMenuButtonServices, mainViewSideMenuButtonReports, mainViewSideMenuButtonSettings;
    @FXML
    HBox search_box;
    @FXML
    TextField search_field;
    @FXML
    AppointmentController includedViewAppointmentsController;
    @FXML
    UsersController includedViewUsersController;
    @FXML
    ServiceController includedViewServicesController;

    public void initialize() {
        mainPane.getStylesheets().add(Objects.requireNonNull(this.getClass()
                .getResource(Util.PACKAGE_DIR + "themes/style_" +
                        UserConfiguration.getInstance().getActualTheme().toLowerCase()
                        + ".css")).toExternalForm());
    }

    @FXML
    public void onMenuItemAppointmentsMouseClicked() {
        includedViewAppointments.setVisible(true);
        includedViewAppointments.setDisable(false);
        includedViewUsers.setVisible(false);
        includedViewUsers.setDisable(true);
        includedViewServices.setVisible(false);
        includedViewServices.setDisable(true);
        includedViewReports.setVisible(false);
        includedViewReports.setDisable(true);
        includedViewSettings.setVisible(false);
        includedViewSettings.setDisable(true);
        search_box.setVisible(true);

    }

    @FXML
    public void onMenuItemUsersMouseClicked() {
        includedViewAppointments.setVisible(false);
        includedViewAppointments.setDisable(true);
        includedViewUsers.setVisible(true);
        includedViewUsers.setDisable(false);
        includedViewServices.setVisible(false);
        includedViewServices.setDisable(true);
        includedViewReports.setVisible(false);
        includedViewReports.setDisable(true);
        includedViewSettings.setVisible(false);
        includedViewSettings.setDisable(true);
        search_box.setVisible(true);

    }

    @FXML
    public void onMenuItemServicesMouseClicked() {
        includedViewAppointments.setVisible(false);
        includedViewAppointments.setDisable(true);
        includedViewUsers.setVisible(false);
        includedViewUsers.setDisable(true);
        includedViewServices.setVisible(true);
        includedViewServices.setDisable(false);
        includedViewReports.setVisible(false);
        includedViewReports.setDisable(true);
        includedViewSettings.setVisible(false);
        includedViewSettings.setDisable(true);
        search_box.setVisible(true);

    }

    @FXML
    public void onMenuItemReportsMouseClicked() {
        includedViewAppointments.setVisible(false);
        includedViewAppointments.setDisable(true);
        includedViewUsers.setVisible(false);
        includedViewUsers.setDisable(true);
        includedViewServices.setVisible(false);
        includedViewServices.setDisable(true);
        includedViewReports.setVisible(true);
        includedViewReports.setDisable(false);
        includedViewSettings.setVisible(false);
        includedViewSettings.setDisable(true);
        search_box.setVisible(false);
    }

    @FXML
    public void onMenuItemSettingsMouseClicked() {
        includedViewAppointments.setVisible(false);
        includedViewAppointments.setDisable(true);
        includedViewUsers.setVisible(false);
        includedViewUsers.setDisable(true);
        includedViewServices.setVisible(false);
        includedViewServices.setDisable(true);
        includedViewReports.setVisible(false);
        includedViewReports.setDisable(true);
        includedViewSettings.setVisible(true);
        includedViewSettings.setDisable(false);
    }

    @FXML
    public void onSearchButtonClick() {
        includedViewAppointmentsController.setSearchQuery(search_field.getText());
        includedViewServicesController.setSearchQuery(search_field.getText());
        includedViewUsersController.setSearchQuery(search_field.getText());
    }
}
