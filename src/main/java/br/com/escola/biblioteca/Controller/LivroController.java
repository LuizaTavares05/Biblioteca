package br.com.escola.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroController {
	/*
	private static List<Livro> lista_livro = new ArrayList<Livro>();
	
	@PostMapping("/inserir")
	@ResponseStatus(HttpStatus.CREATED)
	public Livro inserir(@RequestBody Livro livro) {
		return repositorio.save(livro);
	}
	
	@GetMapping
	public List<Livro> lista(){
		//return repositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscar(@PathVariable Long id){
		Optional<Livro> livro = repositorio.findById(id);
		if(livro.isPresent()) {
			return ResponseEntity.ok(livro.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("editar/{id}")
	public ResponseEntity<Veiculo> editar(@RequestBody Livro livro, @PathVariable Long id){
		if(!repositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		livro.setId(id);
		livro = repositorio.save(livro);
		return ResponseEntity.ok(livro);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity delete(@PathVariable Long id) {
		if(!repositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	*/
}
