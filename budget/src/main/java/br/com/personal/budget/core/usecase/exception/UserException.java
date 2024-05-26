package br.com.personal.budget.core.usecase.exception;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
