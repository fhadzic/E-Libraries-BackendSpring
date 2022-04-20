package com.example.librarysystem.service;

import com.example.librarysystem.DTO.AuthorDTO;
import com.example.librarysystem.model.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorService {

    public Author saveAuthor(AuthorDTO authorDTO);
    public List<Author> getAll();
    public Author getById(Integer id);
}
