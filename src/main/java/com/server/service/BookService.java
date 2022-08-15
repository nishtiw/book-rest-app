package com.server.service;

import com.server.entity.Book;
import com.server.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    //    get all books
    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll(); //typecasting this because findAll() returns iterable.
        return list;
    }
    public Book getBookById(int id) {
        Book book = null;
        try {
//            book = list.stream().filter(e->e.getId()==id).findFirst().get();
            book = this.bookRepository.findById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // after matching id, if it is true then it will return first element and since it is stored in optional, we use get() method.
        return book;
    }
    public Book addBook(Book b) {
        Book result = bookRepository.save(b);
        return result;
    }
    public ResponseEntity<Void> deleteBook(int bId) {

        try {
            bookRepository.deleteById(bId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public void updateBook(Book book, int id) {
        book.setId(id);
        bookRepository.save(book);
    }

//    Pagination
    public Page<Book> getBooksByPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return bookRepository.findAll(pageable);
    }

//    Sorting books in ascending order and retrieving them page wise
    public Page<Book> getBooksInSortedOrderByPage(Integer pageNumber, Integer pageSize, String sortProperty) {

        Sort sort = Sort.by(Sort.Direction.ASC, sortProperty);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return bookRepository.findAll(pageable);
    }
}



/*
public class BookService {
//    fake service which interacts with DAO layer
    private static List<Book> list = new ArrayList<>();
    static {
        list.add(new Book(12,"Java Complete Reference","ABC"));
        list.add(new Book(36,"Head First to Java","LMN"));
        list.add(new Book(1784,"Core Java Volume II","Horstmann"));
    }

//    get all books
    public List<Book> getAllBooks() {
        return list;
    }

//    get book by id
    public Book getBookById(int id) {
        Book book = null;
        try {
            book = list.stream().filter(e->e.getId()==id).findFirst().get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // after matching id, if it is true then it will return first element and since it is stored in optional, we use get() method.
        return book;
    }

//    add a single book
    public Book addBook(Book b) {
        list.add(b);
        return b;
    }

//    delete book service
    public ResponseEntity<Void> deleteBook(int bId) {
//        list.remove(bId);
        for (int i = 0; i < list.size(); i++) {
//            Book b = list.get(i);
            if(list.get(i).getId() == bId) {
                list.remove(i);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        list = list.stream().filter(book-> {
//            if(book.getId() != bId)
//                return true;
//            else
//                return false;
//        }).collect(Collectors.toList());

//        simplification of above code
//        list = list.stream().filter(book-> book.getId() != bId? true:false).collect(Collectors.toList());

//        more simplification of above code
//        list = list.stream().filter(book-> book.getId() != bId).collect(Collectors.toList());
    }

//    update book service
    public void updateBook(Book book, int id) {
        list = list.stream().map(b -> {
            if(b.getId() == id)
            {
                b.setTitle(book.getTitle());
                b.setAuthor((book.getAuthor()));
            }
            return b;
        }).collect(Collectors.toList());
    }
}
*/
