package com.chatop.chatopapi.controller;

import com.chatop.chatopapi.dto.response.RentalResponse;
import com.chatop.chatopapi.dto.response.RentalsResponse;
import com.chatop.chatopapi.dto.response.StatusResponse;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.FileService;
import com.chatop.chatopapi.service.FileValidationService;
import com.chatop.chatopapi.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private FileValidationService fileValidationService;

    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<RentalsResponse> getRentals(){
        final List<Rental> rentals = this.rentalService.findAll();
        return ResponseEntity.ok(new RentalsResponse(rentals));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRental(@PathVariable("id") Long id){
        final Optional<Rental> rental = this.rentalService.findById(id);
        if(rental.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new RentalResponse(rental.get()));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StatusResponse> createRental(
            @RequestParam("name") String name,
            @RequestParam("surface") float surface,
            @RequestParam("price") float price,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("description") String description
    ) {

        if(!this.fileValidationService.isImage(picture)){
            return new ResponseEntity<>(new StatusResponse("Invalid picture type"), HttpStatus.BAD_REQUEST);
        }

        Optional<String> pictureUrl = this.fileService.save(picture);

        if(pictureUrl.isEmpty()){
            return new ResponseEntity<>(new StatusResponse("Unable to save picture"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Rental rental = Rental.builder()
                .owner(owner)
                .name(name)
                .surface(surface)
                .price(price)
                .picture(pictureUrl.get())
                .description(description)
                .build();

        this.rentalService.save(rental);

        return ResponseEntity.ok(new StatusResponse("Rental created !"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponse> updateRental(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("surface") float surface,
            @RequestParam("price") float price,
            @RequestParam("description") String description
    ){
        final Optional<Rental> rental = this.rentalService.findById(id);

        if(rental.isEmpty()){
            return new ResponseEntity<>(new StatusResponse("Rental not found"), HttpStatus.NOT_FOUND);
        }

        final Rental updateRental = rental.get();

        updateRental.setName(name);
        updateRental.setSurface(surface);
        updateRental.setPrice(price);
        updateRental.setDescription(description);

        this.rentalService.save(updateRental);

        return ResponseEntity.ok(new StatusResponse("Rental updated !"));
    }



}
