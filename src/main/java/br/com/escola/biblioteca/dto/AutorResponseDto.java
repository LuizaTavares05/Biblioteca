package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

import br.com.escola.biblioteca.entity.Autor;

public record AutorResponseDto(Long id, String nome, String nacionalidade, LocalDate dataNascimento) {

    public static AutorResponseDto fromEntity(Autor autor) {
        return new AutorResponseDto(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade(),
                autor.getDataNascimento()
        );
    }
}