package br.com.zup.estrelas.sb.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegrasDeNegocioException.class)
    public ResponseEntity<CustomErrorMessage> handleGenericException(RegrasDeNegocioException e) {
        CustomErrorMessage error =
                new CustomErrorMessage("UNPROCESSABLE_ENTITY", e.getMensagemErro());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.UNPROCESSABLE_ENTITY.value()));
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
