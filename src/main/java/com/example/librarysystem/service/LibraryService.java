package com.example.librarysystem.service;

import com.example.librarysystem.DTO.LibraryDTO;
import com.example.librarysystem.model.Library;

import java.util.List;

public interface LibraryService {

    public Library saveLibrary(LibraryDTO libraryDTO);
    public List<Library> getAll();
    public Library getById(Integer id);
    public Library updateLibrary(Integer id, LibraryDTO libraryDTO);
    public void deleteLibrary(Integer id);
    public void deleteAllLibraries();
}
