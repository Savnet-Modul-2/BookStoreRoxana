package com.example.BookStore.mapper;

import com.example.BookStore.dto.LibraryDto;
import com.example.BookStore.entities.Book;
import com.example.BookStore.entities.Library;

import java.util.List;

public class LibraryMapper {
    public static Library toEntity(LibraryDto libraryDto) {
        if (libraryDto == null) {
            return null;
        }

        Library library = new Library();
        library.setId(libraryDto.getId());
        library.setName(libraryDto.getName());
        library.setAddress(libraryDto.getAddress());
        library.setPhoneNumber(libraryDto.getPhoneNumber());

        if (libraryDto.getBooks() != null) {
            List<Book> books = libraryDto.getBooks().stream()
                    .map(BookMapper::toEntity)
                    .toList();
            library.setBooks(books);
        }

        return library;
    }

    public static LibraryDto toDto(Library library) {
        if (library == null) {
            return null;
        }

        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(library.getId());
        libraryDto.setName(library.getName());
        libraryDto.setAddress(library.getAddress());
        libraryDto.setPhoneNumber(library.getPhoneNumber());

        if (library.getBooks() != null) {
            libraryDto.setBooks(library.getBooks().stream()
                    .map(BookMapper::toDto)
                    .toList());
        }

        return libraryDto;
    }
}
