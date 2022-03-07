package ies.luisvives.peluqueriadamtpv.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Appointment {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private User user;
    private Service service;

}
