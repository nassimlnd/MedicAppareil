import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Patient {

    // Attributs

    private int id;
    private String nom;
    private ArrayList<Consultation> listeConsultationPatient;
    int cb = 0;

    static ArrayList<Patient> listePatient = new ArrayList<Patient>();

    // Constructeur

    public Patient(String nom) {
        //cb++;
        //this.id = cb;
        try {
            this.setId();
            this.nom = nom;
            this.listeConsultationPatient = new ArrayList<Consultation>();
            File file = new File("patient.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            Scanner sc = new Scanner(fileReader);

            boolean contains = false;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.toLowerCase().contains(this.nom.toLowerCase())) {
                    contains = true;
                }
            }

            fileReader.close();

            if (contains == false) {
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(this.getId() + "-" + this.getNom() + "\n");
                bufferedWriter.close();
                fileWriter.close();
            }
            else {
                System.out.println("Patient déjà existant.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId() throws IOException {
        FileReader fileReader;
        Scanner sc;
        try {
            File file = new File("patient.txt");

            if (file.exists()) {
                fileReader = new FileReader("patient.txt");
                sc = new Scanner(fileReader);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] split = line.split("-");
                    try {
                        this.id = Integer.parseInt(split[0]) + 1;
                    } catch (NumberFormatException e) {
                        this.id = 1;
                    }
                }
            } else {
                file.createNewFile();
                this.id = 1;
            }
        }
        catch (FileNotFoundException exception) {

        }


    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Consultation> getListeConsultationPatient() {
        return listeConsultationPatient;
    }

    public void setListeConsultationPatient(ArrayList<Consultation> listeConsultationPatient) {
        this.listeConsultationPatient = listeConsultationPatient;
    }

    // Methodes


}
