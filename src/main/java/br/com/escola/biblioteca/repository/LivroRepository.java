package br.com.escola.biblioteca.repository;

import br.com.escola.biblioteca.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long> {
    List<LivroModel> findByAutorId(Long autorId);
}
