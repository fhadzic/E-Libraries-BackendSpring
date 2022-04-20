package com.example.librarysystem.controller;

import com.example.librarysystem.DTO.BookDTO;
import com.example.librarysystem.model.Author;
import com.example.librarysystem.model.Book;
import com.example.librarysystem.model.Library;
import com.example.librarysystem.service.AuthorService;
import com.example.librarysystem.service.BookService;
import com.example.librarysystem.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/add")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO){
        try {
            Book book = bookService.saveBookDTO(bookDTO);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        try {
            List<Book> books = bookService.getAll();
            if(books.isEmpty()){
                return new ResponseEntity<>("No books found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<?> getBookById(@RequestParam Integer id){
        try {
            Book book = bookService.getById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/title")
    public ResponseEntity<?> getBookByTitle(@RequestParam String title){
        try {
            List<Book> books = bookService.getByTitle(title);
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/library")
    public ResponseEntity<?> getBookByTitle(@RequestParam(value = "id") Integer id){
        try {
            List<Book> books = bookService.getAllByLibraryId(id);
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{bookId}/author/{authorId}")
    ResponseEntity<?> enrolledAuthorToBook(
            @PathVariable Integer bookId,
            @PathVariable Integer authorId
    ){
        try {
            Book book = bookService.getById(bookId);
            Author author = authorService.getById(authorId);
            book.enrolledAuthor(author);
            return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{bookId}/library/{libraryId}")
    ResponseEntity<?> assignLibraryToBook(
            @PathVariable Integer bookId,
            @PathVariable Integer libraryId
    ){
        try {
            Book book = bookService.getById(bookId);
            Library library = libraryService.getById(libraryId);
            book.assignLibrary(library);
            return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
