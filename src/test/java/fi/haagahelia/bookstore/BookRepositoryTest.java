package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
    private BookRepository repository;

    @Test
    public void findByIdShouldReturnBook() {
        List<Book> books = repository.findById(4);
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getId()).isEqualTo(4);
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("Alexandr Pushkin", "Ruslan and Ludmila", "1910990", "1820", new Category("Narrative poems"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
}
