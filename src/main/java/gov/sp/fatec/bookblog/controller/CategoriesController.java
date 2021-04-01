package gov.sp.fatec.bookblog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import gov.sp.fatec.bookblog.model.Category;
import gov.sp.fatec.bookblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @JsonView(Category.View.class)
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Category.View.class)
    public Category findById(@PathVariable("id") final Long id) {
        return categoryService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @JsonView(Category.View.class)
    public Category create(@RequestBody final Category category) {
        return categoryService.create(category);
    }

    @PutMapping
    @JsonView(Category.View.class)
    public Category update(@RequestBody final Category category) {
        return categoryService.update(category);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") final Long id) {
        categoryService.deleteById(id);
    }
}
