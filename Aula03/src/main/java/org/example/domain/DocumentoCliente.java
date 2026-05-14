package org.example.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class DocumentoCliente {

    @NotBlank(message = "O CPF/CNPJ é obrigatório.")
    private String cpfOuCnpj;

    private String rgInscricaoEstadual;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @Enumerated(EnumType.ORDINAL)
    private StatusCliente statusCliente;

    public String getCpfOuCnpj() { return cpfOuCnpj; }
    public void setCpfOuCnpj(String cpfOuCnpj) { this.cpfOuCnpj = cpfOuCnpj; }

    public String getRgInscricaoEstadual() { return rgInscricaoEstadual; }
    public void setRgInscricaoEstadual(String rg) { this.rgInscricaoEstadual = rg; }

    public TipoCliente getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(TipoCliente tipoCliente) { this.tipoCliente = tipoCliente; }

    public StatusCliente getStatusCliente() { return statusCliente; }
    public void setStatusCliente(StatusCliente statusCliente) { this.statusCliente = statusCliente; }
}