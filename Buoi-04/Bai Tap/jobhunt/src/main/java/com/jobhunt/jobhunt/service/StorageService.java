package com.jobhunt.jobhunt.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.jobhunt.jobhunt.exception.StorageException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    @Value("${upload.path}")
    private String path;

    public String saveFile(MultipartFile file, String id) throws IOException {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        String extension = getFileExtension(file.getOriginalFilename());
        String newFileName = path + id + "." + extension;
        try {
            var is = file.getInputStream();
            Files.copy(is, Paths.get(newFileName), StandardCopyOption.REPLACE_EXISTING);
            return id + "." + extension;
        } catch (IOException e) {
            var msg = String.format("Failed to store file %", newFileName);
            throw new StorageException(msg, e);
        }

    }

    /*
     * Bóc tách file extension từ file name. Ví dụ
     * input: pic1.png
     * output: png
     */
    public String getFileExtension(String fileName) {
        int dot = fileName.lastIndexOf(".");

        if (dot >= 0) {
            return fileName.substring(dot + 1);
        } else {
            return null;
        }
    }

    public void deleteFile(String logoPath) {
        String filePath = path + logoPath;
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            var msg = String.format("Failed to delete file %", filePath);
            throw new StorageException(msg, e);
        }
    }
}
