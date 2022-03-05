package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.model.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServiceItemController implements BaseController {
    @FXML
    private Label nameService;
    @FXML
    private Label descriptionService;
    @FXML
    private Label priceService;
    @FXML
    private Label stockService;

    private Service service;

    public ServiceItemController(Service service) {
        this.service = service;
    }

    @FXML
    public void initialize() {
        nameService.setText(service.getName());
        descriptionService.setText(service.getDescription());
        priceService.setText(service.getPrice() + "");
        stockService.setText(service.getStock() + "");
    }
}
