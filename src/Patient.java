import Exceptions.PatientNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient {

    // Attributs

    private int id;
    private String nom;
    private int datedenaissance;
    private ArrayList<Consultation> listeConsultationPatient;
    int cb = 0;

    static ArrayList<Patient> listePatient = new ArrayList<Patient>();

    // Constructeur

    public Patient(String nom, String datedenaissance) {
        //cb++;
        //this.id = cb;
        try {
            this.setId();
            this.nom = nom;
            if (datedenaissance.length() > 8) {
                throw new NumberFormatException();
            }
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
        catch (NumberFormatException exception2) {
            System.out.println("Date de naissance incorrecte.");
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

    public static String getPatient(int id) throws PatientNotFoundException {
        FileReader fileReader;
        Scanner sc;
        try {
            fileReader = new FileReader("patient.txt");
            sc = new Scanner(fileReader);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("-");
                if (Integer.parseInt(split[0]) == id) {
                    System.out.println(split[1]);
                    return split[1];
                }
                else throw new PatientNotFoundException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Patient introuvable");;
        }
        finally {
            return null;
        }


    }


}
