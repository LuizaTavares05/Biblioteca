package br.com.escola.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.entity.Editora;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutorId(Long autorId);
    List<Genero> findByGeneroId(Long id);

    List<Editora> findByEditoraId(Long id);
}
