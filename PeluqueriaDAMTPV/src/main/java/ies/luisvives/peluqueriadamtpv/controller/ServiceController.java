package ies.luisvives.peluqueriadamtpv.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ServiceController implements BaseController{
    @FXML
    TextField stockService;
    @FXML
    TextField priceService;
    @FXML
    TextField descriptionService;
    @FXML
    TextField nombreService;
    @FXML
    HBox tableView;
    @FXML
    TableViewController tableViewController;
    @FXML
    VBox insertEntityView;
    @FXML
    InsertEntityViewController insertEntityViewController;

    private String searchQuery;
//    @FXML
//    TableView<Service> listService;
//
//    private final TableColumn<Service, String> name = new TableColumn("name");
//    private final TableColumn<Service, String> description = new TableColumn("description");
//    private final TableColumn<Service, Double> price = new TableColumn("price");
//    private final TableColumn<Service, Integer> stock = new TableColumn("stock");
//    private final TableColumn<Service, String> image = new TableColumn("image");
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        listService.getColumns().addAll(name, description, price, stock, image);
//        onTableItemService();
//    }
    @FXML
    public void initialize() {
        tableViewController.setSearchQuery("");
        tableViewController.setEntityForTable(TableViewController.SERVICE);
        insertEntityViewController.setEntityType(InsertEntityViewController.SERVICE_TYPE);
    }

    public void setSearchQuery(String searchQuery) {
        tableViewController.setSearchQuery(searchQuery);
    }
//
//    @FXML
//    public void onTableItemService() {
//        try {
//            Response<List<Service>> service = Objects.requireNonNull(APIRestConfig.getServicesService().serviceGetAll().execute());
//            if (service.body() != null) {
//
//                ObservableList<Service> services =
//                        FXCollections.observableArrayList();
//                services.addAll(service.body());
//                name.setCellValueFactory(new PropertyValueFactory<>("name"));
//                description.setCellValueFactory(new PropertyValueFactory<>("description"));
//                price.setCellValueFactory(new PropertyValueFactory<>("price"));
//                stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
//                image.setCellValueFactory(new PropertyValueFactory<>("image"));
//
//                name.setSortType(TableColumn.SortType.DESCENDING);
//                price.setSortType(TableColumn.SortType.DESCENDING);
//                stock.setSortType(TableColumn.SortType.DESCENDING);
//                listService.setItems(services);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    public void deleteService(ActionEvent event) {
//        if (listService.getSelectionModel().getSelectedCells().size() == 1) {
//            String serviceID = listService.getItems().get(listService.getSelectionModel().getFocusedIndex()).getId();
//            try {
//                APIRestConfig.getServicesService().deleteService(serviceID).execute();
//                listService.getItems().remove(listService.getSelectionModel().getFocusedIndex());
//                System.out.println("delete done");
//            } catch (IOException ioException) {
//                System.err.println("Delete not done");
//            }
//        }
//    }
//
    @FXML
    private void insertService() throws IOException {
//        ObservableList<Service> services = FXCollections.observableArrayList();
//        Service service = new Service();
//        if (!nombreService.getText().equals("")) {
//            service.setId(UUID.randomUUID().toString());
//            service.setName(nombreService.getText());
//            service.setDescription(descriptionService.getText());
//            service.setStock(Integer.valueOf(stockService.getText()));
//            service.setPrice(Double.valueOf(priceService.getText()));
//
//            APIRestConfig.getServicesService().insertService(service).execute();
//            Response<List<Service>> serviceList = Objects.requireNonNull(APIRestConfig.getServicesService().serviceGetAll().execute());
//            if (serviceList.body() != null) {
//                services.addAll(serviceList.body());
//                listService.setItems(services);
//            }
//        }
    }
//
//    @FXML
//    private void updateService() {
//        ObservableList<Service> services = FXCollections.observableArrayList();
//        Service service = new Service();
////        service.setName();
////        service.setDescription();
////        service.setStock();
////        service.setPrice();
////        APIRestConfig.getServicesService().updateService(service).execute();
//        //TODO: why commented?
//        try {
//            Response<List<Service>> serviceList = Objects.requireNonNull(APIRestConfig.getServicesService().serviceGetAll().execute());
//            if (serviceList.body() != null) {
//                services.addAll(serviceList.body());
//                listService.setItems(services);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}