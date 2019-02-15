package fi.haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;


@Controller
public class BookstoreController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository drepository;
	
	// Show all books
	
	@RequestMapping(value="/booklist") 
	public String booklist(Model model) {
	
	model.addAttribute("booklist", repository.findAll());
	return "booklist";
}
	//Delete book
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model){
	repository.deleteById(bookId);
	return "redirect:../booklist";
}
	
	//Add book

	@RequestMapping(value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("category", drepository.findAll());
		return "addbook";
}
	
	//Save new book 
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook (Book Book) {
	repository.save(Book);
	return "redirect:booklist";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model){
	model.addAttribute("book", repository.findById(id));
	return "editbook";
	}
}

	
