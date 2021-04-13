package utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import pojos.requestPOJOs.NewBook;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIUtils {

    public static String getToken(String username, String password){

        return given()
                .contentType(ContentType.URLENC)
                .formParam("email", username)
                .formParam("password" , password).
                        when()
                .post("/login")
                .path("token");

    }


    public static Map<String,Object> getRandomBook(){

        Faker faker = new Faker();

        Map<String,Object> myBookMap = new HashMap<>();
        myBookMap.put("name", faker.book().title()      );
        myBookMap.put("isbn", faker.number().digits(8) );
        myBookMap.put("year", faker.number().numberBetween(1600, 2021));
        myBookMap.put("author",faker.book().author() );
        myBookMap.put("book_category_id", faker.number().numberBetween(1,20)  );
        myBookMap.put("description",faker.chuckNorris().fact()  );

        return myBookMap ;
    }
public static  NewBook getRandomBookPOJO(){
    Faker faker = new Faker();
    NewBook newBook = new NewBook();
    newBook.setName(faker.book().title());
    newBook.setIsbn(faker.number().digits(8));
    newBook.setYear(faker.number().numberBetween(1600, 2021));
    newBook.setAuthor(faker.book().author());
    newBook.setBook_category_id(faker.number().numberBetween(1,20));
    newBook.setDescription(faker.chuckNorris().fact());
    return newBook;

}

}
