package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.config.MailConfig;
import br.com.escola.biblioteca.dto.LivroRequestDto;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.exception.NotFoundException;
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

    @Autowired
    private GeneroService generoService;

    @Autowired
    private EditoraService editoraService;

    @Autowired
    private MailConfig mailConfig;

    public LivroResponseDTO criar(LivroRequestDto dto) {
        Autor autor = autorService.buscarEntidadePorId(dto.autorId());
        Genero genero = generoService.buscarEntidadePorId(dto.generoId());
        Editora editora = editoraService.buscarEntidadePorId(dto.editoraId());

        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setAutor(autor);
        livro.setGenero(genero);
        livro.setEditora(editora);

        Livro salvo = livroRepository.save(livro);

        // Envia e-mail de cadastro
        mailConfig.sendEmail(
                "admin@biblioteca.com",
                "Novo livro cadastrado",
                "Título: " + salvo.getTitulo() +
                        "\nAutor: " + salvo.getAutor().getNome() +
                        "\nGênero: " + salvo.getGenero().getNome() +
                        "\nEditora: " + salvo.getEditora().getNome());

        return LivroResponseDTO.fromEntity(salvo);
    }

    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado com id: " + id));

        return LivroResponseDTO.fromEntity(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDto dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado com id: " + id));

        Autor autor = autorService.buscarEntidadePorId(dto.autorId());
        Genero genero = generoService.buscarEntidadePorId(dto.generoId());
        Editora editora = editoraService.buscarEntidadePorId(dto.editoraId());

        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setAutor(autor);
        livro.setGenero(genero);
        livro.setEditora(editora);

        Livro atualizado = livroRepository.save(livro);

        // Envia e-mail de alteração
        mailConfig.sendEmail(
                "admin@biblioteca.com",
                "Livro atualizado",
                "Título: " + atualizado.getTitulo() +
                        "\nAutor: " + atualizado.getAutor().getNome() +
                        "\nGênero: " + atualizado.getGenero().getNome() +
                        "\nEditora: " + atualizado.getEditora().getNome());

        return LivroResponseDTO.fromEntity(atualizado);
    }

    public void deletar(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado com id: " + id));

        livroRepository.deleteById(id);

        // Envia e-mail de exclusão
        mailConfig.sendEmail(
                "admin@biblioteca.com",
                "Livro excluído",
                "Título: " + livro.getTitulo() +
                        "\nAutor: " + livro.getAutor().getNome() +
                        "\nGênero: " + livro.getGenero().getNome() +
                        "\nEditora: " + livro.getEditora().getNome());
    }
}