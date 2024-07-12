package br.com.alura.LiterAlura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.alura.LiterAlura.service.GutendexService;
import br.com.alura.LiterAlura.model.Book;
import br.com.alura.LiterAlura.model.Author;
import br.com.alura.LiterAlura.repository.BookRepository;
import br.com.alura.LiterAlura.repository.AuthorRepository;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/search")
    public Book searchBookByTitle(@RequestParam String title) {
        return gutendexService.fetchBookByTitle(title);
    }

    @GetMapping("/list")
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/authors")
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/authors/year")
    public List<Author> listAuthorsByYear(@RequestParam Integer year) {
        return authorRepository.findAuthorsByBookPublicationYear(year);
    }

    @GetMapping("/list/language")
    public List<Book> listBooksByLanguage(@RequestParam String language) {
        return bookRepository.findByLanguage(language);
    }
}
