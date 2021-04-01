package gov.sp.fatec.bookblog.service;

import gov.sp.fatec.bookblog.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookOwnerCheck {
    @Autowired
    private BookService bookService;

    public boolean isBookOwner(Long bookId, Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return false;
        }

        Optional<Book> bookById = bookService.findById(bookId);
        if (!bookById.isPresent()) {
            return false;
        }

        return authentication.getName().equals(bookById.get().getOwner().getUsername());
    }

    public boolean isBookOwner(Book book, Authentication authentication) {
        return this.isBookOwner(book.getId(), authentication);
    }
}