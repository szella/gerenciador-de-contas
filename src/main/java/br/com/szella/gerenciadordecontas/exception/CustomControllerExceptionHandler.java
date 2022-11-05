package br.com.szella.gerenciadordecontas.exception;

import br.com.szella.gerenciadordecontas.model.response.ErroResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomControllerExceptionHandler extends ResponseEntityExceptionHandler {


    private static ResponseEntity<ErroResponse> retornaErroResponse(Exception e, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(
                        ErroResponse.builder()
                                .mensagem(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(DBException.class)
    protected ResponseEntity<ErroResponse> handleDBException(Exception e) {
        return retornaErroResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TipoDespesaException.class)
    protected ResponseEntity<ErroResponse> handleTipoDespesaException(Exception e) {
        return retornaErroResponse(e, HttpStatus.BAD_REQUEST);
    }

}
