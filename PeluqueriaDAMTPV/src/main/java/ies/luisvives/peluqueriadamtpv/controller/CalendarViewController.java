package ies.luisvives.peluqueriadamtpv.controller;

import ies.luisvives.peluqueriadamtpv.restcontroller.APIRestConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class CalendarViewController {
    private Calendar calendar;
    private List<List<Button>> gridButtons;
    @FXML
    private Button day_button_0_0;
    @FXML
    private Button day_button_0_1;
    @FXML
    private Button day_button_0_2;
    @FXML
    private Button day_button_0_3;
    @FXML
    private Button day_button_0_4;
    @FXML
    private Button day_button_0_5;
    @FXML
    private Button day_button_0_6;
    @FXML
    private Button day_button_1_0;
    @FXML
    private Button day_button_1_1;
    @FXML
    private Button day_button_1_2;
    @FXML
    private Button day_button_1_3;
    @FXML
    private Button day_button_1_4;
    @FXML
    private Button day_button_1_5;
    @FXML
    private Button day_button_1_6;
    @FXML
    private Button day_button_2_0;
    @FXML
    private Button day_button_2_1;
    @FXML
    private Button day_button_2_2;
    @FXML
    private Button day_button_2_3;
    @FXML
    private Button day_button_2_4;
    @FXML
    private Button day_button_2_5;
    @FXML
    private Button day_button_2_6;
    @FXML
    private Button day_button_3_0;
    @FXML
    private Button day_button_3_1;
    @FXML
    private Button day_button_3_2;
    @FXML
    private Button day_button_3_3;
    @FXML
    private Button day_button_3_4;
    @FXML
    private Button day_button_3_5;
    @FXML
    private Button day_button_3_6;
    @FXML
    private Button day_button_4_0;
    @FXML
    private Button day_button_4_1;
    @FXML
    private Button day_button_4_2;
    @FXML
    private Button day_button_4_3;
    @FXML
    private Button day_button_4_4;
    @FXML
    private Button day_button_4_5;
    @FXML
    private Button day_button_4_6;
    @FXML
    private Button day_button_5_0;
    @FXML
    private Button day_button_5_1;
    @FXML
    private Button day_button_5_2;
    @FXML
    private Button day_button_5_3;
    @FXML
    private Button day_button_5_4;
    @FXML
    private Button day_button_5_5;
    @FXML
    private Button day_button_5_6;
    @FXML
    private Label month_string;
    @FXML
    private Label year_string;
    @FXML
    private Button prev_month_button;
    @FXML
    private Button next_month_button;
    @FXML
    private TableViewController tableViewController;

    public CalendarViewController() {
        gridButtons = new ArrayList<>();
        calendar = Calendar.getInstance();
    }

    @FXML
    public void initialize() {
        gridButtons.add(List.of(day_button_0_0, day_button_0_1, day_button_0_2, day_button_0_3, day_button_0_4, day_button_0_5, day_button_0_6));
        gridButtons.add(List.of(day_button_1_0, day_button_1_1, day_button_1_2, day_button_1_3, day_button_1_4, day_button_1_5, day_button_1_6));
        gridButtons.add(List.of(day_button_2_0, day_button_2_1, day_button_2_2, day_button_2_3, day_button_2_4, day_button_2_5, day_button_2_6));
        gridButtons.add(List.of(day_button_3_0, day_button_3_1, day_button_3_2, day_button_3_3, day_button_3_4, day_button_3_5, day_button_3_6));
        gridButtons.add(List.of(day_button_4_0, day_button_4_1, day_button_4_2, day_button_4_3, day_button_4_4, day_button_4_5, day_button_4_6));
        gridButtons.add(List.of(day_button_5_0, day_button_5_1, day_button_5_2, day_button_5_3, day_button_5_4, day_button_5_5, day_button_5_6));
        setButtonNamesForMonthYear();
        updateMonthYearLabel();
    }

    private void updateMonthYearLabel() {
        DateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        month_string.setText(monthFormat.format(calendar.getTime()));
        year_string.setText(calendar.get(Calendar.YEAR) + "");
    }

    @FXML
    public void nextMonthButtonAction() {
        if (calendar.get(Calendar.MONTH)== Calendar.DECEMBER)
            calendar.set(calendar.get(Calendar.YEAR) + 1, Calendar.JANUARY, calendar.get(Calendar.DAY_OF_MONTH));
        else
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        setButtonNamesForMonthYear();
        updateMonthYearLabel();
    }

    @FXML
    public void prevMonthButtonAction() {
        if (calendar.get(Calendar.MONTH)== Calendar.JANUARY)
            calendar.set(calendar.get(Calendar.YEAR) - 1, Calendar.DECEMBER, calendar.get(Calendar.DAY_OF_MONTH));
        else
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, calendar.get(Calendar.DAY_OF_MONTH));
        setButtonNamesForMonthYear();
        updateMonthYearLabel();
    }

    @FXML
    public void onCalendarDayAction (ActionEvent event) {
        System.out.println("Button pressed");
        LocalDate date = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, Integer.parseInt(((Button)event.getSource()).getText()));
        System.out.println("REST petition with date " + date);
        tableViewController.setDate(date);
    }

    private void setButtonNamesForMonthYear() {
        gridButtons.forEach(l -> l.forEach(b -> {
            b.setText("");
            b.setDisable(true);
        }));
        int firstDayIndex = calculateFirstDayPosition(calendar);
        int[] lastPosition = calculateLastDayPosition(calendar, firstDayIndex);
        int lastDayIndex = 6;

        int day = 1;
        System.out.println(lastPosition[0] + " " + lastPosition[1]);
        for (int i = 0; i < lastPosition[1]; i++) {
            if (i != 0) firstDayIndex = 0;
            if (i == lastPosition[1] - 1) {
                lastDayIndex = lastPosition[0];
            }
            for (int j = firstDayIndex; j <= lastDayIndex; j++) {
                gridButtons.get(i).get(j).setText(day + "");
                gridButtons.get(i).get(j).setDisable(false);

//          this if statement enables only buttons that are present day or future
//                if (
//                        (day >= calendar.get(Calendar.DAY_OF_MONTH)
//                            && calendar.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
//                            && calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
//                        )
//                                || calendar.getTime().after(Calendar.getInstance().getTime()))
//                    gridButtons.get(i).get(j).setDisable(false);
                day++;
            }
        }
    }

    private int[] calculateLastDayPosition(Calendar calendar, int firstDayIndex) {
        Calendar lastDayOfMonth = Calendar.getInstance();
        lastDayOfMonth.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DATE));
        int nRows = 0;
        if ((firstDayIndex + calendar.getActualMaximum(Calendar.DATE))%7 == 0)
            nRows = (firstDayIndex + calendar.getActualMaximum(Calendar.DATE))/7;
        else
            nRows = (firstDayIndex + calendar.getActualMaximum(Calendar.DATE))/7 + 1;

        if (lastDayOfMonth.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return new int[]{ 6, nRows};
        }
        else {
            return new int[]{lastDayOfMonth.get(Calendar.DAY_OF_WEEK) - 2, nRows};
        }
    }

    private int calculateFirstDayPosition(Calendar calendar) {
        Calendar firstDayOfMonth = Calendar.getInstance();
        firstDayOfMonth.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        if (firstDayOfMonth.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) return 6;
        else return firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 2;
    }

    public void setTableViewController(TableViewController tableViewController) {
        this.tableViewController = tableViewController;
    }
}
