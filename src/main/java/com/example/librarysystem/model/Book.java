package com.example.librarysystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Author> authors = new ArrayList<>();

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "library_id", referencedColumnName = "id")
    private Library library;

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Library getLibrary() {
        return library;
    }

    public void enrolledAuthor(Author author) {
        authors.add(author);
    }

    public void assignLibrary(Library library) {
        this.library = library;
    }
}
