import Exceptions.PatientNotFoundException;

public class Main {

    public static void main(String[] args) {

        try {
            System.out.println(Patient.getPatient(2));
        }
        catch (PatientNotFoundException e3) {

        }


    }
}
