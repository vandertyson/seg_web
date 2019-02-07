/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.cba.tool.pna.web.controller;

import com.viettel.cba.tool.pna.web.MainApp;
import com.viettel.cba.tool.pna.web.model.Role;
import com.viettel.cba.tool.pna.web.model.User;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.Arrays;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.springframework.boot.context.embedded.LocalServerPort;
import com.google.gson.Gson;
import com.viettel.cba.tool.pna.web.security.JwtTokenProvider;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = MainApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private JwtTokenProvider jwtProvider;

    @Before
    public void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void signup() {
        User sonpt = new User();
        int empCode = (int) (Math.random() * 100);
        String usrname = "sonpt" + empCode;
        sonpt.setUsername(usrname);
        sonpt.setPassword("12345678");
        sonpt.setEmail(usrname + "@viettel.com.vn");
        sonpt.setRoles(new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
        Gson gson = new Gson();
        String toJson = gson.toJson(sonpt);
        String token = given()
                .accept(ContentType.ANY)
                .contentType(ContentType.JSON)
                .body(toJson)
                .when()
                .post("/users/signup")
                .then()
                .statusCode(is(HttpStatus.SC_OK))
                .contentType(ContentType.TEXT)
                .extract()
                .body().asString();
        Authentication authentication = jwtProvider.getAuthentication(token);
        assertThat(authentication.getName(), is(sonpt.getUsername()));        
    }

}
