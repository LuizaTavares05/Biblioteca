package br.com.escola.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.escola.biblioteca.dto.GeneroResponseDTO;
import br.com.escola.biblioteca.dto.GeneroRequestDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/generos")

@Tag(name = "Generos", description = "Api responsavel pelo gerenciamento de gêneros"

)
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping("/cadastrar")

    @Operation(

            summary = "Cadastrar gênero", description = "Realizar o cadastro de um novo gênero")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gênero cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar gênero")

    })
    public ResponseEntity<GeneroResponseDTO> inserir(@RequestBody @Valid GeneroRequestDTO dto) {
        GeneroResponseDTO response = generoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar")

    @Operation(summary = "Listar gêneros", description = "Retorna todos os gêneros cadastrados")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")

    })
    public ResponseEntity<List<GeneroResponseDTO>> listar() {
        return ResponseEntity.ok(generoService.listarTodos());
    }

    @GetMapping("/buscarPorId/{id}")

    @Operation(summary = "Buscar gênero por ID", description = "Retorna um gênero específico pelo ID"

    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero encontrado"),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado")

    })

    public ResponseEntity<GeneroResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.buscarPorId(id));
    }

    @PutMapping("/alterarPorId/{id}")

    @Operation(summary = "Alterar gênero", description = "Atualizar os dados de um gênero")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gênero atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado")
    })

    public ResponseEntity<GeneroResponseDTO> editar(@PathVariable Long id,
            @RequestBody @Valid GeneroRequestDTO dto) {
        return ResponseEntity.ok(generoService.atualizar(id, dto));
    }

    @DeleteMapping("/deletarPorId/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        generoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
