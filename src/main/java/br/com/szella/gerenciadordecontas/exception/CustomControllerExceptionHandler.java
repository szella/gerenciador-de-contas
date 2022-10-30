package br.com.szella.gerenciadordecontas.exception;

import br.com.szella.gerenciadordecontas.model.response.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DBException.class)
    protected ResponseEntity<ErroResponse> handleDBException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErroResponse.builder()
                                .mensagem(e.getMessage())
                                .build()
                );
    }

}
