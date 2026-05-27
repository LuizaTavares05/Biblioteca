package br.com.escola.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequestDto(

		@Schema(description = "Título do livro", example = "Dom Casmurro") @NotBlank(message = "Título não pode ser vazio") String titulo,

		@Schema(description = "Código ISBN do livro", example = "978-85-359-0277-5") String isbn,

		@Schema(description = "Ano de publicação do livro", example = "1899") Integer anoPublicacao,

		@Schema(description = "ID do autor do livro", example = "1") @NotNull(message = "Autor é obrigatório") Long autorId,

		@Schema(description = "ID do gênero do livro", example = "1") @NotNull(message = "Gênero é obrigatório") Long generoId,

		@Schema(description = "ID da editora do livro", example = "1") @NotNull(message = "Editora é obrigatória") Long editoraId

) {
}