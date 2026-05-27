package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.EditoraRequestDTO;
import br.com.escola.biblioteca.dto.EditoraResponseDTO;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.exception.NotFoundException;
import br.com.escola.biblioteca.repository.EditoraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public EditoraResponseDTO criar(EditoraRequestDTO dto) {
        validarCnpj(dto.cnpj());
        validarEstado(dto.estado());

        // Verifica CNPJ duplicado
        if (editoraRepository.existsByCnpj(dto.cnpj())) {
            throw new RuntimeException("Já existe uma editora cadastrada com o CNPJ: " + dto.cnpj());
        }

        Editora editora = new Editora();
        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());
        editora.setEstado(dto.estado().toUpperCase());

        Editora salva = editoraRepository.save(editora);
        return EditoraResponseDTO.fromEntity(salva);
    }

    public List<EditoraResponseDTO> listarTodos() {
        return editoraRepository.findAll()
                .stream()
                .map(EditoraResponseDTO::fromEntity)
                .toList();
    }

    public EditoraResponseDTO buscarPorId(Long id) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Editora não encontrada com id: " + id));

        return EditoraResponseDTO.fromEntity(editora);
    }

    public EditoraResponseDTO atualizar(Long id, EditoraRequestDTO dto) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Editora não encontrada com id: " + id));

        validarCnpj(dto.cnpj());
        validarEstado(dto.estado());

        // Verifica CNPJ duplicado ignorando a própria editora
        if (editoraRepository.existsByCnpjAndIdNot(dto.cnpj(), id)) {
            throw new RuntimeException("Já existe outra editora cadastrada com o CNPJ: " + dto.cnpj());
        }

        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());
        editora.setEstado(dto.estado().toUpperCase());

        Editora atualizada = editoraRepository.save(editora);
        return EditoraResponseDTO.fromEntity(atualizada);
    }

    public void deletar(Long id) {
        if (!editoraRepository.existsById(id)) {
            throw new NotFoundException("Editora não encontrada com id: " + id);
        }
        editoraRepository.deleteById(id);
    }

    public Editora buscarEntidadePorId(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Editora não encontrada com id: " + id));
    }

    private void validarCnpj(String cnpj) {
        if (cnpj == null || cnpj.isBlank()) {
            throw new RuntimeException("CNPJ não pode ser vazio.");
        }
        String cnpjNumeros = cnpj.replaceAll("[.\\-/]", "");
        if (cnpjNumeros.length() != 14) {
            throw new RuntimeException("CNPJ inválido. Deve conter 14 dígitos. Exemplo: 12.345.678/0001-90.");
        }
        if (!cnpjNumeros.matches("\\d+")) {
            throw new RuntimeException("CNPJ deve conter apenas números.");
        }
    }

    private void validarEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            throw new RuntimeException("Estado não pode ser vazio.");
        }
        if (estado.length() != 2) {
            throw new RuntimeException("Estado deve ser a sigla com 2 letras. Exemplos: RJ, SP, RS, MG.");
        }
    }
}