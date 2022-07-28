package com.example.exercise.repository;

import com.example.exercise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    <T>T getUserById(Integer integer, Class<T> type);
}