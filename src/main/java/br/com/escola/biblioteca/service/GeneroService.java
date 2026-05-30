package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.GeneroRequestDTO;
import br.com.escola.biblioteca.dto.GeneroResponseDTO;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.exception.BusinessException;
import br.com.escola.biblioteca.exception.NotFoundException;
import br.com.escola.biblioteca.repository.GeneroRepository;
import br.com.escola.biblioteca.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public GeneroResponseDTO criar(GeneroRequestDTO dto) {
        validarSigla(dto.sigla());

        Genero genero = new Genero();
        genero.setNome(dto.nome());
        genero.setSigla(dto.sigla().toUpperCase());

        Genero salvo = generoRepository.save(genero);
        return GeneroResponseDTO.fromEntity(salvo);
    }

    public List<GeneroResponseDTO> listarTodos() {
        return generoRepository.findAll()
                .stream()
                .map(GeneroResponseDTO::fromEntity)
                .toList();
    }

    public GeneroResponseDTO buscarPorId(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gênero não encontrado com id: " + id));

        return GeneroResponseDTO.fromEntity(genero);
    }

    public GeneroResponseDTO atualizar(Long id, GeneroRequestDTO dto) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gênero não encontrado com id: " + id));

        validarSigla(dto.sigla());

        genero.setNome(dto.nome());
        genero.setSigla(dto.sigla().toUpperCase());

        Genero atualizado = generoRepository.save(genero);
        return GeneroResponseDTO.fromEntity(atualizado);
    }

    public void deletar(Long id) {
        if (!generoRepository.existsById(id)) {
            throw new NotFoundException("Gênero não encontrado com id: " + id);
        }

        if (!livroRepository.findByGeneroId(id).isEmpty()) {
            throw new BusinessException(
                    "Não é possível excluir gênero com livros cadastrados. Delete os livros primeiro.");
        }

        generoRepository.deleteById(id);
    }

    public Genero buscarEntidadePorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gênero não encontrado com id: " + id));
    }

    private void validarSigla(String sigla) {
        if (sigla == null || sigla.isBlank()) {
            throw new BusinessException("Sigla não pode ser vazia.");
        }
        if (sigla.length() < 2 || sigla.length() > 5) {
            throw new BusinessException("Sigla deve ter entre 2 e 5 caracteres. Exemplo: ROM, DRM.");
        }
        if (!sigla.matches("[a-zA-Z]+")) {
            throw new BusinessException("Sigla deve conter apenas letras.");
        }
    }
}