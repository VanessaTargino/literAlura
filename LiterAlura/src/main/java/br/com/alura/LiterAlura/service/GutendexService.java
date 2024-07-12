package br.com.alura.LiterAlura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import br.com.alura.LiterAlura.model.Book;
import br.com.alura.LiterAlura.model.Author;
import br.com.alura.LiterAlura.repository.BookRepository;
import br.com.alura.LiterAlura.repository.AuthorRepository;

@Service
public class GutendexService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private static final String API_URL = "http://gutendex.com/books?title=";

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
        book.setTitle("Titulo Pesquisado");
        book.setLanguage("Idioma Pesquisado");
        book.setPublicationYear(2020);
        Author author = new Author();
        author.setName("Autor Pesquisado");
        book.setAuthor(author);
        return book;
    }
}

