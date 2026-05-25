package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.model.LivroModel;

import io.swagger.v3.oas.annotations.media.Schema;

public record LivroResponseDTO(

        @Schema(
            description = "ID do livro",
            example = "1"
        )
        Long id,

        @Schema(
            description = "Título do livro",
            example = "Dom Casmurro"
        )
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
            description = "ID do autor",
            example = "1"
        )
        Long autorId,

        @Schema(
            description = "Nome do autor",
            example = "Machado de Assis"
        )
        String autorNome

) {

    public static LivroResponseDTO fromEntity(LivroModel livro) {

        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getGenero(),
                livro.getAutor().getId(),
                livro.getAutor().getNome()
        );
    }
}