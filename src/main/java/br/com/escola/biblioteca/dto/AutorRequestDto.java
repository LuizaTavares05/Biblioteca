package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AutorRequestDto(

        @NotBlank(message = "Nome não pode ser nulo")
        String nome,

        @NotBlank(message = "Nacionalidade não pode ser nula")
        String nacionalidade,

        @NotNull(message = "Data de nascimento não pode ser nula")
        LocalDate dataNascimento

) {}