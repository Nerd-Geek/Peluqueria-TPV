package ies.luisvives.peluqueriadamtpv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Service implements TableEntity{
    private String id;
    private String image;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    @JsonIgnore
    private List<Appointment> appointments;

    @Override
    public String toString() {
        return
                "id='" + id + '\'' +
                        ", image='" + image + '\'' +
                        ", name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", price=" + price +
                        ", stock=" + stock +
                        '}';
    }
}
