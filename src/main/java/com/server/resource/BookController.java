package com.server.resource;

import com.server.entity.Book;
import com.server.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

//    Get request for all books Handler
    @GetMapping("/books")
    @ApiOperation("Returns a list of all the books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = this.bookService.getAllBooks();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
//    Get request for single book Handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        if(book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

//    Post request for single book Handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            System.out.println(b);
            return ResponseEntity.of(Optional.of(b));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    Delete request Handler
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
        return this.bookService.deleteBook(bookId);
    }

//    update book handler
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int id) {
        try{
            this.bookService.updateBook(book, id);
            return ResponseEntity.ok().body(book);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    Pagination endpoint
    @GetMapping("/books/{pageNumber}/{pageSize}")
    public Page<Book> getBooksByPage(@PathVariable Integer pageNumber, @PathVariable Integer pageSize) {
        return bookService.getBooksByPage(pageNumber, pageSize);
    }

//    Sorting endpoint based
    @GetMapping("/books/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<Book> getBooksInSortedOrder(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String sortProperty) {
        return bookService.getBooksInSortedOrderByPage(pageNumber, pageSize, sortProperty);
    }
}
