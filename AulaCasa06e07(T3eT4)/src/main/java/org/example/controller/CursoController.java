package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.Aluno;
import org.example.domain.Curso;
import org.example.repository.AlunoRepository;
import org.example.repository.CursoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;
    private final AlunoRepository alunoRepository;

    public CursoController(CursoRepository cursoRepository, AlunoRepository alunoRepository) {
        this.cursoRepository = cursoRepository;
        this.alunoRepository = alunoRepository;
    }

    @GetMapping
    public List<Curso> listarTodos() { return cursoRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        return cursoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso criar(@RequestBody @Valid Curso curso) {
        // Busca os alunos pelo id antes de salvar
        if (curso.getAlunos() != null) {
            List<Aluno> alunosGerenciados = curso.getAlunos().stream()
                    .map(a -> alunoRepository.findById(a.getId())
                            .orElseThrow(() -> new RuntimeException("Aluno " + a.getId() + " não encontrado")))
                    .collect(Collectors.toList());
            curso.setAlunos(alunosGerenciados);
        }
        return cursoRepository.save(curso);
    }
}