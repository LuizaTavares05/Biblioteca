package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record GeneroRequestDTO(
        @Schema(description = "Gênero", example = "Romance")

        @NotBlank(message = "Gênero não pode ser nulo") String nome,

        @Schema(description = "Sigla do gênero", example = "Roamnce = ROM")

        @NotBlank(message = "Sigla não pode ser nula") String sigla) {
}