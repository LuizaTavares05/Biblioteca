package br.com.escola.biblioteca.Controller;

import br.com.escola.biblioteca.dto.AutorRequestDto;
import br.com.escola.biblioteca.dto.AutorResponseDto;
import br.com.escola.biblioteca.service.AutorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/cadastrar")
    public ResponseEntity<AutorResponseDto> inserir(@RequestBody @Valid AutorRequestDto dto) {
        AutorResponseDto response = autorService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AutorResponseDto>> listar() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<AutorResponseDto> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(autorService.buscarPorId(id));
    }

    @PutMapping("alterarPorId/{id}")
    public ResponseEntity<AutorResponseDto> editar(@PathVariable Long id,
            @RequestBody @Valid AutorRequestDto dto) {
        return ResponseEntity.ok(autorService.atualizar(id, dto));
    }

    @DeleteMapping("deletarPorId/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}