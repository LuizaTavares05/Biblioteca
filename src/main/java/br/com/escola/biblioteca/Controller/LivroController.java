package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.dto.LivroRequestDto;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.service.LivroService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@PostMapping
	public ResponseEntity<LivroResponseDTO> inserir(@RequestBody @Valid LivroRequestDto dto) {
		LivroResponseDTO response = livroService.criar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<LivroResponseDTO>> listar() {
		return ResponseEntity.ok(livroService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroResponseDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(livroService.buscarPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<LivroResponseDTO> editar(@PathVariable Long id,
			@RequestBody @Valid LivroRequestDto dto) {
		return ResponseEntity.ok(livroService.atualizar(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		livroService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}