package com.example.Library.controller;

import com.example.Library.dto.LibrarianDto;
import com.example.Library.dto.validation.LoginValidation;
import com.example.Library.dto.validation.ValidationOrder;
import com.example.Library.entities.Librarian;
import com.example.Library.mapper.LibrarianMapper;
import com.example.Library.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Validated(ValidationOrder.class) LibrarianDto librarianDto) {
        Librarian librarian = LibrarianMapper.toEntity(librarianDto);
        Librarian createdLibrarian = librarianService.create(librarian);
        LibrarianDto createdLibrarianDto = LibrarianMapper.toDto(createdLibrarian);

        return ResponseEntity.ok(createdLibrarianDto);
    }

    @GetMapping("/{librarianId}")
    public ResponseEntity<LibrarianDto> getById(@PathVariable Long librarianId) {
        Librarian foundLibrarian = librarianService.getById(librarianId);
        return ResponseEntity.ok(LibrarianMapper.toDto(foundLibrarian));
    }

    @GetMapping
    public ResponseEntity<List<LibrarianDto>> findAll() {
        List<LibrarianDto> librarians = librarianService.findAll().stream()
                .map(LibrarianMapper::toDto)
                .toList();
        return ResponseEntity.ok(librarians);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibrarian(@RequestBody @Validated(ValidationOrder.class) LibrarianDto librarianDto,
                                             @PathVariable Long id) {
        Librarian librarianEntity = LibrarianMapper.toEntity(librarianDto);
        Librarian librarianUpdate = librarianService.update(librarianEntity, id);
        LibrarianDto updatedLibrarianDTO = LibrarianMapper.toDto(librarianUpdate);

        return ResponseEntity.ok(updatedLibrarianDTO);
    }

    @DeleteMapping("/{librarianId}")
    public ResponseEntity<?> deleteLibrarian(@PathVariable Long librarianId) {
        librarianService.delete(librarianId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam String email, @RequestParam String code) {
        librarianService.verify(email, code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated(LoginValidation.class) LibrarianDto librarianDto) {
        Librarian librarianToLogin = LibrarianMapper.toEntity(librarianDto);
        Librarian librarian = librarianService.login(librarianToLogin.getEmail(), librarianToLogin.getPassword());
        return ResponseEntity.ok(LibrarianMapper.toDto(librarian));
    }
}
