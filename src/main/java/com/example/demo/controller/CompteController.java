package com.example.demo.controller;

import com.example.demo.model.Compte;
import com.example.demo.model.TypeCompte;
import com.example.demo.repository.CompteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/banque")
@Tag(name = "Compte", description = "API de gestion des comptes bancaires")
public class CompteController {
    
    @Autowired
    private CompteRepository compteRepository;
    
    @Operation(summary = "Récupérer tous les comptes", description = "Retourne la liste de tous les comptes bancaires")
    @ApiResponse(responseCode = "200", description = "Liste des comptes trouvée",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE + "," + MediaType.APPLICATION_XML_VALUE,
                         schema = @Schema(implementation = Compte.class)))
    @GetMapping(value = "/comptes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }
    
    @Operation(summary = "Récupérer un compte par son ID", description = "Retourne un compte bancaire spécifique par son identifiant")
    @ApiResponse(responseCode = "200", description = "Compte trouvé")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @GetMapping(value = "/comptes/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
        return compteRepository.findById(id)
        .map(compte -> ResponseEntity.ok().body(compte))
        .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Créer un nouveau compte", description = "Crée un nouveau compte bancaire")
    @ApiResponse(responseCode = "201", description = "Compte créé avec succès")
    @PostMapping(value = "/comptes", 
                consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte savedCompte = compteRepository.save(compte);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCompte.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedCompte);
    }
    
    @Operation(summary = "Mettre à jour un compte", description = "Met à jour un compte bancaire existant")
    @ApiResponse(responseCode = "200", description = "Compte mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @PutMapping(value = "/comptes/{id}",
               consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
               produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compteDetails) {
        return compteRepository.findById(id)
        .map(compte -> {
            compte.setSolde(compteDetails.getSolde());
            compte.setDateCreation(compteDetails.getDateCreation());
            compte.setType(compteDetails.getType());
            Compte updatedCompte = compteRepository.save(compte);
            return ResponseEntity.ok().body(updatedCompte);
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Supprimer un compte", description = "Supprime un compte bancaire par son identifiant")
    @ApiResponse(responseCode = "200", description = "Compte supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Compte non trouvé")
    @DeleteMapping("/comptes/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        return compteRepository.findById(id)
        .map(compte -> {
            compteRepository.delete(compte);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
