package org;

import Exceptions.PatientNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient {

    // Attributs

    private int id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String nbSecuriteSociale;
    private ArrayList<Consultation> listeConsultationPatient;
    
    // Martin le bg

    public static ArrayList<Patient> listePatient = new ArrayList<Patient>();

    // Constructeur

    public Patient(String nom, String prenom, String dateNaissance, String nbSecuriteSociale) {

        try {
            this.setId();
            this.nom = nom;
            this.prenom = prenom;

            this.setDateNaissance(dateNaissance);
            this.setNbSecuriteSociale(nbSecuriteSociale);

            this.listeConsultationPatient = new ArrayList<Consultation>();

            this.ajouterPatient();
        }
        catch (NumberFormatException | IOException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
        }

    }

    public Patient(int id, String nom, String prenom, String dateNaissance, String nbSecuriteSociale) {
        try {
            this.setId();
            this.nom = nom;
            this.dateNaissance = dateNaissance;
            this.nbSecuriteSociale = nbSecuriteSociale;
            this.listeConsultationPatient = new ArrayList<Consultation>();
            this.ajouterPatient();
        } catch (NumberFormatException | IOException e) {
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
                    String[] split = line.split(";");
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
        if (String.valueOf(nbSecuriteSociale).length() != 13) {
            throw new NumberFormatException("Numéro de sécurité sociale incorrect.");
        }

        this.nbSecuriteSociale = nbSecuriteSociale;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        String[] date = dateNaissance.split("/");

        if (date[0].length() != 2 || date[1].length() != 2 || date[2].length() != 4) {
            throw new NumberFormatException("Date de naissance incorrecte.");
        }
        this.dateNaissance = dateNaissance;
    }

    public static ArrayList<Patient> getListePatient() {
        return listePatient;
    }

    public static void setListePatient(ArrayList<Patient> listePatient) {
        Patient.listePatient = listePatient;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Methodes


    @Override
    public String toString() {
        return nom + " " + dateNaissance;
    }

    public static Patient getPatient(String nbSecuriteSociale) throws PatientNotFoundException {
        FileReader fileReader;
        Scanner sc;
        Patient patient = null;
        try {
            fileReader = new FileReader("patient.txt");
            sc = new Scanner(fileReader);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(";");
                if (split[3].equals(nbSecuriteSociale)) {
                    patient = Patient.listePatient.get(Integer.parseInt(split[0]));
                }
                else throw new PatientNotFoundException();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Patient introuvable");
            return null;
        }
        return patient;
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
                if (line.toLowerCase().contains(this.getDateNaissance())) {
                    contains = true;
                }
            }

            fileReader.close();

            if (contains == false) {
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(this.getId() + ";" + this.getNom() + ";" + this.getPrenom() + ";" + this.getDateNaissance() + ";" + this.getNbSecuriteSociale() + "\n");
                bufferedWriter.close();
                fileWriter.close();
                listePatient.add(this);
            }

        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void modif() {
        FileReader fileReader;
        Scanner sc;
        FileWriter fileWriter;
        String oldLine = "";
        String newLine;
        String oldContent = "";
        String newContent = "";

        try {
            File file = new File("patient.txt");

            fileReader = new FileReader(file.getAbsoluteFile());
            sc = new Scanner(fileReader);
            fileWriter = new FileWriter("patient.txt");

            newLine = this.getId() + ";" + this.getNom() + ";" + this.getPrenom() + ";" + this.getDateNaissance() + ";" + this.getNbSecuriteSociale() + "\n";

            while (sc.hasNextLine()) {

                oldContent += sc.nextLine() + "\n";

                if (String.valueOf(sc.nextLine().charAt(0)).equals(this.id)) {
                    oldLine = sc.nextLine();
                }
            }

            newContent = oldContent.replaceAll(oldLine, newLine);

            fileWriter.write(newContent);

            fileReader.close();
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    void ajouterConsultation(Consultation consultation) {
        this.listeConsultationPatient.add(consultation);
    }

    void supprimerConsulation(Consultation consultation) {
        this.listeConsultationPatient.remove(consultation);
    }

    void modifierPatient() {

    }

    public static void initList() {
        FileReader fileReader;
        Scanner sc;
        boolean contains = false;
        try {
            fileReader = new FileReader("patient.txt");
            sc = new Scanner(fileReader);

            Patient.listePatient.clear();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(";");

                for (int i = 0; i < Patient.listePatient.size(); i ++) {
                    if (split[4].equals(Patient.listePatient.get(i).getNbSecuriteSociale())) {
                        contains = true;
                    }
                }


                if (contains == false) {
                    listePatient.add(new Patient(split[1], split[2], split[3], split[4]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier inexistant");;
        }
    }


}
