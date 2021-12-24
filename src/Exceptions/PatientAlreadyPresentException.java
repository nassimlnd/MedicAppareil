package Exceptions;

public class PatientAlreadyPresentException extends Exception {

    public PatientAlreadyPresentException() {
        super();
    }

    public PatientAlreadyPresentException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
