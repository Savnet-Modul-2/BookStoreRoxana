package com.example.Library.repository;

import com.example.Library.entities.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    Optional<Librarian> findByEmail(String email);

    Optional<Librarian> findByEmailAndPasswordAndVerifiedAccountTrue(String email, String password);

    Optional<Librarian> findByLibraryId(Long libraryId);
}
