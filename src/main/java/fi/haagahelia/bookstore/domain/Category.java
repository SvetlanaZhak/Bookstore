package fi.haagahelia.bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> booklist;
	
	public Category() {}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public Long getCategoryid() {
		return categoryid;
	}
	
	public void setCategoryid(Long Categoryid) {
		this.categoryid = Categoryid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}

	@Override
	public String toString() {
		return "Category [Categoryid=" + categoryid + ", name=" + name + "]";
	}
}