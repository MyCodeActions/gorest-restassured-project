package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class UserCRUDTest extends TestBase {
    static String token = "4c9a7e0792e0e7d33962f732f47cce30d0133079d2bb012b9830286dec0a9223";
    static String name = "Prashant" + TestUtils.getRandomValue();
    static String email = "ppatel" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";


    @Test
    public void verifyUserCreatedSuccessfully() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given().log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(userPojo)
                .when()
                .post("")
                .then()
                .statusCode(201)
                .log().all()
                .extract().response();

        // Assertions
        response.then().body("name", equalTo(name));
        response.then().body("email", equalTo(email));
        response.then().body("gender", equalTo(gender));
        response.then().body("status", equalTo(status));

    }

    @Test
    public void readUserById(){

        int userId = 7612591;
        Response response = given().log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when()
                .get("/" + userId)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        // Assertions (Verify user details - update these as per the user data)
        response.then().body("id", equalTo(userId));
        response.then().body("name", equalTo("Tenalie Ramakrishna")); // Replace with expected name
        response.then().body("email", equalTo("tenali.ramakrishna@357ce.com")); // Replace with expected email
        response.then().body("gender", equalTo("male")); // Replace with expected gender
        response.then().body("status", equalTo("active")); // Replace with expected status

    }

    @Test
    public void deleteUserById() {
        int userId = 7612591;  // Example user ID, replace with actual user ID

        Response response = given().log().all()
                .header("Authorization", "Bearer " + token)
                .delete("/" + userId);

        response.then().statusCode(204);  // Assert successful deletion (HTTP 204)
    }
}