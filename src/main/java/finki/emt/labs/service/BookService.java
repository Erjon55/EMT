package finki.emt.labs.service;

import finki.emt.labs.model.Book;
import finki.emt.labs.model.dto.BookDto;
import finki.emt.labs.model.enums.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> save (BookDto bookDto);
    Optional<Book> findById(Long id);
    Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> update(Long id, BookDto bookDto);
    void deleteById(Long id);
    Optional<Book> markAsTaken(Long id);
}
