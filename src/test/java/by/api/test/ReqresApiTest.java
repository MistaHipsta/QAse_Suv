package by.api.test;

import by.api.rest.dto.RegisterPage;
import by.api.rest.dto.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqresApiTest {

    @Test
    public void checkListUserAndUserFromList() {
        //get users list
        int userId = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getInt("data[0].id");
        assertThat(given()).isNotNull().as("Response is null");

        //get user from list
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("userID", userId)
                .when()
                .get("https://reqres.in/api/users/{userID}")
                .then()
                .log().all()
                .statusCode(200);
        assertThat(given()).isNotNull().as("Response is null");

        //get user with incorrect ID
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("userID", 23)
                .when()
                .get("https://reqres.in/api/users/{userID}")
                .then()
                .log().all()
                .statusCode(404);
        assertThat(given()).isNotNull().as("Response is null");
    }

    @Test
    public void checkListResourceAndResourceFromList() {
        //get resource list
        int resourceId = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getInt("data[0].id");
        assertThat(given()).isNotNull().as("Response is null");

        //get resource from list
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("resourceID", resourceId)
                .when()
                .get("https://reqres.in/api/unknown/{resourceID}")
                .then()
                .log().all()
                .statusCode(200);
        assertThat(given()).isNotNull().as("Response is null");

        //get resource with incorrect ID
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("resourceID", 23)
                .when()
                .get("https://reqres.in/api/unknown/{resourceID}")
                .then()
                .log().all()
                .statusCode(404);
        assertThat(given()).isNotNull().as("Response is null");
    }

    @Test
    public void createUser() {
        //create user
        User validUser = User.builder().build()
                .setName("TestUserSuv")
                .setJob("TestJobQA");
        String userId = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(validUser)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().body().jsonPath().getString("id");
        assertThat(given()).isNotNull().as("Response is null");

        //GET list users from my ID is not work
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                pathParams("userId", userId).
                when()
                .get("https://reqres.in/api/users/{userId}")
                .then()
                .log().all()
                .statusCode(200);
        assertThat(given()).isNotNull().as("Response is null");
    }

    @Test
    public void updateUser() {
        //get user from list for update
        int userId = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getInt("data[0].id");
        assertThat(given()).isNotNull().as("Response is null");

        //update user via user obj
        User validUser = User.builder().build()
                .setName("TestUserSuvNew")
                .setJob("TestJobQaNew");
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                pathParams("userId", userId).
                body(validUser).
                when()
                .put("https://reqres.in/api/users/{userId}")
                .then()
                .log().all()
                .statusCode(200);
        assertThat(given()).isNotNull().as("Response is null");

        //update user via path method
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                pathParams("userId", userId).
                body(validUser).
                when()
                .patch("https://reqres.in/api/users/{userId}")
                .then()
                .log().all()
                .statusCode(200);
        assertThat(given()).isNotNull().as("Response is null");
    }

    @Test
    public void deleteUser() {
        //delete user from list
        int userId = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getInt("data[0].id");
        assertThat(given()).isNotNull().as("Response is null");

        //delete user
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                pathParams("userId", userId).
                when()
                .delete("https://reqres.in/api/users/{userId}")
                .then()
                .log().all()
                .statusCode(204);
        assertThat(given()).isNotNull().as("Response is null");
    }

    @Test
    public void registerSuccessfulTest() {
        //register post
        RegisterPage registerPage = RegisterPage.builder().build()
                .setEmail("eve.holt@reqres.in")
                .setPassword("pistol");
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(registerPage)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(200);

        assertThat(given()).isNotNull().as("Response is null");

        //Register - unsuccessful
        RegisterPage registerPageNotValid = RegisterPage.builder().build()
                .setEmail("sydney@fife")
                .setPassword("");
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(registerPageNotValid)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(400);
        assertThat(given()).isNotNull().as("Response is null");
    }

    @Test
    public void loginSuccessfulTest() {
        //login post
        RegisterPage loginPage = RegisterPage.builder().build()
                .setEmail("eve.holt@reqres.in")
                .setPassword("cityslicka");
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginPage)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200);

        assertThat(given()).isNotNull().as("Response is null");

        RegisterPage loginPage2 = RegisterPage.builder().build()
                .setEmail("peter@klaven")
                .setPassword("");
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(loginPage2)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(400);

        assertThat(given()).isNotNull().as("Response is null");
    }
}
