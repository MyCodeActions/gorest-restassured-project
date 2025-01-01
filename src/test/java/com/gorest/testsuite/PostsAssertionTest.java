package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class PostsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2/posts";

        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", 25)
                .get()
                .then().statusCode(200);
    }

    // 1.  Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size()", equalTo(25));
    }


    //2. Verify the if the title of id = 184660 is equal to "Suffragium spes comitatus omnis vesper centum beneficium approbo voluptas."
    @Test
    public void test002() {
        response.body("findAll{it.id == 184660 }.title[0]", equalTo("Suffragium spes comitatus omnis vesper centum beneficium approbo voluptas."));
    }


    //3. Check the single user_id in the Array list (7609171)
    @Test
    public void test003() {
        response.body("user_id", hasItem(7609171)).log();
    }


    //4. Check the multiple ids in the ArrayList (184650, 184651, 184652)
    @Test
    public void test004() {
        response.body("id", hasItems(184650, 184651, 184652));
    }


    //5. Verify the body of userid = 7609153 is equal “Nemo vindico suus. Venustas vel demum. Tot atqui sono. Aqua denuncio animadverto. Arx desparatus ut. Triumphus brevis optio. Solium vicissitudo conspergo. Thalassinus adicio adfectus. Quasi campana degero. Amoveo bene stella. Attero color ustilo. Eius altus brevis. Atrox ater absum. Bis et cupiditate.”
    @Test
    public void test005() {
        response.body("findAll{it.user_id == 7609153}.body", equalTo(Collections.singletonList("Nemo vindico suus. Venustas vel demum. Tot atqui sono. Aqua denuncio animadverto. Arx desparatus ut. Triumphus brevis optio. Solium vicissitudo conspergo. Thalassinus adicio adfectus. Quasi campana degero. Amoveo bene stella. Attero color ustilo. Eius altus brevis. Atrox ater absum. Bis et cupiditate.")));
    }
}
