package br.com.escola.biblioteca.dto;

import br.com.escola.biblioteca.entity.Livro;;

public record LivroResponseDTO( Long id, String titulo, String isbn, Integer anoPublicacao, String genero, 
    Long autorId, String autorNome) {

    public static LivroResponseDTO fromEntity(Livro livro) {
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