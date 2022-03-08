package ies.luisvives.peluqueriadamtpv.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ServiceController implements BaseController {
    @FXML
    private HBox tableView;
    @FXML
    private TableViewController tableViewController;
    @FXML
    private BorderPane insertEntityView;
    @FXML
    private InsertEntityViewController insertEntityViewController;

    private String searchQuery;

    @FXML
    public void initialize() {
        tableViewController.setSearchQuery("");
        tableViewController.setEntityForTable(TableViewController.SERVICE);
        insertEntityViewController.setEntityType(InsertEntityViewController.SERVICE_TYPE);
    }

    public void setSearchQuery(String searchQuery) {
        tableViewController.setSearchQuery(searchQuery);
    }
}