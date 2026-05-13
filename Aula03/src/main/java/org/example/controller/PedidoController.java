package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.Pedido;
import org.example.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.example.exception.RecursoNaoEncontradoException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository repository;

    public PedidoController(PedidoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pedido " + id + " não encontrado"));
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@RequestBody @Valid Pedido pedido){
        return repository.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id,
                                            @RequestBody @Valid Pedido pedidoAtualizado){
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        pedidoAtualizado.setId(id);
        return ResponseEntity.ok(repository.save(pedidoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (!repository.existsById(id))
            return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}