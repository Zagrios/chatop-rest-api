package com.chatop.chatopapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileValidationService {

    public boolean isImage(MultipartFile file){
        try (InputStream input = file.getInputStream()) {
            try {
                ImageIO.read(input).toString();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        catch (IOException e){
            return false;
        }
    }

}
