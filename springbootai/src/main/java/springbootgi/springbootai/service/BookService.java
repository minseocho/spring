package springbootgi.springbootai.service;

import springbootgi.springbootai.model.Book;
import java.util.List;

public interface BookService {

	public List<Book> read(String bookCd);

}
