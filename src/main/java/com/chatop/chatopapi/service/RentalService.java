package com.chatop.chatopapi.service;

import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> findAll(){
        return this.rentalRepository.findAll();
    }

    public Optional<Rental> findById(Long id){
        return this.rentalRepository.findById(id);
    }

    public Rental save(Rental rental){
        return this.rentalRepository.save(rental);
    }

}
