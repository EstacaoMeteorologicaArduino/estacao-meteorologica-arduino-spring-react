package br.com.estacaometeorologica.adapter.error;

import br.com.estacaometeorologica.model.error.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<Object> handleRequestException(RequestException ex) {

        log.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(NOT_FOUND)
                .body(ApiError.builder().mensagem(ex.getMessage()).build());
    }
}