package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.entity.Editora;

import io.swagger.v3.oas.annotations.media.Schema;

public record EditoraResponseDTO(
        @Schema(description = "ID da editora", example = "1") Long id,

        @Schema(description = "Nome da editora", example = "Rocco") String nome,

        @Schema(description = "CNPJ da editora", example = "62.173.620/0001-80") String cnpj,

        @Schema(description = "Estado da editora", example = "RJ, SP, MG...") String estado) {

    public static EditoraResponseDTO fromEntity(Editora editora) {
        return new EditoraResponseDTO(
                editora.getId(),
                editora.getNome(),
                editora.getCnpj(),
                editora.getEstado());
    }

}
