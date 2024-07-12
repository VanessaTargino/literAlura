package br.com.alura.LiterAlura;

import br.com.alura.LiterAlura.model.Author;
import br.com.alura.LiterAlura.model.Book;
import br.com.alura.LiterAlura.repository.AuthorRepository;
import br.com.alura.LiterAlura.repository.BookRepository;
import br.com.alura.LiterAlura.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private GutendexService gutendexService;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Menu:");
			System.out.println("1 - Buscar livro pelo título");
			System.out.println("2 - Listar livros digitados");
			System.out.println("3 - Listar autores registrados");
			System.out.println("4 - Listar autores vivos em um determinado ano");
			System.out.println("5 - Listar livros em um determinado idioma");
			System.out.println("6 - Sair");
			System.out.print("Escolha uma opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					System.out.print("Digite o título do livro: ");
					String title = scanner.nextLine();
					Book book = gutendexService.fetchBookByTitle(title);
					System.out.println("Livro adicionado: " + book.getTitle());
					break;
				case 2:
					List<Book> books = bookRepository.findAll();
					books.forEach(b -> System.out.println(b.getTitle()));
					break;
				case 3:
					List<Author> authors = authorRepository.findAll();
					authors.forEach(a -> System.out.println(a.getName()));
					break;
				case 4:
					System.out.print("Digite o ano: ");
					int year = scanner.nextInt();
					List<Author> aliveAuthors = authorRepository.findByIsAliveAndBirthYearLessThan(true, year);
					aliveAuthors.forEach(a -> System.out.println(a.getName()));
					break;
				case 5:
					System.out.print("Digite o idioma: ");
					String language = scanner.nextLine();
					List<Book> booksByLanguage = bookRepository.findByLanguage(language);
					booksByLanguage.forEach(b -> System.out.println(b.getTitle()));
					break;
				case 6:
					System.out.println("Saindo...");
					scanner.close();
					return;
				default:
					System.out.println();
			}
		}
	}
}
