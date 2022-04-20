package com.example.librarysystem.repository;

import com.example.librarysystem.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {
    Optional<Library> findById(Integer id);
}
