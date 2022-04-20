package com.example.librarysystem.service;

import com.example.librarysystem.DTO.BookDTO;
import com.example.librarysystem.model.Book;
import com.example.librarysystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImp implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBookDTO(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        return bookRepository.save(book);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        return book;
    }

    @Override
    public List<Book> getByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books;
    }

    @Override
    public List<Book> getAllByLibraryId(Integer id) {
        List<Book> books = bookRepository.findByLibraryId(id);
        return books;
    }
}
