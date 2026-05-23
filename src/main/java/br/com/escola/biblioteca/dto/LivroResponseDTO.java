package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.model.LivroModel;;

public record LivroResponseDTO( Long id, String titulo, String isbn, Integer anoPublicacao, String genero, 
    Long autorId, String autorNome) {

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