package fi.haagahelia.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository drepository) {
		return (args) -> {
			log.info("save a couple of books");
			drepository.save(new Category("Novel"));
			drepository.save(new Category("Fiction"));
			drepository.save(new Category("Drama"));
			
			repository.save(new Book("Ernest Hemingway", "A Farewell to Arms", "1232332-21", "1929", drepository.findByName("Novel").get(0)));
			repository.save(new Book("George Orwell ", "Animal Farm", "2212343-5", "1945",  drepository.findByName("Fiction").get(0)));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
