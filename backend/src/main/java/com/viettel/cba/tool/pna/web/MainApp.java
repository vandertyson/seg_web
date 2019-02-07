/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.cba.tool.pna.web;

import com.viettel.cba.tool.pna.web.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class MainApp {

    private static final Logger logger = LogManager.getLogger(MainApp.class);
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
