package tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utilities.ConfigurationReader;
import utilities.DBUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.reset;
import static utilities.APIUtils.getToken;

public class LibraryTestBase {

    public static String librarianToken;

    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.get("base_uri");
        basePath = ConfigurationReader.get("base_path");
        String username = ConfigurationReader.get("librarian_username");
        String password = ConfigurationReader.get("librarian_password");
        librarianToken = getToken(username, password);
        DBUtils.createConnection();
    }
    @AfterAll
    public static void cleanup() {
        reset();
        DBUtils.destroy();
    }
}