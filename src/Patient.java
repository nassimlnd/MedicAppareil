import Exceptions.PatientNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient {

    // Attributs

    private int id;
    private String nom;
    private String dateNaissance;
    private String nbSecuriteSociale;
    private ArrayList<Consultation> listeConsultationPatient;
    //int cb = 0;

    static ArrayList<Patient> listePatient = new ArrayList<Patient>();

    // Constructeur

    public Patient(String nom, String dateNaissance, String nbSecuriteSociale) {
        //cb++;
        //this.id = cb;
        try {
            this.setId();
            this.nom = nom;

            String[] date = dateNaissance.split("/");

            if (date[0].length() != 2 || date[1].length() != 2 || date[2].length() != 4) {
                throw new NumberFormatException("Date de naissance incorrecte.");
            } else if (String.valueOf(nbSecuriteSociale).length() != 13) {
                throw new NumberFormatException("Numéro de sécurité sociale incorrect.");
            }

            this.dateNaissance = dateNaissance;

            this.nbSecuriteSociale = nbSecuriteSociale;

            this.listeConsultationPatient = new ArrayList<Consultation>();

            this.ajouterPatient();
        }
        catch (NumberFormatException | IOException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
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

    public String getNbSecuriteSociale() {
        return nbSecuriteSociale;
    }

    public void setNbSecuriteSociale(String nbSecuriteSociale) {
        this.nbSecuriteSociale = nbSecuriteSociale;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    // Methodes

    public static void getPatient(String nbSecuriteSociale) throws PatientNotFoundException {
        FileReader fileReader;
        Scanner sc;
        try {
            fileReader = new FileReader("patient.txt");
            sc = new Scanner(fileReader);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(";");
                if (split[3].equals(nbSecuriteSociale)) {
                    System.out.println(split[1]);
                }
                else throw new PatientNotFoundException();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Patient introuvable");;
        }


    }

    void ajouterPatient() {
        try {

            File file = new File("patient.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            Scanner sc = new Scanner(fileReader);

            boolean contains = false;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.toLowerCase().contains(this.getNom().toLowerCase()) || line.toLowerCase().contains(this.getDateNaissance())) {
                    contains = true;
                }
            }

            fileReader.close();

            if (contains == false) {
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(this.getId() + ";" + this.getNom() + ";" + this.getDateNaissance() + ";" + this.getNbSecuriteSociale() + "\n");
                bufferedWriter.close();
                fileWriter.close();
                listePatient.add(this);
            } else {
                System.out.println("Patient déjà existant.");
            }

        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    void ajouterConsultation(Consultation consultation) {
        this.listeConsultationPatient.add(consultation);
    }


}
