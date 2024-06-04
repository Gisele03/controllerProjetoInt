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
import com.projetojpa.entities.Tipo; 
import com.projetojpa.services.TipoService; 
import jakarta.validation.Valid; 



@RestController 
@RequestMapping("/tipo") 
public class TipoController { 
	private final TipoService tipoService; 
	@Autowired 
	public TipoController(TipoService tipoService) { 
		this.tipoService = tipoService; 
	} 
	@GetMapping("/{id}") 
	public ResponseEntity<Tipo> getTipoById(@PathVariable Long id) { 
		Tipo tipo = tipoService.getTipoById(id); 
		if (tipo != null) { 
			return ResponseEntity.ok(tipo); 
		} else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
	@GetMapping("/") 
	public ResponseEntity<List<Tipo>> getAllTipos() { 
		List<Tipo> tipos = tipoService.getAllTipos(); 
		return ResponseEntity.ok(tipos); 
	} 
	@PostMapping("/") 
	public ResponseEntity<Tipo> criarTipo(@RequestBody @Valid Tipo tipo) { 
		Tipo criarTipo = tipoService.salvarTipo(tipo); 
		return ResponseEntity.status(HttpStatus.CREATED).body(criarTipo); 
	} 
	@PutMapping("/{id}") 
	public ResponseEntity<Tipo> updateTipo(@PathVariable Long id, @RequestBody @Valid Tipo tipo) { 
		Tipo updatedTipo = tipoService.updateTipo(id, tipo); 
		if (updatedTipo != null) { 
			return ResponseEntity.ok(updatedTipo); 
		} else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 
	@DeleteMapping("/{id}") 
	public ResponseEntity<Tipo> deleteTipo(@PathVariable Long id) { 
		boolean deleted = tipoService.deleteTipo(id); 
		if (deleted) { 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  
		} else { 
			return ResponseEntity.notFound().build(); 

		} 
	} 
} 

