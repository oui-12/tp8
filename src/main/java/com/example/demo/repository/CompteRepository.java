package com.example.demo.repository;

import com.example.demo.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    // Les méthodes de base (findAll, findById, save, deleteById, etc.) sont déjà fournies par JpaRepository
}
