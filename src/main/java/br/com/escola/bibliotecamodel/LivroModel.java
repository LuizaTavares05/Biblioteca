package br.com.escola.bibliotecamodel;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class LivroModel {
	
	@Id
	@Generated (value = { "" })
	private Long Id;
	@Column
	private String titulo;
	@Column
	private String isbn;
	@Column
	private String anoPublicacao;
	@Column
	private String genero;
	@Column
	private String autor;
	
	
	
	public LivroModel() {
		super();
	}


	public LivroModel(Long id, String titulo, String isbn, String anoPublicacao, String genero, String autor) {
		super();
		Id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.anoPublicacao = anoPublicacao;
		this.genero = genero;
		this.autor = autor;
	}


	public Long getId() {
		return Id;
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
