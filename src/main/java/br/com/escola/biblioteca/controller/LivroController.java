package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.dto.LivroRequestDto;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/livros")

@Tag(name = "livros", description = "API responsavel pelo gerenciamento de livros")

public class LivroController {

	@Autowired
	private LivroService livroService;

	@PostMapping("/cadastrar")

	@Operation(summary = "Cadastrar livro", description = "Realiza o cadastro de um novo livro"

	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro ao cadastrar livro")
	})

	public ResponseEntity<LivroResponseDTO> inserir(@RequestBody @Valid LivroRequestDto dto) {
		LivroResponseDTO response = livroService.criar(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/listar")

	@Operation(summary = "Listar livros", description = "Retorna todos os livros cadastrados")

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	})

	public ResponseEntity<List<LivroResponseDTO>> listar() {
		return ResponseEntity.ok(livroService.listarTodos());
	}

	@GetMapping("/buscarPorId/{id}")

	@Operation(summary = "Buscar livro por ID", description = "Retorna um livro específico pelo ID")

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Livro encontrado"),
			@ApiResponse(responseCode = "404", description = "Livro não encontrado")
	})
	public ResponseEntity<LivroResponseDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(livroService.buscarPorId(id));
	}

	@PutMapping("/alterarPorId/{id}")

	@Operation(summary = "Alterar livro", description = "Atualiza os dados de um livro")

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Livro não encontrado")
	})

	public ResponseEntity<LivroResponseDTO> editar(@PathVariable Long id,
			@RequestBody @Valid LivroRequestDto dto) {
		return ResponseEntity.ok(livroService.atualizar(id, dto));
	}

	@DeleteMapping("/deletarPorId/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		livroService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}