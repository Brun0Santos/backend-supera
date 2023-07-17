package br.com.banco.exceptions;

public class ParserDataException extends RuntimeException {
    public ParserDataException() {
        super("Erro ao fazer o parser da data");
    }
}
