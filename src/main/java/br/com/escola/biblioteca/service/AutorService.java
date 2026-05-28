package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.AutorRequestDto;
import br.com.escola.biblioteca.dto.AutorResponseDto;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.exception.BusinessException;
import br.com.escola.biblioteca.exception.NotFoundException;
import br.com.escola.biblioteca.repository.AutorRepository;
import br.com.escola.biblioteca.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    public AutorResponseDto criar(AutorRequestDto dto) {
        Autor autor = new Autor();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        Autor salvo = autorRepository.save(autor);
        return AutorResponseDto.fromEntity(salvo);
    }

    public List<AutorResponseDto> listarTodos() {
        return autorRepository.findAll()
                .stream()
                .map(AutorResponseDto::fromEntity)
                .toList();
    }

    public AutorResponseDto buscarPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado com id: " + id));

        return AutorResponseDto.fromEntity(autor);
    }

    public AutorResponseDto atualizar(Long id, AutorRequestDto dto) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado com id: " + id));

        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        Autor atualizado = autorRepository.save(autor);
        return AutorResponseDto.fromEntity(atualizado);
    }

    public void deletar(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new NotFoundException("Autor não encontrado com id: " + id);
        }

        if (!livroRepository.findByAutorId(id).isEmpty()) {
            throw new BusinessException("Não é possível excluir autor com livros cadastrados. Delete os livros primeiro.");
        }

        autorRepository.deleteById(id);
    }

    // Método auxiliar — usado pelo LivroService
    public Autor buscarEntidadePorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Autor não encontrado com id: " + id));
    }
}