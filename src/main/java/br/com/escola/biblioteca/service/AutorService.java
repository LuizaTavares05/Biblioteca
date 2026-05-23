package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.AutorRequestDto;
import br.com.escola.biblioteca.dto.AutorResponseDto;
import br.com.escola.biblioteca.model.AutorModel;
import br.com.escola.biblioteca.repository.AutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public AutorResponseDto criar(AutorRequestDto dto) {
        AutorModel autor = new AutorModel();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        AutorModel salvo = autorRepository.save(autor);
        return AutorResponseDto.fromEntity(salvo);
    }

    public List<AutorResponseDto> listarTodos() {
        return autorRepository.findAll()
                .stream()
                .map(AutorResponseDto::fromEntity)
                .toList();
    }

    public AutorResponseDto buscarPorId(Long id) {
        AutorModel autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com id: " + id));

        return AutorResponseDto.fromEntity(autor);
    }

    public AutorResponseDto atualizar(Long id, AutorRequestDto dto) {
        AutorModel autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com id: " + id));

        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        AutorModel atualizado = autorRepository.save(autor);
        return AutorResponseDto.fromEntity(atualizado);
    }

    public void deletar(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado com id: " + id);
        }
        autorRepository.deleteById(id);
    }

    public AutorModel buscarEntidadePorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com id: " + id));
    }
}