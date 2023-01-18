package com.chatop.chatopapi.controller;

import com.chatop.chatopapi.dto.response.StatusResponse;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public List<Rental> getRentals(){
        return this.rentalService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Rental> getRental(@PathVariable("id") Long id){
        return this.rentalService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<StatusResponse> createRental(){
        return ResponseEntity.ok(new StatusResponse("ok"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponse> updateRental(){
        return ResponseEntity.ok(new StatusResponse("ok"));
    }



}
