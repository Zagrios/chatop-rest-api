package com.chatop.chatopapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class FileValidationService {

    public boolean isImage(MultipartFile file){
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
