package springbootgi.springbootai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import springbootgi.springbootai.model.Book;

@Service
public class BookServiceImpl implements BookService {

	public List<Book> read(String bookCd){

		List<Book> bookList = new ArrayList<Book>();

		if("12".equals(bookCd)){

			Book book = new Book();
			book.setBookCd(bookCd);
			book.setSubject("제목");
			book.setContent("내용");
			book.setWriter("저자");

			bookList.add(book);

		}



		return bookList;
	}

}
