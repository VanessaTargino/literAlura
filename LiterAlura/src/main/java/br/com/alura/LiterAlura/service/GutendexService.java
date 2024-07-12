package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.model.Author;
import br.com.alura.LiterAlura.model.Book;
import br.com.alura.LiterAlura.repository.AuthorRepository;
import br.com.alura.LiterAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class GutendexService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private static final String API_URL = "http://gutendex.com/books?search=";

    public Book fetchBookByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL + title, String.class);
        Book book = parseBookResponse(response.getBody());
        Author author = authorRepository.save(book.getAuthor());
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    private Book parseBookResponse(String responseBody) {
        // Implementação da lógica de parsing
        Book book = new Book();
        book.setTitle("Parsed Title");
        book.setLanguage("Parsed Language");
        book.setPublicationYear(2020);
        Author author = new Author();
        author.setName("Parsed Author");
        book.setAuthor(author);
        return book;
    }
}
