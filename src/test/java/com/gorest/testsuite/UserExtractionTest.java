package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2/users";

        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get()
                .then().statusCode(200);
    }
    // 1. Extract all IDs
    @Test
    public void test001() {
        List<Integer> allIds = response.extract().path("id");
        System.out.println("Name of all Ids are : " + allIds);
        System.out.println("------------------------------------------------------------------");

    }
    // 2. Extract all Names
    @Test
    public void test002() {
        List<String> allNames = response.extract().path("name");
        System.out.println("Name of all the Names are : " + allNames);
        System.out.println("------------------------------------------------------------------");
    }
    // 3. Extract the name of the 5th object
    @Test
    public void test003() {
        String fifthName = response.extract().path("name[4]");
        System.out.println("Name of 5th Object: " + fifthName);
        System.out.println("------------------------------------------------------------------");
    }


    // 4. Extract the names of all objects whose status = inactive
    @Test
    public void test004() {
        List<String> inactiveNames = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("Names with Inactive Status: " + inactiveNames);
        System.out.println("------------------------------------------------------------------");
    }

    // 5. Extract the gender of all objects whose status = active
    @Test
    public void test005() {
        List<String> activeGenders = response.extract().path("findAll {it.status == 'active'}.gender");
        System.out.println("Genders with Active Status: " + activeGenders);
        System.out.println("------------------------------------------------------------------");
    }


    // 6. Print the names of objects whose gender = female
    @Test
    public void test006() {
        List<String> femaleNames = response.extract().path("findAll {it.gender == 'female'}.name");
        System.out.println("Names with Female Gender: " + femaleNames);
        System.out.println("------------------------------------------------------------------");
    }


    // 7. Get all emails of objects where status = inactive
    @Test
    public void test007() {
        List<String> inactiveEmails = response.extract().path("findAll {it.status == 'inactive'}.email");
        System.out.println("Emails with Inactive Status: " + inactiveEmails);
        System.out.println("------------------------------------------------------------------");
    }


    // 8. Get the IDs of objects where gender = male
    @Test
    public void test008() {
        List<Integer> maleIds = response.extract().path("findAll {it.gender == 'male'}.id");
        System.out.println("IDs with Male Gender: " + maleIds);
        System.out.println("------------------------------------------------------------------");
    }


    // 9. Get all statuses
    @Test
    public void test009() {
        List<String> allStatuses = response.extract().path("status");
        System.out.println("All Statuses: " + allStatuses);
        System.out.println("------------------------------------------------------------------");
    }

    // 10. Get email of the object where name = "Aagney Sinha"
    @Test
    public void test010() {
        String email = response.extract().path("find {it.name == 'Aagney Sinha'}.email");
        System.out.println("Email of 'Aagney Sinha': " + email);
        System.out.println("------------------------------------------------------------------");
    }

    // 11. Get gender of id = 7609129
    @Test
    public void test011() {
        String gender = response.extract().path("find {it.id == 7609129}.gender");
        System.out.println("Gender of ID 7609129: " + gender);
        System.out.println("------------------------------------------------------------------");
    }
}
