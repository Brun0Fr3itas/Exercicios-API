package org.example.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErroResposta {

    private int status;
    private String titulo;
    private LocalDateTime dataHora;
    private List<CampoErro> erros;

    public ErroResposta(int status, String titulo, List<CampoErro> erros) {
        this.status = status;
        this.titulo = titulo;
        this.dataHora = LocalDateTime.now();
        this.erros = erros;
    }

    public int getStatus() { return status; }
    public String getTitulo() { return titulo; }
    public LocalDateTime getDataHora() { return dataHora; }
    public List<CampoErro> getErros() { return erros; }

    public static class CampoErro {
        private String campo;
        private String mensagem;

        public CampoErro(String campo, String mensagem) {
            this.campo = campo;
            this.mensagem = mensagem;
        }

        public String getCampo() { return campo; }
        public String getMensagem() { return mensagem; }
    }
}