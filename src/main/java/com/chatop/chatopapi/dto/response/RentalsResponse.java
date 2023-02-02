package com.chatop.chatopapi.dto.response;

import com.chatop.chatopapi.model.Rental;
import lombok.Getter;

import java.util.List;

@Getter
public class RentalsResponse {

    private List<RentalResponse> rentals;

    public RentalsResponse(List<Rental> rentals){
        this.rentals = rentals.stream().map(RentalResponse::new).toList();
    }

}
