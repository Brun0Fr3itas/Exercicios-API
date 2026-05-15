package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.Avaliacao;
import org.example.repository.AvaliacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository repository;

    public AvaliacaoController(AvaliacaoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Avaliacao> listarTodos() { return repository.findAll(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao criar(@RequestBody @Valid Avaliacao avaliacao){
        return repository.save(avaliacao);
    }
}