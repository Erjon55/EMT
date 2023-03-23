package finki.emt.labs.service.impl;

import finki.emt.labs.model.Author;
import finki.emt.labs.model.Book;
import finki.emt.labs.model.dto.BookDto;
import finki.emt.labs.model.enums.Category;
import finki.emt.labs.model.exceptions.AuthorNotFoundException;
import finki.emt.labs.model.exceptions.BookNotFoundException;
import finki.emt.labs.model.exceptions.InvalidArgumentsException;
import finki.emt.labs.repository.BookRepository;
import finki.emt.labs.service.AuthorService;
import finki.emt.labs.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        if(name.equals(""))
            throw new InvalidArgumentsException();
        Optional<Author> author = this.authorService.findById(authorId);
        if(author.isEmpty())
            throw new AuthorNotFoundException(authorId);
        Author a = author.get();
        Book book = new Book(name, category, a, availableCopies);
        return Optional.of(this.bookRepository.save(book));

    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        if(bookDto.getName().equals(""))
            throw new InvalidArgumentsException();
        Optional<Author> author = this.authorService.findById(bookDto.getAuthorId());
        if(author.isEmpty())
            throw new AuthorNotFoundException(bookDto.getAuthorId());

        Author a = author.get();
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), a, bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Optional<Author> author = this.authorService.findById(authorId);
        if(author.isEmpty())
            throw new AuthorNotFoundException(authorId);
        Author a = author.get();

        Optional<Book> book = this.findById(id);
        if(book.isEmpty())
            throw new BookNotFoundException(id);

        Book b = book.get();
        b.setName(name);
        b.setCategory(category);
        b.setAuthor(a);
        b.setAvailableCopies(availableCopies);
        return Optional.of((this.bookRepository.save(b)));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Optional<Author> author = this.authorService.findById(bookDto.getAuthorId());
        if(author.isEmpty())
            throw new AuthorNotFoundException(bookDto.getAuthorId());
        Author a = author.get();

        Optional<Book> book = this.findById(id);
        if(book.isEmpty())
            throw new BookNotFoundException(id);

        Book b = book.get();
        b.setName(bookDto.getName());
        b.setCategory(bookDto.getCategory());
        b.setAuthor(a);
        b.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(b));
    }

    @Override
    public void deleteById(Long id) {
        if(this.findById(id).isEmpty())
            throw new BookNotFoundException(id);
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Optional<Book> book = this.findById(id);
        if(book.isEmpty())
            throw new BookNotFoundException(id);
        Book b = book.get();
        if(b.getAvailableCopies()>0){
            b.setAvailableCopies(b.getAvailableCopies()-1);
            return Optional.of(this.bookRepository.save(b));
        }
        return book;
    }
}
