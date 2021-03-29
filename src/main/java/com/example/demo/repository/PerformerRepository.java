package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Performer;


public interface PerformerRepository extends JpaRepository<Performer, Long> {

}