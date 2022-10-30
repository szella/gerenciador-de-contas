package br.com.szella.gerenciadordecontas.exception;

public class DBException extends RuntimeException {
    public DBException(String message) {
        super(message);
    }
}
