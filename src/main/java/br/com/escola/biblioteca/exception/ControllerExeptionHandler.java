package br.com.escola.biblioteca.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.escola.biblioteca.entity.ErroResposta;

@ControllerAdvice
public class ControllerExeptionHandler extends ResponseEntityExceptionHandler {

    // Trata erros de validação (@NotBlank, @NotNull, etc)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            erros.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ErroResposta erroResposta = new ErroResposta(
                status.value(),
                "Existem Campos Inválidos, Confira o preechimento",
                LocalDateTime.now(),
                erros);
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    // Trata recurso não encontrado → 404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroResposta> handleNotFoundException(NotFoundException ex) {
        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                List.of());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
    }

    // Trata regra de negócio violada → 409
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErroResposta> handleBusinessException(BusinessException ex) {
        ErroResposta erroResposta = new ErroResposta(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                List.of());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erroResposta);
    }

}