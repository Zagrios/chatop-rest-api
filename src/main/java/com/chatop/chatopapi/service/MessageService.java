package com.chatop.chatopapi.service;

import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message){
        return this.messageRepository.save(message);
    }

}
