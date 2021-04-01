package gov.sp.fatec.bookblog.repository;

import gov.sp.fatec.bookblog.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.owner o WHERE o.id = :ownerId")
    List<Book> findAllByOwnerId(@Param("ownerId") final Long ownerId);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :categoryId")
    List<Book> findAllByCategoryId(@Param("categoryId") final Long categoryId);

    @Query("SELECT b FROM Book b JOIN b.owner o JOIN b.categories c WHERE o.id = :ownerId AND c.id = :categoryId")
    List<Book> findAllByOwnerIdAndCategoryId(@Param("ownerId") final Long ownerId, @Param("categoryId") final Long categoryId);
}
