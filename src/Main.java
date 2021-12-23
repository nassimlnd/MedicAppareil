import Exceptions.PatientNotFoundException;
import GUI.Frame;

import javax.swing.*;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws PatientNotFoundException {

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
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
}
