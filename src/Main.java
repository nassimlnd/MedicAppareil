import Exceptions.PatientNotFoundException;
import GUI.MenuFrame;
import org.Patient;

public class Main {

    public static void main(String[] args) throws PatientNotFoundException {

        Patient.initList();




        Patient patient1 = new Patient("Nassim", "02/08/2002", "1234567891234");
        /*Patient patient2 = new Patient("Magomed", "02/08/2002", "1234567891234");
        for (Patient patient1 : Patient.getListePatient()) {
            System.out.println(patient1.getNom());
        }*/

        //Patient.getPatient("1234567891234");

        //Consultation consultation = new Consultation("test", "test", patient, "test");

        for (Patient patient: Patient.listePatient) {
            System.out.println(patient.getNom());
        }

        MenuFrame menuFrame = new MenuFrame();

        menuFrame.setVisible(true);


    }
}
