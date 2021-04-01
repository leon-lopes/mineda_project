package gov.sp.fatec.bookblog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import gov.sp.fatec.bookblog.model.Book;
import gov.sp.fatec.bookblog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("api/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    @GetMapping
    @JsonView(Book.View.class)
    public Iterable<Book> findAll(@RequestParam(required = false) Long owner, @RequestParam(required = false) Long category) {
        return bookService.findAllBy(owner, category);
    }

    @GetMapping("{id}")
    @JsonView(Book.View.class)
    public Book findById(@PathVariable("id") final Long id) {
        return bookService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @JsonView(Book.View.class)
    public Book create(@RequestBody final Book book) {
        return bookService.create(book);
    }

    @PutMapping
    @JsonView(Book.View.class)
    public Book update(@RequestBody final Book book) {
        return bookService.update(book).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") final Long id) {
        bookService.deleteById(id);
    }
}
