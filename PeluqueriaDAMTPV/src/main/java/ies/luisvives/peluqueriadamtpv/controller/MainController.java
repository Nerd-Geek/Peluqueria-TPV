package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.UserConfiguration;
import ies.luisvives.peluqueriadamtpv.utils.Util;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

public class MainController implements BaseController{
    TranslateTransition toggleTranlate;
    @FXML
    public SplitPane mainPane;
    @FXML
    StackPane includedViewAppointments, includedViewUsers, includedViewServices, includedViewReports, includedViewSettings;
    @FXML
    ToggleGroup sidemenutogglebuttons;
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
    public void onMenuItemSelected(){
        if (mainViewSideMenuButtonAppointments.isSelected()){
            includedViewAppointments.setVisible(true);
            includedViewAppointments.setDisable(false);
            search_box.setVisible(true);
            mainViewSideMenuButtonAppointments.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonAppointments);
        }else{
            includedViewAppointments.setVisible(false);
            includedViewAppointments.setDisable(true);
            mainViewSideMenuButtonAppointments.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonAppointments);
        }
        if (mainViewSideMenuButtonUsers.isSelected()){
            includedViewUsers.setVisible(true);
            includedViewUsers.setDisable(false);
            search_box.setVisible(true);
            mainViewSideMenuButtonUsers.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonUsers);
        }else{
            includedViewUsers.setVisible(false);
            includedViewUsers.setDisable(true);
            mainViewSideMenuButtonUsers.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonUsers);
        }
        if (mainViewSideMenuButtonServices.isSelected()){
            includedViewServices.setVisible(true);
            includedViewServices.setDisable(false);
            search_box.setVisible(true);
            mainViewSideMenuButtonServices.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonServices);
        }else{
            includedViewServices.setVisible(false);
            includedViewServices.setDisable(true);
            mainViewSideMenuButtonServices.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonServices);
        }
        if (mainViewSideMenuButtonReports.isSelected()){
            includedViewReports.setVisible(true);
            includedViewReports.setDisable(false);
            search_box.setVisible(false);
            mainViewSideMenuButtonReports.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonReports);
        }else{
            includedViewReports.setVisible(false);
            includedViewReports.setDisable(true);
            mainViewSideMenuButtonReports.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonReports);
        }
        if (mainViewSideMenuButtonSettings.isSelected()){
            includedViewSettings.setVisible(true);
            includedViewSettings.setDisable(false);
            search_box.setVisible(false);
            mainViewSideMenuButtonSettings.setDisable(true);
            animateToggleButton(mainViewSideMenuButtonSettings);
        }else{
            includedViewSettings.setVisible(false);
            includedViewSettings.setDisable(true);
            mainViewSideMenuButtonSettings.setDisable(false);
            animateToggleButton(mainViewSideMenuButtonSettings);
        }
    }
    public void animateToggleButton(ToggleButton toggleButton){
        toggleTranlate = new TranslateTransition(Duration.millis(100),toggleButton);
        if(toggleButton.isSelected()){
        toggleTranlate.setFromX(toggleButton.getTranslateX());
        toggleTranlate.setToX(toggleButton.getTranslateX()+25);}
        else{
           // toggleTranlate.setDuration(Duration.millis(1));
            toggleTranlate.setFromX(toggleButton.getLayoutX());
            toggleTranlate.setToX(toggleButton.getLayoutX());
        }
        toggleTranlate.play();
    }
    @FXML
    public void onSearchButtonClick() {
        includedViewAppointmentsController.setSearchQuery(search_field.getText());
        includedViewServicesController.setSearchQuery(search_field.getText());
        includedViewUsersController.setSearchQuery(search_field.getText());
    }
}
