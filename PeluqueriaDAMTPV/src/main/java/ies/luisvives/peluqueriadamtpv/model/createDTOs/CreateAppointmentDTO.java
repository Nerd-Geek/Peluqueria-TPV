package ies.luisvives.peluqueriadamtpv.model.createDTOs;

import lombok.Data;
import java.util.UUID;

@Data
public class CreateAppointmentDTO {
    private String id = UUID.randomUUID().toString();
    private String date;
    private String time;
    private String userId;
    private String serviceId;
}
