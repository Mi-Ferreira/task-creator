package taskcreator.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

public class APITests {

    @Test
    public void signinUser() {

        RestAssured.given()
                .body("{" +
                        "\"name\": \"Elison\"," +
                        "\"lastname\": \"Nascimento\"," +
                        "\"email\": \"elisonno@gmail.com\"," +
                        "\"password\": \"12345\"" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post("http://localhost:8080/taskcreator/auth/signin")
                .then()
                .statusCode(201);

    }

}
