package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Genre;


public interface GenreRepository extends JpaRepository<Genre, Long> {

}