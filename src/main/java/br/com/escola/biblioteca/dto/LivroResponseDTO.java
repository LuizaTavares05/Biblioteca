package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.entity.Livro;

import io.swagger.v3.oas.annotations.media.Schema;

public record LivroResponseDTO(

        @Schema(description = "ID do livro", example = "1")
        Long id,

        @Schema(description = "Título do livro", example = "Dom Casmurro")
        String titulo,

        @Schema(description = "Código ISBN do livro", example = "978-85-359-0277-5")
        String isbn,

        @Schema(description = "Ano de publicação do livro", example = "1899")
        Integer anoPublicacao,

        @Schema(description = "ID do autor", example = "1")
        Long autorId,

        @Schema(description = "Nome do autor", example = "Machado de Assis")
        String autorNome,

        @Schema(description = "ID do gênero", example = "1")
        Long generoId,

        @Schema(description = "Nome do gênero", example = "Romance")
        String generoNome,

        @Schema(description = "ID da editora", example = "1")
        Long editoraId,

        @Schema(description = "Nome da editora", example = "Companhia das Letras")
        String editoraNome

) {

    public static LivroResponseDTO fromEntity(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getAutor().getId(),
                livro.getAutor().getNome(),
                livro.getGenero().getId(),
                livro.getGenero().getNome(),
                livro.getEditora().getId(),
                livro.getEditora().getNome()
        );
    }
}