package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDto(

        @NotBlank(message = "Título não pode ser vazio")
        String titulo,

        String isbn,

        Integer anoPublicacao,

        String genero,

        @NotNull(message = "Autor é obrigatório")
        Long autorId

) {}