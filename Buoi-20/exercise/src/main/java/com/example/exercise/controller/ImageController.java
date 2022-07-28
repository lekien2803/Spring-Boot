package com.example.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercise.entity.Image;
import com.example.exercise.service.ImageService;

@RestController
@RequestMapping("upload/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] readImage(@PathVariable("id") Integer id){
        Image image = imageService.findById(id);
        return imageService.readImage(image.getLink());
    }

}
