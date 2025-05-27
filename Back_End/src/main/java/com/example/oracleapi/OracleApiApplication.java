package com.example.oracleapi;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OracleApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OracleApiApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }
<<<<<<< HEAD
=======

>>>>>>> 569ff7bac1ba1cf60419652be69a14302c298b65
}
