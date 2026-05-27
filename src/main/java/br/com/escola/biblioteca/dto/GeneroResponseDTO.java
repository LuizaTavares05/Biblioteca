package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.entity.Genero;

import io.swagger.v3.oas.annotations.media.Schema;

public record GeneroResponseDTO(
        @Schema(description = "ID de gênero", example = "1") Long id,

        @Schema(description = "Gênero", example = "Romance") String nome,

        @Schema(description = "Sigla do gênero", example = "Roamnce = ROM") String sigla) {
    public static GeneroResponseDTO fromEntity(Genero genero) {
        return new GeneroResponseDTO(
                genero.getId(),
                genero.getNome(),
                genero.getSigla());
    }
}
