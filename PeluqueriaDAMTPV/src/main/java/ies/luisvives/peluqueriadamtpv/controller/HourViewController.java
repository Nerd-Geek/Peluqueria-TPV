package ies.luisvives.peluqueriadamtpv.controller;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.kordamp.ikonli.javafx.FontIcon;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

public class HourViewController {
    @FXML private FontIcon iconTime;

    @FXML private Label hourTitle1;
    @FXML private Label hourTitle2;
    @FXML private Label hourTitle3;
    @FXML private Label hourTitle4;
    @FXML private Label hourTitle5;

    @FXML private Button hourOne;
    @FXML private Button hourOne2;
    @FXML private Button hourOne3;
    @FXML private Button hourOne4;
    @FXML private Button hourOne5;

    @FXML private Button hourTwo;
    @FXML private Button hourTwo2;
    @FXML private Button hourTwo3;
    @FXML private Button hourTwo4;
    @FXML private Button hourTwo5;

    @FXML private Button hourThree;
    @FXML private Button hourThree2;
    @FXML private Button hourThree3;
    @FXML private Button hourThree4;
    @FXML private Button hourThree5;

    @FXML private Button hourFour;
    @FXML private Button hourFour2;
    @FXML private Button hourFour3;
    @FXML private Button hourFour4;
    @FXML private Button hourFour5;

    private static final int DAY = 1;
    private static final int AFTERNOON = 2;
    private static final int NIGHT = 3;

    private int actualPage = DAY;
    private int maxPages = NIGHT;
    private String actualTimeString = "";

    private TableViewController tableViewController;
    private StringProperty actualDate;


    @FXML
    public void initialize(){
        refeshTableContent();
    }

    private void refeshTableContent() {
        updateActualPage();
        updateHoursTable();
        //this.actualTimeString = ""; //TODO: hide user friendly?
    }

    private String convertHourStr(int startHour) {
        if (startHour <= 9){
            return "0" + startHour;
        }else{
            return startHour + "";
        }
    }

    @FXML
    protected void onButtonHourPressed(ActionEvent event) {
        Button b = (Button) event.getSource();

        //TODO: TEST
        if (!Objects.equals(actualDate.get(), "")){
            actualTimeString = b.getText();
            LocalTime time = stringToTime(actualTimeString);
            System.out.println("REST petition with time " + time);
            tableViewController.setTime(time); //TODO: DO
        }
    }

    private LocalTime stringToTime(String str) {
        String[] separated = str.split(":");
        int selectedHour = Integer.parseInt(separated[0]);
        int selectedMinute = Integer.parseInt(separated[1]);
        return LocalTime.of(selectedHour, selectedMinute);
    }

    private int getMinuteFromButton(String str) { //TODO: DEL?
        return Integer.parseInt(str.split(":")[1]);
    }

    public void setExternalData(TableViewController tableViewController, StringProperty actualDate) {
        this.tableViewController = tableViewController;
        this.actualDate = actualDate;
        this.actualDate.addListener(e -> this.refreshHoursTable());
    }

    public Optional<String> getActualTimeString(){
        if (Objects.equals(this.actualTimeString, "")){
            return Optional.empty();
        }else{
            return Optional.of(this.actualTimeString);
        }
    }

    public void refreshHoursTable(){
        actualTimeString = "";
        updateHoursTable();
    }

    private void updateHoursTable() {
        int startHour = 8;
        int columnQuantity = 5;

        if (actualPage == AFTERNOON){
            startHour += columnQuantity;
        } else if (actualPage == NIGHT){
            startHour += (columnQuantity) * 2;
        }

        //Hour titles
        hourTitle1.setText(convertHourStr(startHour));
        hourTitle2.setText(convertHourStr(startHour + 1));
        hourTitle3.setText(convertHourStr(startHour + 2));
        hourTitle4.setText(convertHourStr(startHour + 3));
        hourTitle5.setText(convertHourStr(startHour + 4));

//        List<Button> groupButtons = new ArrayList();
//        groupButtons.addAll(List.of(hourOne,hourOne2,hourOne3,hourOne4,hourOne5));
//        for (int n = 0; n < groupButtons.size(); n++){
//            groupButtons.get(n).setText(startHour + n + ":00");
//        }

        //00 minutes column
        hourOne.setText(startHour + ":00");
        hourOne2.setText(startHour + 1 + ":00");
        hourOne3.setText(startHour + 2 + ":00");
        hourOne4.setText(startHour + 3 + ":00");
        hourOne5.setText(startHour + 4 + ":00");

        //15 minutes column
        hourTwo.setText(startHour + ":15");
        hourTwo2.setText(startHour + 1 + ":15");
        hourTwo3.setText(startHour + 2 + ":15");
        hourTwo4.setText(startHour + 3 + ":15");
        hourTwo5.setText(startHour + 4 + ":15");

        //30 minutes column
        hourThree.setText(startHour + ":30");
        hourThree2.setText(startHour + 1 + ":30");
        hourThree3.setText(startHour + 2 + ":30");
        hourThree4.setText(startHour + 3 + ":30");
        hourThree5.setText(startHour + 4 + ":30");

        //45 minutes column
        hourFour.setText(startHour + ":45");
        hourFour2.setText(startHour + 1 + ":45");
        hourFour3.setText(startHour + 2 + ":45");
        hourFour4.setText(startHour + 3 + ":45");
        hourFour5.setText(startHour + 4 + ":45");
    }

    private void updateActualPage() {
        if (actualPage == DAY){
            iconTime.setIconLiteral("fas-sun");
        }else if (actualPage == AFTERNOON) {
            iconTime.setIconLiteral("fas-cloud-sun");
        }else if (actualPage == NIGHT) {
            iconTime.setIconLiteral("fas-moon");
        }
    }

    @FXML
    protected void prevHourButtonAction() {
        if (actualPage > 1){
            actualPage -=1;
        }
        refeshTableContent();
    }

    @FXML
    protected void nextHourButtonAction() {
        if (actualPage < maxPages){
            actualPage +=1;
        }
        refeshTableContent();
    }
}
