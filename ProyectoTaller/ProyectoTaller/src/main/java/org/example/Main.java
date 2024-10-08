package org.example;

import org.example.Class.FirebaseInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication(scanBasePackages = "org.example")

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void init() {
        try {
            FirebaseInitializer.initializeFirebase();
        } catch (IOException e) {
            System.err.println("Error al inicializar Firebase: " + e.getMessage());
        }
    }
}