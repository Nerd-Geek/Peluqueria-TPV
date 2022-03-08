package ies.luisvives.peluqueriadamtpv.model.createDTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateService{
    private String id = UUID.randomUUID().toString();
    private String image;
    private String name;
    private String description;
    private Double price;
    private Integer stock;

    public CreateService(String image, String name, String description, Double price, Integer stock) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
