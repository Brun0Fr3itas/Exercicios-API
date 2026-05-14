package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ClienteVip extends Cliente {

    @NotBlank(message = "O consultor responsável é obrigatório.")
    private String consultorResponsavel;

    public String getConsultorResponsavel() { return consultorResponsavel; }
    public void setConsultorResponsavel(String c) { this.consultorResponsavel = c; }
}