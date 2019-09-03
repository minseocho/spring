package springbootgi.springbootai.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootgi.springbootai.RestTestController;
import springbootgi.springbootai.model.Book;
import springbootgi.springbootai.model.BookResultMessage;
import springbootgi.springbootai.service.BookService;

@RestController
@RequestMapping("/book")
//@Api(tags = { "책 API" })
public class BookController {

	@Autowired private BookService bookService;

	// http://localhost:8081/
	private static final Logger log = LoggerFactory.getLogger(RestTestController.class);

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/read/{bookCd}", method= RequestMethod.GET)
	public ResponseEntity<BookResultMessage> read(@PathVariable String bookCd) {
		log.info(">>>>>>>>Start read bookCd : {}", bookCd);

		List<Book> bookList = bookService.read(bookCd);

		BookResultMessage<List<Book>> message = new BookResultMessage<List<Book>>();
		message.setData(bookList);

		return new ResponseEntity<BookResultMessage>(message, HttpStatus.OK);
	}

}
