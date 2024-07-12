package br.com.alura.LiterAlura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    @Entity
    public class Author {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private Integer publicationYear;

        public Integer getPublicationYear() {
            return publicationYear;
        }

        public void setPublicationYear(Integer publicationYear) {
            this.publicationYear = publicationYear;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

