package br.com.zup.estrelas.sb.exceptions;

import static java.util.Objects.nonNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.zup.estrelas.sb.dto.ErrorDTO;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public @ResponseBody List<ErrorDTO> handleValidationError(MethodArgumentNotValidException e) {

        List<ErrorDTO> errosDeValidacao = new ArrayList<>();

        for (ObjectError erro : e.getBindingResult().getAllErrors()) {

            if (nonNull(erro.getCodes())) {

                String mensagemASerExibida = erro.getDefaultMessage();

                errosDeValidacao.add(new ErrorDTO(mensagemASerExibida.toString()));
            }
        }

        return errosDeValidacao;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)

    public @ResponseBody ErrorDTO handleGenericException(Exception e) {

        ErrorDTO erro = new ErrorDTO(e.getLocalizedMessage());

        return erro;
    }

}
