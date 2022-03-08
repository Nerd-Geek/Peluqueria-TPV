package ies.luisvives.peluqueriadamtpv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentList implements TableEntity {
    private String id;
    private String user;
    private String service;
    private String time;
    private String date;
}
