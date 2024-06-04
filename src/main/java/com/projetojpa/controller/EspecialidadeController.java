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

import com.projetojpa.entities.Especialidade; 
import com.projetojpa.services.EspecialidadeService; 

import jakarta.validation.Valid; 


@RestController 
@RequestMapping("/especialidade") 
public class EspecialidadeController { 
	private final EspecialidadeService especialidadeService; 
	@Autowired 
	public EspecialidadeController(EspecialidadeService especialidadeService) { 
		this.especialidadeService = especialidadeService; 
	} 
	@GetMapping("/{id}") 
	public ResponseEntity<Especialidade> getEspecialidadeById(@PathVariable Long id) { 
		Especialidade especialidade = especialidadeService.getEspecialidadeById(id); 
		if (especialidade != null) { 
			return ResponseEntity.ok(especialidade); 
		} else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
	@GetMapping("/") 
	public ResponseEntity<List<Especialidade>> getAllEspecialidades() { 
		List<Especialidade> especialidades = especialidadeService.getAllEspecialidades(); 
		return ResponseEntity.ok(especialidades); 
	} 
	@PostMapping("/") 
	public ResponseEntity<Especialidade> criarEspecialidade(@RequestBody @Valid Especialidade especialidade) { 
		Especialidade criarEspecialidade = especialidadeService.salvarEspecialidade(especialidade); 
		return ResponseEntity.status(HttpStatus.CREATED).body(criarEspecialidade); 
	} 
	@PutMapping("/{id}") 
	public ResponseEntity<Especialidade> updateEspecialidade(@PathVariable Long id, @RequestBody @Valid Especialidade especialidade) { 
		Especialidade updatedEspecialidade = especialidadeService.updateEspecialidade(id, especialidade); 
		if (updatedEspecialidade != null) { 
			return ResponseEntity.ok(updatedEspecialidade); 
		} else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
	@DeleteMapping("/{id}") 
	public ResponseEntity<Especialidade> deleteEspecialidade(@PathVariable Long id) { 
		boolean deleted = especialidadeService.deleteEspecialidade(id); 
		if (deleted) { 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  
		} else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
} 

