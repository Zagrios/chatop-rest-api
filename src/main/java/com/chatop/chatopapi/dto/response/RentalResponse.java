package com.chatop.chatopapi.dto.response;

import com.chatop.chatopapi.model.Rental;
import lombok.Getter;

import java.util.Date;

@Getter
public class RentalResponse {

    private Long id;
    private String name;
    private float surface;
    private float price;
    private String picture;
    private String description;
    private Long owner_id;
    private Date created_at;
    private Date updated_at;

    public RentalResponse(Rental rental){
        this.id = rental.getId();
        this.name = rental.getName();
        this.surface = rental.getSurface();
        this.price = rental.getPrice();
        this.picture = rental.getPicture();
        this.description = rental.getDescription();
        this.owner_id = rental.getOwner().getId();
        this.created_at = rental.getCreated_at();
        this.updated_at = rental.getUpdated_at();
    }

}
