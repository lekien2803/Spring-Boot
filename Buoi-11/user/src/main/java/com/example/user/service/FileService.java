package com.example.user.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.sound.midi.Patch;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.user.exception.BadRequestException;
import com.example.user.util.Utils;

@Service
public class FileService {
    // path folder de upload file
    private final Path rootPath = Paths.get("uploads");

    public FileService() {
        createFolder(rootPath.toString());
    }

    // tao folder
    public void createFolder(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // upload file
    public String uploadFile(int id, MultipartFile file) {
        // b1: tao folder tuong ung id
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        // b2: validate file
        validate(file);

        // b3 : tao path tuong ung voi fileUpload;
        String generateFileId = String.valueOf(Instant.now().getEpochSecond());
        File fileServer = new File(userDir + "/" + generateFileId);

        try {
            // su dung Buffer de luu du lieu
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileServer));
            stream.write(file.getBytes());
            stream.close();

            return "/api/v1/users/" + id + "/files/" + generateFileId;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi upload file");
        }

    }

    // validate file
    public void validate(MultipartFile file) {
        // kiem tra ten file
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không hợp lệ");
        }

        // kiem tra duoi file
        String fileExtension = Utils.getExtesion(fileName);
        if (!Utils.checkFileExtesion(fileExtension)) {
            throw new BadRequestException("đuôi File không hợp lệ");
        }

        // kiem tra size
        if ((double) file.getSize() / 1_000_000L > 5) {
            throw new BadRequestException("File không vượt quá 2 MB");
        }
    }

    // xem file
    public byte[] readFile(int id, String fileId) {
        // lay duong dan file tuong ung voi user
        Path userPath = rootPath.resolve(String.valueOf(id));

        // kiem tra userPath co ton tai hay khong
        if (!Files.exists(userPath)) {
            throw new RuntimeException("Lỗi khi đọc file" + fileId);
        }

        try {
            Path file = userPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đọc file");
        }
        return null;
    }

    // xoa file
    public void deleteFile(int id, String fileId) {
        // lay duong dan file tuong ung voi user
        Path userPath = rootPath.resolve(String.valueOf(id));

        if (!Files.exists(userPath)) {
            throw new RuntimeException("Lỗi khi đọc file" + fileId);
        }

        Path file = userPath.resolve(fileId);
        if (!file.toFile().exists()) {
            throw new RuntimeException("file khong ton tai");
        }
        file.toFile().delete();
    }

    // lay danh sach file
    public List<String> getFiles(int id){
        // lay duong dan file tuong ung voi user
        Path userPath = rootPath.resolve(String.valueOf(id));


        // kiem tra userpath co ton tai hay khong
        if (!Files.exists(userPath)) {
            return new ArrayList<>();
        }
        
        // lay danh sach file
        File[] files = userPath.toFile().listFiles();
        return Arrays.stream(files)
            .map(file -> file.getName())
            .sorted(Comparator.reverseOrder())
            .map(file -> "api/v1/users" + id + "/files/" + file)
            .collect(Collectors.toList());
    }
}