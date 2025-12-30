package com.example.tp8;

import com.example.demo.model.Compte;
import com.example.demo.model.TypeCompte;
import com.example.demo.repository.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
@EntityScan(basePackages = "com.example.demo.model")
public class Tp8Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp8Application.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            // Création de comptes de test
            compteRepository.save(new Compte(null, 1000.0, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 5000.0, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, 2500.0, new Date(), TypeCompte.COURANT));
            
            // Affichage des comptes créés dans la console
            System.out.println("=== Comptes initialisés ===");
            compteRepository.findAll().forEach(System.out::println);
            System.out.println("==========================");
        };
    }
}
