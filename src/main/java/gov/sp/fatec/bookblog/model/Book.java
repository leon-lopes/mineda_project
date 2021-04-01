package gov.sp.fatec.bookblog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @JsonView({View.class, View2.class})
    private final Long id = null;

    @NotNull
    @Getter
    @JsonView(View.class)
    private final String title;

    @NotNull
    @Getter
    @JsonView(View.class)
    private final String author;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NotNull
    @Getter
    @Setter
    @JsonView(View.class)
    private User owner = null;

    @ManyToMany
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Getter
    @Setter
    @JsonView({View.class, View2.class})
    private List<Category> categories = Collections.emptyList();

    public Book() {
        title = null;
        author = null;
    }

    public Book(@JsonProperty("title") final String title, @JsonProperty("author") final String author, @JsonProperty("categories") final List<Category> categories) {
        this.title = title;
        this.author = author;

        if (categories != null) {
            this.categories = categories;
        }
    }

    public interface View extends Category.View, User.View {
    }

    public interface View2 extends  Category.View{}
}
