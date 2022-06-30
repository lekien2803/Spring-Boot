package com.example.quanhecacbang.repository;

import com.example.quanhecacbang.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    List<Image> getImagesByUserId(Integer id);
}