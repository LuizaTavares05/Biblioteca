package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.EditoraRequestDTO;
import br.com.escola.biblioteca.dto.EditoraResponseDTO;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.repository.EditoraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    // CREATE
    public EditoraResponseDTO criar(EditoraRequestDTO dto) {
        Editora editora = new Editora();
        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());
        editora.setEstado(dto.estado());

        Editora salva = editoraRepository.save(editora);
        return EditoraResponseDto.fromEntity(salva);
    }

    // GET ALL
    public List<EditoraResponseDTO> listarTodos() {
        return editoraRepository.findAll()
                .stream()
                .map(EditoraResponseDTO::fromEntity)
                .toList();
    }

    // GET ONE
    public EditoraResponseDTO buscarPorId(Long id) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada com id: " + id));

        return EditoraResponseDTO.fromEntity(editora);
    }

    // UPDATE
    public EditoraResponseDTO atualizar(Long id, EditoraRequestDTO dto) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada com id: " + id));

        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());
        editora.setEstado(dto.estado());

        Editora atualizada = editoraRepository.save(editora);
        return EditoraResponseDTO.fromEntity(atualizada);
    }

    // DELETE
    public void deletar(Long id) {
        if (!editoraRepository.existsById(id)) {
            throw new RuntimeException("Editora não encontrada com id: " + id);
        }
        editoraRepository.deleteById(id);
    }

    public Editora buscarEntidadePorId(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada com id: " + id));
    }
}