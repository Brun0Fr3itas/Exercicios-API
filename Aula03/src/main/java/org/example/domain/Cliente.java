package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 60, message = "A descrição deve conter no máximo 60 caracteres.")
    @Column(nullable = false, length = 60)
    private String nome;

    @Column(length = 11, unique = true)
    private String cpf;

    @Size(max = 50, message = "O email deve conter no máximo 50 caracteres.")
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, name = "data_nascimento")
    private LocalDate dataNascimento;

    public Cliente() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}