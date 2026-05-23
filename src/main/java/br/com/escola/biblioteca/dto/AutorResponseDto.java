package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.model.AutorModel;

import java.time.LocalDate;

public record AutorResponseDto(Long id, String nome, String nacionalidade, LocalDate dataNascimento) {

    public static AutorResponseDto fromEntity(AutorModel autor) {
        return new AutorResponseDto(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataNascimento()
        );
    }
}