import Exceptions.PatientNotFoundException;
import GUI.Frame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws PatientNotFoundException {

        //Patient patient1 = new Patient("Nassim", "02/08/2002", "1234567891234");

        try {
            Frame frame = new Frame();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
