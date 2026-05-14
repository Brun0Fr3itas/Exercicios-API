package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.ClienteVip;
import org.example.repository.ClienteVipRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes-vip")
public class ClienteVipController {

    private final ClienteVipRepository repository;

    public ClienteVipController(ClienteVipRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<ClienteVip> listarTodos() { return repository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteVip> buscarPorId(@PathVariable Long id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteVip criar(@RequestBody @Valid ClienteVip clienteVip){
        return repository.save(clienteVip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteVip> atualizar(@PathVariable Long id,
                                                @RequestBody @Valid ClienteVip atualizado){
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        atualizado.setId(id);
        return ResponseEntity.ok(repository.save(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}