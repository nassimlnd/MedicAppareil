package Exceptions;

public class ConsultationNotFoundException extends Exception {

    public ConsultationNotFoundException() {
        super();
    }

    public ConsultationNotFoundException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
