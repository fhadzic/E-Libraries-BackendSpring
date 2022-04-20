package com.example.librarysystem.service;

import com.example.librarysystem.DTO.BookDTO;
import com.example.librarysystem.model.Book;

import java.util.List;

public interface BookService {

    public Book saveBookDTO(BookDTO bookDTO);
    public Book saveBook(Book book);
    public List<Book> getAll();
    public Book getById(Integer id);
    public List<Book> getByTitle(String title);
    public List<Book> getAllByLibraryId(Integer id);
}
