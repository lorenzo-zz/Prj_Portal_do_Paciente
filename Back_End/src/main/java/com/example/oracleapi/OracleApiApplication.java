package com.example.oracleapi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class OracleApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OracleApiApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }


}
