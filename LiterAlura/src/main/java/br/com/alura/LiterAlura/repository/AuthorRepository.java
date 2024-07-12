package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT DISTINCT a FROM Author a JOIN a.books b WHERE b.publicationYear = :year")
    List<Author> findAuthorsByBookPublicationYear(@Param("year") Integer year);
}