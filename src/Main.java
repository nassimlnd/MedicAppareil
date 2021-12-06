import Exceptions.PatientNotFoundException;

public class Main {

    public static void main(String[] args) throws PatientNotFoundException {

        Patient patient = new Patient("Nassim", "02/08/2002", "1234567891234");
        Patient patient2 = new Patient("Magomed", "02/08/2002", "1234567891234");
        for (Patient patient1 : Patient.listePatient) {
            System.out.println(patient1.getNom());
        }

        Patient.getPatient("1234567891234");

        Consultation consultation = new Consultation("test", "test", patient, "test");


    }
}
