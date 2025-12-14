package app.domain.model.exceptions;

public class OperationCanceledByUserException extends Exception {
    public OperationCanceledByUserException(String errorMessage) {
        super(errorMessage);
    }
}
