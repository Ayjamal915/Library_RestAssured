package pojos.requestPOJOs;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class NewBook {
    private String name;
    private String isbn;
    private int year;
    private String author;
    private int book_category_id;
    private String description;



}
