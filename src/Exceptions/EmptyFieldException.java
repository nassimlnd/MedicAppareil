package Exceptions;

public class EmptyFieldException extends Exception {

    public EmptyFieldException() {
        super();
    }

    public EmptyFieldException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
