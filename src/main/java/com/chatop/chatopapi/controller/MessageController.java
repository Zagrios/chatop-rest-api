package com.chatop.chatopapi.controller;

import com.chatop.chatopapi.dto.request.MessageRequest;
import com.chatop.chatopapi.dto.response.StatusResponse;
import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.MessageService;
import com.chatop.chatopapi.service.RentalService;
import com.chatop.chatopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<StatusResponse> sendMessage(@RequestBody @Valid MessageRequest req){

        final Optional<User> user = this.userService.findById(req.getUser_id());

        if(user.isEmpty()){
            return new ResponseEntity<>(new StatusResponse("user not found"), HttpStatus.NOT_FOUND);
        }

        final Optional<Rental> rental = this.rentalService.findById(req.getRental_id());

        if(rental.isEmpty()){
            return new ResponseEntity<>(new StatusResponse("rental not found"), HttpStatus.NOT_FOUND);
        }

        this.messageService.save(
                Message.builder().message(req.getMessage()).rental(rental.get()).user(user.get()).build()
        );

        return ResponseEntity.ok(new StatusResponse("Message send with success"));
    }



}
