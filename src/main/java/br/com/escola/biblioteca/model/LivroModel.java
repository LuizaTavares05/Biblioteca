package br.com.escola.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table (name = "Livro")
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*Banco gera ID sozinho */
    @Column (name = "ID")
    private Long id;

    @Column(name = "titulo", nullable = false) /*Regras Obrigatorias - Não permitir cadastro de livro com titulo vazio */
    @NotBlank (message = "Preencher o titulo do livro")
    private String titulo;

    @Column (name = "Isbn")
    private String isbn;

    @Column (name = "data_ publicação")
    private String anoPublicacao;

    @Column (name = "genero")
    private String genero;

    @Column(name = "autor", nullable = false)/*Regras Obrigatorias - Não permitir cadastro de livro sem autor*/
    @NotBlank (message = "Preencher o nome do autor")
    private String autor;

    public LivroModel() {
        super();
    }

    public LivroModel(Long id, String titulo, String isbn, String anoPublicacao, String genero, String autor) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}