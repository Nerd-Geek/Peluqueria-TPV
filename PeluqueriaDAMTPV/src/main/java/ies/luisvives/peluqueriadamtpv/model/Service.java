package ies.luisvives.peluqueriadamtpv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class Service implements TableEntity{
    private String id;
    private String image;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    @JsonIgnore
    private List<Appointment> appointments;

    public Service(String image, String name, String description, Double price, Integer stock){
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

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
