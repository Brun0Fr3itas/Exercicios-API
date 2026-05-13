package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErroResposta.CampoErro> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> new ErroResposta.CampoErro(f.getField(), f.getDefaultMessage()))
                .toList();

        return new ErroResposta(400, "Erro de validação", erros);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return new ErroResposta(404, ex.getMessage(), List.of());
    }
}