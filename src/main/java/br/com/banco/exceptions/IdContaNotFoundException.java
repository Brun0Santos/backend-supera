package br.com.banco.exceptions;

public class IdContaNotFoundException extends RuntimeException {
    public IdContaNotFoundException() {
        super("ContaID informada nao encontrada");
    }
}
