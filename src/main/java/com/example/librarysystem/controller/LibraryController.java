package com.example.librarysystem.controller;

import com.example.librarysystem.DTO.LibraryDTO;
import com.example.librarysystem.model.Library;
import com.example.librarysystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@CrossOrigin
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/add")
    public ResponseEntity<Library> createStudent(@RequestBody LibraryDTO libraryDTO){
        try {
            Library library = libraryService.saveLibrary(libraryDTO);
            return new ResponseEntity<Library>(library, HttpStatus.CREATED);

        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllLibraries(){
        try {
            List<Library> libraries = libraryService.getAll();
            if(libraries.isEmpty()){
                return new ResponseEntity<>("No libraries found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(libraries, HttpStatus.OK);
        }catch (Exception excaption){
            return new ResponseEntity<>(excaption.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLibraryById(@PathVariable("id") Integer id){
        try{
            Library library = libraryService.getById(id);
            return new ResponseEntity<Library> (library, HttpStatus.OK)  ;
        }catch (Exception excepion){
            return new ResponseEntity<>("Library no found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibrary(@PathVariable("id") Integer id, @RequestBody LibraryDTO libraryDTO){
        try {
            Library library = libraryService.updateLibrary(id, libraryDTO);
            return new ResponseEntity<>(library, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibrary(@PathVariable("id") Integer id){
        try {
            libraryService.deleteLibrary(id);
            return new ResponseEntity<>("Deleted library.", HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllLibraries(){
        try {
            libraryService.deleteAllLibraries();
            return new ResponseEntity<>("Deleted all Libraries.", HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
