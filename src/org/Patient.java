package org;

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
    
    // Martin le bg

    public static ArrayList<Patient> listePatient = new ArrayList<Patient>();

    // Constructeur

    public Patient(String nom, String dateNaissance, String nbSecuriteSociale) {

        try {
            this.setId();
            this.nom = nom;

            this.setDateNaissance(dateNaissance);
            this.setNbSecuriteSociale(nbSecuriteSociale);

            this.listeConsultationPatient = new ArrayList<Consultation>();

            this.ajouterPatient();
        }
        catch (NumberFormatException | IOException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
        }

    }

    public Patient(String id, String nom, String dateNaissance, String nbSecuriteSociale) {
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

    // Methodes


    @Override
    public String toString() {
        return nom + " " + dateNaissance;
    }

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
            sc.close();
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
                if (line.toLowerCase().contains(this.getDateNaissance())) {
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
            }

        }
        catch (IOException ioException) {
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
        System.out.println("Que souhaitez vous modifier ? n pour le nom, d pour la date de naissance, et s pour le numéro de sécurité social.");
        Scanner sc = new Scanner(System.in);
        char choix = sc.nextLine().charAt(0);

        switch (choix) {
            case 'n' :
                System.out.println("Veuillez saisir le nouveau nom du patient :");
                String n = sc.nextLine();
                this.setNom(n);
                break;
            case 'd' :
                System.out.println("Date de naissance ?");
                String date = sc.nextLine();
                this.setDateNaissance(date);
                break;
            case 's' :
                System.out.println("Numéro de sécurité sociale :");
                String nbSecu = sc.nextLine();
                this.setNbSecuriteSociale(nbSecu);
                break;
        }

    }

    public static void initList() {
        FileReader fileReader;
        Scanner sc;
        try {
            fileReader = new FileReader("patient.txt");
            sc = new Scanner(fileReader);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(";");
                listePatient.add(new Patient(split[0], split[1], split[2], split[3]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Patient introuvable");;
        }
    }

    /*public static void ajouterPatient(String nom, String dateNaissance, String nbSecuriteSociale) {
        new Patient(nom, dateNaissance, nbSecuriteSociale);

    }*/


}
