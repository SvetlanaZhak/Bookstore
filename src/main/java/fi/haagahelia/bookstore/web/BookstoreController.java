package fi.haagahelia.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;


@Controller
public class BookstoreController {
	
	@Autowired
	private BookRepository repository;
	
	@RequestMapping("/booklist") 
	public String booklist(Model model) {
	
	model.addAttribute("booklist", repository.findAll());
	return "booklist";
}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Book bookId, Model model){
	repository.delete(bookId);
	return "redirect:../booklist";
}

	@RequestMapping(value = "/add")
	public String addbook(Model model){
		model.addAttribute("book", new Book());
		return "addbook";
}
	
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

	
