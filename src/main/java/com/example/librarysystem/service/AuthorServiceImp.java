package com.example.librarysystem.service;

import com.example.librarysystem.DTO.AuthorDTO;
import com.example.librarysystem.model.Author;
import com.example.librarysystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class AuthorServiceImp implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setAddress(authorDTO.getAddress());
        author.setEmail(authorDTO.getEmail());
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Integer id) {
        Author author = authorRepository.findById(id).
        orElseThrow(NoSuchElementException::new);
        return author;
    }
}
