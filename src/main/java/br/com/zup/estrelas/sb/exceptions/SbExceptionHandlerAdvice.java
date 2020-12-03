package br.com.zup.estrelas.sb.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javassist.NotFoundException;

@RestControllerAdvice
public class SbExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<SbErrorResponse> handleGenericException(Exception e, WebRequest request) {

        String descricaoErro = e.getLocalizedMessage();
        if (descricaoErro == null) {
            descricaoErro = e.toString();
        }

        int statusHttp = 0;

        SbErrorResponse sbErrorResponse =
                new SbErrorResponse(descricaoErro, statusHttp, LocalDateTime.now());

        return null;

    }

}
