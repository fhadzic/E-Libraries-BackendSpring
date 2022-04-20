package com.example.librarysystem.repository;

import com.example.librarysystem.model.Book;
import com.example.librarysystem.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findById(Integer id);
    List<Book> findByTitle(String title);
    List<Book> findByLibraryId(Integer id);
}
