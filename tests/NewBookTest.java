package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.requestPOJOs.NewBook;
import utilities.DBUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static utilities.APIUtils.getRandomBook;
import static utilities.APIUtils.getRandomBookPOJO;

public class NewBookTest extends LibraryTestBase {
    @DisplayName("Add new book, validate from database")
    @Test
    public void addNewBook() {
        Map<String, Object> newBook = getRandomBook();
        int book_id = given().log().all()
                .header("x-library-token", librarianToken)
                .contentType(ContentType.URLENC)
                .formParams(newBook)
                .when()
                .post("/add_book")
                .prettyPeek()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getInt("book_id");
        String query = "select * from books where id = " + book_id;
        DBUtils.runQuery(query);
        Map<String, String> newBookInfo = DBUtils.getRowMap(1);
        System.out.println("newBookInfo = " + newBookInfo);
        assertThat(newBookInfo.get("name"),is(newBook.get("name")));
        assertThat(newBookInfo.get("author"),is(newBook.get("author")));
        assertThat(newBookInfo.get("isbn"),is(newBook.get("isbn")));
        //assertThat(actualData, is(expectedData));



    }
}