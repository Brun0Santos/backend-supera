package br.com.banco.exceptions;

public class OperadorNotFoundException extends RuntimeException {
    public OperadorNotFoundException(String message) {
        super(message);
    }
}
