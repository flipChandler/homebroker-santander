package br.com.santander.backend.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message); // quem trata essa mensagem é classe pai (RuntimeException)
    }
}
