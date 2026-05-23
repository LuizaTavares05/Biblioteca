package br.com.escola.biblioteca.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.biblioteca.model.Autor;
import br.com.escola.biblioteca.repository.AutorRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/biblioteca")
public class AutorController {
  
    @Autowired
    private AutorRepository autorRepository;


    @GetMapping("/all")
    public List<Autor> listar(){
        return autorRepository.findAll();
    }
  
    @GetMapping("/buscar/{id}")
    public  ResponseEntity<Autor> listarPorId(@PathVariable Long id){
        Optional<Autor> autorOptional= autorRepository.findById(id);
        if(autorOptional.isPresent()){
        return ResponseEntity.ok(autorOptional.get());

        }else {
            return ResponseEntity.notFound().build();
        }
    }
        @PostMapping("/cadastrar")
        @ResponseStatus(HttpStatus.CREATED)
        public Autor cadastroAutor(@Valid @RequestBody Autor autor){
            autorRepository.save(autor);
            return ResponseEntity.ok(autor).getBody();
        
    }

    @PutMapping("/atualizarAutor/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Autor> atualizarAutor(@Valid @PathVariable Long id, @RequestBody Autor autor){
        if(!autorRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        autor.setId(id);
        autorRepository.save(autor);
        return ResponseEntity.ok(autor);


    }
    @DeleteMapping("/deletarAutor/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id){
        if(!autorRepository.existsById(id)){
            return ResponseEntity.notFound().build();

        }
        autorRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }
        
    }
    


    
    
    
    


