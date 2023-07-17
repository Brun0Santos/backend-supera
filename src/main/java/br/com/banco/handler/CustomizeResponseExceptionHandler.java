package br.com.banco.handler;

import br.com.banco.exceptions.IdContaNotFoundException;
import br.com.banco.exceptions.OperadorNotFoundException;
import br.com.banco.exceptions.ParserDataException;
import br.com.banco.exceptions.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class CustomizeResponseExceptionHandler {
    @ExceptionHandler(IdContaNotFoundException.class)
    public ResponseEntity<ResponseException> handleAllExceptions(IdContaNotFoundException ex, WebRequest request) {
        ResponseException exception = new ResponseException(LocalDate.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParserDataException.class)
    public ResponseEntity<ResponseException> dataParserError(ParserDataException ex, WebRequest request) {
        ResponseException exception = new ResponseException(LocalDate.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperadorNotFoundException.class)
    public ResponseEntity<ResponseException> operadorNotFound(OperadorNotFoundException ex, WebRequest request) {
        ResponseException exception = new ResponseException(LocalDate.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
