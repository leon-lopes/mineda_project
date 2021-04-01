package gov.sp.fatec.bookblog.service;

import gov.sp.fatec.bookblog.model.Book;
import gov.sp.fatec.bookblog.model.Category;
import gov.sp.fatec.bookblog.model.User;
import gov.sp.fatec.bookblog.repository.BookRepository;
import gov.sp.fatec.bookblog.security.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookOwnerCheck bookOwnerCheck;

    @PreAuthorize("permitAll()")
    public Iterable<Book> findAllBy(final Long ownerId, final Long categoryId) {
        if (ownerId != null && categoryId != null) {
            return bookRepository.findAllByOwnerIdAndCategoryId(ownerId, categoryId);
        }

        if (ownerId != null) {
            return bookRepository.findAllByOwnerId(ownerId);
        }

        if (categoryId != null) {
            return bookRepository.findAllByCategoryId(categoryId);
        }

        return bookRepository.findAll();
    }

    @PreAuthorize("permitAll()")
    public Optional<Book> findById(final Long id) {
        return bookRepository.findById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    public Book create(final Book book) {
        final User user = AuthUtil.getAuthenticatedUser();

        book.setOwner(user);
        book.setCategories((List<Category>) categoryService.findAllByIds(book.getCategories().stream().map(Category::getId).collect(Collectors.toList())));

        return bookRepository.save(book);
    }

    @PreAuthorize("hasRole('ADMIN') || @bookOwnerCheck.isBookOwner(#book, authentication)")
    @Transactional
    public Optional<Book> update(final Book book) {
        Optional<Book> existingBook = findById(book.getId());

        if (!existingBook.isPresent()) {
            return Optional.empty();
        }

        book.setOwner((existingBook.get().getOwner()));
        book.setCategories((List<Category>) categoryService.findAllByIds(book.getCategories().stream().map(Category::getId).collect(Collectors.toList())));

        return Optional.of(bookRepository.save(book));

    }

    @PreAuthorize("hasRole('ADMIN') || @bookOwnerCheck.isBookOwner(#id, authentication)")
    public void deleteById(final Long id) {
        bookRepository.deleteById(id);
    }
}
