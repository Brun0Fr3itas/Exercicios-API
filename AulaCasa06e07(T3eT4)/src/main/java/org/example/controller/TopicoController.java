package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.Topico;
import org.example.repository.TopicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Topico> listarTodos() { return repository.findAll(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topico criar(@RequestBody @Valid Topico topico) {
        return repository.save(topico);
    }

    @GetMapping("/curso/{idCurso}")
    public List<Topico> listarPorCurso(@PathVariable Long idCurso) {
        return repository.findByCursoId(idCurso);
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