package gov.sp.fatec.bookblog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @JsonView(View.class)
    private final Long id;

    @Column(length = 16, unique = true)
    @NotNull
    @Getter
    @JsonView(View.class)
    private final String name;

    @ManyToMany(mappedBy = "categories")
    @Getter
    private final List<Book> books = Collections.emptyList();

    public Category() {
        id = null;
        name = null;
    }

    public Category(@JsonProperty("id") final Long id, @JsonProperty("name") final String name) {
        this.id = id;
        this.name = name;
    }

    public interface View {
    }
}
