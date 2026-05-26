package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.GeneroRequestDTO;
import br.com.escola.biblioteca.dto.GeneroResponseDTO;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.repository.GeneroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    // CREATE
    public GeneroResponseDTO criar(GeneroRequestDTO dto) {
        Genero genero = new Genero();
        genero.setNome(dto.nome());
        genero.setSigla(dto.sigla());

        Genero salvo = generoRepository.save(genero);
        return GeneroResponseDTO.fromEntity(salvo);
    }

    // GET ALL
    public List<GeneroResponseDTO> listarTodos() {
        return generoRepository.findAll()
                .stream()
                .map(GeneroResponseDTO::fromEntity)
                .toList();
    }

    // GET ONE
    public GeneroResponseDTO buscarPorId(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado com id: " + id));

        return GeneroResponseDTO.fromEntity(genero);
    }

    // UPDATE
    public GeneroResponseDTO atualizar(Long id, GeneroRequestDTO dto) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado com id: " + id));

        genero.setNome(dto.nome());
        genero.setSigla(dto.sigla());

        Genero atualizado = generoRepository.save(genero);
        return GeneroResponseDTO.fromEntity(atualizado);
    }

    // DELETE
    public void deletar(Long id) {
        if (!generoRepository.existsById(id)) {
            throw new RuntimeException("Gênero não encontrado com id: " + id);
        }
        generoRepository.deleteById(id);
    }

    public Genero buscarEntidadePorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado com id: " + id));
    }
}