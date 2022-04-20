package com.example.librarysystem.service;

import com.example.librarysystem.DTO.LibraryDTO;
import com.example.librarysystem.model.Library;
import com.example.librarysystem.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibraryServiceImp implements LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public Library saveLibrary(LibraryDTO libraryDTO) {
        Library library = new Library();
        library.setName(libraryDTO.getName());
        library.setOpeningDate(libraryDTO.getOpeningDate());
        library.setAddress(libraryDTO.getAddress());
        return libraryRepository.save(library);
    }

    @Override
    public List<Library> getAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Library getById(Integer id) {
        Library library = libraryRepository.findById(id).
                orElseThrow(NoSuchElementException::new);
        return library;
    }

    @Override
    public Library updateLibrary(Integer id, LibraryDTO libraryDTO) {
        Library library = libraryRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("Library no found.") );
        if(libraryDTO.getName() != null) library.setName(libraryDTO.getName());
        if(libraryDTO.getAddress() != null) library.setAddress(libraryDTO.getAddress());
        if(libraryDTO.getOpeningDate() != null) library.setOpeningDate(libraryDTO.getOpeningDate());

        return libraryRepository.save(library);
    }

    @Override
    public void deleteLibrary(Integer id) {
        libraryRepository.deleteById(id);
    }

    @Override
    public void deleteAllLibraries() {
        libraryRepository.deleteAll();
    }
}
