package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Pet;
import com.projetojpa.services.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class PetController {
    
    private final PetService petService;
    
    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }
    //Query Method
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Pet>> buscarPetsPorNome(@PathVariable String nome) {
      List<Pet> pets = petService.buscarPetsPorNome(nome);
      return ResponseEntity.ok(pets);
    }
  //Query Method
    @GetMapping("/proprietario/{proprietario}")
    public ResponseEntity<List<Pet>> buscarPetsPorProprietario(@PathVariable String proprietario) {
      List<Pet> pets = petService.buscarPetsPorProprietario(proprietario);
      return ResponseEntity.ok(pets);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Pet pet = petService.getPetById(id);
        if (pet != null) {
            return ResponseEntity.ok(pet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/")
    public ResponseEntity<Pet> criarPet(@RequestBody @Valid Pet pet) {
        Pet criarPet = petService.salvarPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarPet);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody @Valid Pet pet) {
        Pet updatedPet = petService.updatePet(id, pet);
        if (updatedPet != null) {
            return ResponseEntity.ok(pet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable Long id) {
        boolean deleted = petService.deletePet(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
 }