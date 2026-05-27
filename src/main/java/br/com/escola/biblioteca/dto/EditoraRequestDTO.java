package br.com.escola.biblioteca.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record EditoraRequestDTO(
        @Schema(description = "Nome da editora", example = "Rocco")

        @NotBlank(message = "Nome não pode ser nulo") String nome,

        @Schema(description = "CNPJ da editora", example = "62.173.620/0001-80")

        @NotBlank(message = "CNPJ não pode ser nulo") String cnpj,

        @Schema(description = "Estado da editora", example = "RJ, SP, MG...")

        @NotBlank(message = "Estado não pode ser nulo") String estado) {
}