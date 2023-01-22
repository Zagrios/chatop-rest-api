package com.chatop.chatopapi.service;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import static org.apache.commons.io.FilenameUtils.normalize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    @Value("${file.uploads-dir}")
    private String UPLOAD_DIR;

    @Value("${file.server-url}")
    private String FILE_SERVER_URL;

    private String getFileUrl(String filename){
        return UriComponentsBuilder
                .fromHttpUrl(this.FILE_SERVER_URL+"/{uploadsDir}/{filename}")
                .buildAndExpand(this.UPLOAD_DIR, filename)
                .toUriString();
    }

    public Optional<String> save(MultipartFile file) {
        String filename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        try{
            Files.createDirectories(Paths.get(this.UPLOAD_DIR));
            file.transferTo(Paths.get(this.UPLOAD_DIR, filename));
            return Optional.of(this.getFileUrl(filename));
        }
        catch (IOException e){
            return Optional.empty();
        }
    }

}
