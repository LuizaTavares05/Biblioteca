package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public record AutorRequestDto(

		@Schema(description = "Nome do autor", example = "Machado de Assis")

		@NotBlank(message = "Nome não pode ser nulo") String nome,

		@Schema(description = "Nacionalidade do autor", example = "Brasileira")

		@NotBlank(message = "Nacionalidade não pode ser nula") String nacionalidade,

		@Schema(description = "Data de nascimento do autor", example = "1839-06-21")

		@NotNull(message = "Data de nascimento não pode ser nula") LocalDate dataNascimento

) {
}