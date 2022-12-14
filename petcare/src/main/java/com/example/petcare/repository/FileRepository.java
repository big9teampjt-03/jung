package com.example.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petcare.model.FileBoard;



public interface FileRepository extends JpaRepository<FileBoard, Long>{

}
