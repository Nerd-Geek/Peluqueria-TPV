package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.UserConfiguration;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Objects;

public class MainController implements BaseController {
    private TranslateTransition toggleTranlate;
    @FXML
    private SplitPane mainPane;
    @FXML
    private StackPane includedViewAppointments, includedViewUsers, includedViewServices, includedViewReports, includedViewSettings;
    @FXML
    private ToggleGroup sidemenutogglebuttons;
    @FXML
    private ToggleButton mainViewSideMenuButtonAppointments, mainViewSideMenuButtonUsers, mainViewSideMenuButtonServices, mainViewSideMenuButtonReports, mainViewSideMenuButtonSettings;
    @FXML
    private HBox searchBox;
    @FXML
    private TextField searchField;
    @FXML
    private AppointmentController includedViewAppointmentsController;
    @FXML
    private UsersController includedViewUsersController;
    @FXML
    private ServiceController includedViewServicesController;

    public void initialize() {
        mainPane.getStylesheets().add(Objects.requireNonNull(this.getClass()
                .getResource(Util.PACKAGE_DIR + "themes/style_" +
                        UserConfiguration.getInstance().getActualTheme().toLowerCase() + ".css")).toExternalForm());

        searchField.setOnKeyPressed(k -> refreshSearch());
    }

    @FXML
    public void onMenuItemSelected() {
        if (mainViewSideMenuButtonAppointments.isSelected()) {
            includedViewAppointments.setVisible(true);
            includedViewAppointments.setDisable(false);
            searchBox.setVisible(true);
            mainViewSideMenuButtonAppointments.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonAppointments);
        } else {
            includedViewAppointments.setVisible(false);
            includedViewAppointments.setDisable(true);
            mainViewSideMenuButtonAppointments.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonAppointments);
        }
        if (mainViewSideMenuButtonUsers.isSelected()) {
            includedViewUsers.setVisible(true);
            includedViewUsers.setDisable(false);
            searchBox.setVisible(true);
            mainViewSideMenuButtonUsers.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonUsers);
        } else {
            includedViewUsers.setVisible(false);
            includedViewUsers.setDisable(true);
            mainViewSideMenuButtonUsers.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonUsers);
        }
        if (mainViewSideMenuButtonServices.isSelected()) {
            includedViewServices.setVisible(true);
            includedViewServices.setDisable(false);
            searchBox.setVisible(true);
            mainViewSideMenuButtonServices.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonServices);
        } else {
            includedViewServices.setVisible(false);
            includedViewServices.setDisable(true);
            mainViewSideMenuButtonServices.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonServices);
        }
        if (mainViewSideMenuButtonReports.isSelected()) {
            includedViewReports.setVisible(true);
            includedViewReports.setDisable(false);
            searchBox.setVisible(false);
            mainViewSideMenuButtonReports.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonReports);
        } else {
            includedViewReports.setVisible(false);
            includedViewReports.setDisable(true);
            mainViewSideMenuButtonReports.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonReports);
        }
        if (mainViewSideMenuButtonSettings.isSelected()) {
            includedViewSettings.setVisible(true);
            includedViewSettings.setDisable(false);
            searchBox.setVisible(false);
            mainViewSideMenuButtonSettings.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonSettings);
        } else {
            includedViewSettings.setVisible(false);
            includedViewSettings.setDisable(true);
            mainViewSideMenuButtonSettings.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonSettings);
        }
    }

    public void animateToggleButton(ToggleButton toggleButton) {
        toggleTranlate = new TranslateTransition(Duration.millis(100), toggleButton);
        if (toggleButton.isSelected()) {
            toggleTranlate.setFromX(toggleButton.getTranslateX());
            toggleTranlate.setToX(toggleButton.getTranslateX() + 25);
        } else {
            // toggleTranlate.setDuration(Duration.millis(1));
            toggleTranlate.setFromX(toggleButton.getLayoutX());
            toggleTranlate.setToX(toggleButton.getLayoutX());
        }
        toggleTranlate.play();
    }

    @FXML
    public void refreshSearch() {
        includedViewAppointmentsController.setSearchQuery(searchField.getText());
        includedViewServicesController.setSearchQuery(searchField.getText());
        includedViewUsersController.setSearchQuery(searchField.getText());
    }
}
