package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.Editora;
import org.example.repository.EditoraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    private final EditoraRepository repository;

    public EditoraController(EditoraRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Editora> listarTodos() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Editora> buscarPorId(@PathVariable Long id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Editora criar(@RequestBody @Valid Editora editora){
        return repository.save(editora);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}