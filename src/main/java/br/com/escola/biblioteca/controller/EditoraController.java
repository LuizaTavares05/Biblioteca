package br.com.escola.biblioteca.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.dto.EditoraResponseDTO;
import br.com.escola.biblioteca.service.EditoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/editoras")
@Tag(
        name = "Editoras",
        description = "API responsavel pelo gerenciamento de editoras"
)
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @PostMapping("/cadastrar")

    @Operation(
            summary = "Cadastrar editora",
            description = "Realiza o cadastro de uma nova editora"
    )

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Editora cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar editora")
    })

    public ResponseEntity<EditoraResponseDto> inserir(@RequestBody @Valid EditoraRequestDto dto) {
        EditoraResponseDto response = editoraService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

        @GetMapping("/listar")
    
        @Operation(
                summary = "Listar editoras",
                description = "Retorna todas as editoras cadastradas"
        )
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
        })
        public ResponseEntity<List<EditoraResponseDTO>> listar() {
            return ResponseEntity.ok(editoraService.listarTodos());

        }
        @GetMapping("/buscarPorId/{id}")

        @Operation(
                summary = "Buscar editora por ID",
                description = "Retorna uma editora específica pelo ID"
        )

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Editora encontrada"),
                @ApiResponse(responseCode = "404", description = "Editora não encontrada")
        })

        public ResponseEntity<EditoraResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(editoraService.buscarPorId(id));
        }


        @PutMapping("/alterarPorId/{id}")
            @Operation(
                summary = "Atualizar editora",
                description = "Atualização de uma editora existente"
        )
        

         @ApiResponses(value = {
                 @ApiResponse(responseCode = "200", description = "Editora atualizada com sucesso"),
                 @ApiResponse(responseCode = "404", description = "Editora não encontrada")
         })

        public ResponseEntity<EditoraResponseDto> editar(@PathVariable Long id, 
            @RequestBody @Valid EditoraRequestDto dto) {
             return ResponseEntity.ok(editoraService.atualizar(id, dto));
            }
        
            @DeleteMapping("/deletarPorId/{id}")
            public ResponseEntity<Void> deletar(@PathVariable Long id) {
                editoraService.deletarPorId(id);
                return ResponseEntity.noContent().build();
            }


    
}

