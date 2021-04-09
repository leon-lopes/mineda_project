package gov.sp.fatec.bookblog.service;

import gov.sp.fatec.bookblog.model.Book;
import gov.sp.fatec.bookblog.model.Category;
import gov.sp.fatec.bookblog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookService bookService;

    @PreAuthorize("permitAll()")
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @PreAuthorize("permitAll()")
    public Optional<Category> findById(final Long id) {
        return categoryRepository.findById(id);
    }

    @PreAuthorize("permitAll()")
    public Iterable<Category> findAllByIds(final Iterable<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Category create(final Category category) {
        return categoryRepository.save(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Category update(final Category category) {
        return categoryRepository.save(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteById(final Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            for (Book book : category.get().getBooks()) {
                book.getCategories().remove(category.get());

                bookService.update(book);
            }
            categoryRepository.deleteById(id);
        }
    }
}
