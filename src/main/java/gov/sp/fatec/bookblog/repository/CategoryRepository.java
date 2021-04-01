package gov.sp.fatec.bookblog.repository;

import gov.sp.fatec.bookblog.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
