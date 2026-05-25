package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDto(
		
		 @Schema(
		            description = "Título do livro",
		            example = "Dom Casmurro"
		        )

		        @NotBlank(message = "Título não pode ser vazio")
		        String titulo,

		        @Schema(
		            description = "Código ISBN do livro",
		            example = "978-85-359-0277-5"
		        )

		        String isbn,

		        @Schema(
		            description = "Ano de publicação do livro",
		            example = "1899"
		        )

		        Integer anoPublicacao,

		        @Schema(
		            description = "Gênero literário do livro",
		            example = "Romance"
		        )

		        String genero,

		        @Schema(
		            description = "ID do autor do livro",
		            example = "1"
		        )

		        @NotNull(message = "Autor é obrigatório")
		        Long autorId


) {}