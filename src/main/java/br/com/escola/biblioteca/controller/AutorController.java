package br.com.escola.biblioteca.Controller;

import br.com.escola.biblioteca.dto.AutorRequestDto;

import br.com.escola.biblioteca.dto.AutorResponseDto;
import br.com.escola.biblioteca.service.AutorService;
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
@RequestMapping("/autores")

@Tag(
	    name = "Autores",
	    description = "API responsável pelo gerenciamento de autores"
	)

public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/cadastrar")
    
    @Operation(
            summary = "Cadastrar autor",
            description = "Realiza o cadastro de um novo autor"
        )

        @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar autor")
        })
    
    
    public ResponseEntity<AutorResponseDto> inserir(@RequestBody @Valid AutorRequestDto dto) {
        AutorResponseDto response = autorService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar")
    
    @Operation(
            summary = "Listar autores",
            description = "Retorna todos os autores cadastrados"
        )

        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
        })
    
    public ResponseEntity<List<AutorResponseDto>> listar() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @GetMapping("buscarPorId/{id}")
    
    @Operation(
            summary = "Buscar autor por ID",
            description = "Retorna um autor específico pelo ID"
        )

        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
        })

    
    
    
    public ResponseEntity<AutorResponseDto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(autorService.buscarPorId(id));
    }

    @PutMapping("alterarPorId/{id}")
    
    @Operation(
            summary = "Alterar autor",
            description = "Atualiza os dados de um autor"
        )

        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
        })
    
    
    
    public ResponseEntity<AutorResponseDto> editar(@PathVariable Long id,
            @RequestBody @Valid AutorRequestDto dto) {
        return ResponseEntity.ok(autorService.atualizar(id, dto));
    }

    @DeleteMapping("deletarPorId/{id}")
    
    @Operation(
            summary = "Deletar autor",
            description = "Remove um autor pelo ID"
        )

        @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
        })
    
    
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}