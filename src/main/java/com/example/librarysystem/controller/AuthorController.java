package com.example.librarysystem.controller;

import com.example.librarysystem.DTO.AuthorDTO;
import com.example.librarysystem.model.Author;
import com.example.librarysystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<?> createAutor(@RequestBody AuthorDTO authorDTO){
        try {
            Author author = authorService.saveAuthor(authorDTO);
            return new ResponseEntity<>(author, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/all")
    public ResponseEntity<?> getAllAuthors(){
        try {
            List<Author> authors = authorService.getAll();
            if(authors.isEmpty()){
                return new ResponseEntity<>("No authors found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(authors, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable("id") Integer id){
        try {
            Author author = authorService.getById(id);
            return new ResponseEntity<>(author, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
