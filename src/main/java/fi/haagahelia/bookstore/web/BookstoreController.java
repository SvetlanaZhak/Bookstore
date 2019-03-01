package fi.haagahelia.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;


@Controller
public class BookstoreController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository drepository;
	
	// Show all students
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	// Show all books
	
	@RequestMapping(value="/booklist") 
	public String booklist(Model model) {
	
	model.addAttribute("booklist", repository.findAll());
	return "booklist";
}
	

	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> studentListRest() {	
        return (List<Book>) repository.findAll();
    }    

	// RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    } 
	
	//Delete book
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model){
	repository.deleteById(bookId);
	return "redirect:../booklist";
}
	
	//Add new book
	@PreAuthorize("hasAuthority('ADMIN')")
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
	
	// Edit new book
	
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model){
	model.addAttribute("book", repository.findById(id));
	return "editbook";
	}
	@RequestMapping(value={"/", "/home"})
	public String homeSecure() {
		return "home";
	}  
    
    @RequestMapping(value="/hello")
	public String helloSecure() {
		return "hello";
	}
    
   
}

	
