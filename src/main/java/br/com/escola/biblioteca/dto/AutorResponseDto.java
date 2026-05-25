package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.entity.Autor;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record AutorResponseDto(

        @Schema(
            description = "ID do autor",
            example = "1"
        )
        Long id,

        @Schema(
            description = "Nome do autor",
            example = "Machado de Assis"
        )
        String nome,

        @Schema(
            description = "Nacionalidade do autor",
            example = "Brasileira"
        )
        String nacionalidade,

        @Schema(
            description = "Data de nascimento do autor",
            example = "1839-06-21"
        )
        LocalDate dataNascimento

) {

    public static AutorResponseDto fromEntity(Autor autor) {

        return new AutorResponseDto(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataNascimento()
        );
    }
}