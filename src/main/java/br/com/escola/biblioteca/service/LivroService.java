package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.LivroRequestDto;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.model.AutorModel;
import br.com.escola.biblioteca.model.LivroModel;
import br.com.escola.biblioteca.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorService autorService;


    public LivroResponseDTO criar(LivroRequestDto dto) {

        AutorModel autor = autorService.buscarEntidadePorId(dto.autorId());

        LivroModel livro = new LivroModel();
        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setGenero(dto.genero());
        livro.setAutor(autor);

        LivroModel salvo = livroRepository.save(livro);
        return LivroResponseDTO.fromEntity(salvo);
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {
        LivroModel livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));

        return LivroResponseDTO.fromEntity(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDto dto) {
        LivroModel livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));

        AutorModel autor = autorService.buscarEntidadePorId(dto.autorId());

        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setGenero(dto.genero());
        livro.setAutor(autor);

        LivroModel atualizado = livroRepository.save(livro);
        return LivroResponseDTO.fromEntity(atualizado);
    }

    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com id: " + id);
        }
        livroRepository.deleteById(id);
    }
}