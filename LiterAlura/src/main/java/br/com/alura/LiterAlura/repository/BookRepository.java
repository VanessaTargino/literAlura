package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
        List<Book> findByLanguage(String language);
    }
