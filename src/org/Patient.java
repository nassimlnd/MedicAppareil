package org;

import Exceptions.PatientAlreadyPresentException;
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

    public static ArrayList<Patient> listePatient = new ArrayList<>();

    // Constructeur

    public Patient(String nom, String prenom, String dateNaissance, String nbSecuriteSociale) throws PatientAlreadyPresentException, NumberFormatException, IOException {

            this.setId();
            this.nom = nom;
            this.prenom = prenom;

            this.setDateNaissance(dateNaissance);
            this.setNbSecuriteSociale(nbSecuriteSociale);

            this.listeConsultationPatient = new ArrayList<>();

            this.ajouterPatient();

    }

    public Patient(int id, String nom, String prenom, String dateNaissance, String nbSecuriteSociale) {
        try {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.dateNaissance = dateNaissance;
            this.nbSecuriteSociale = nbSecuriteSociale;
            this.listeConsultationPatient = new ArrayList<>();
        } catch (NumberFormatException e) {
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
                    if (Integer.parseInt(split[0]) <= 0) {
                        this.id = 1;
                    }
                    else {
                        try {
                            this.id = Integer.parseInt(split[0]) + 1;
                        } catch (NumberFormatException e) {
                            this.id = 1;
                        }
                    }
                }
                if (!sc.hasNextLine()) {
                    this.id = 1;
                }
            } else {
                file.createNewFile();
                this.id = 1;
            }
        }
        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }


    }

    public void setIdFichier(int id) {
        this.id = id;
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

    void ajouterPatient() throws IOException, PatientAlreadyPresentException {
        File file = new File("patient.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileReader fileReader = new FileReader(file.getAbsoluteFile());
        Scanner sc = new Scanner(fileReader);


        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.toLowerCase().contains(this.getNbSecuriteSociale())) {
                throw new PatientAlreadyPresentException();
            }
        }

        fileReader.close();

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(this.getId() + ";" + this.getNom() + ";" + this.getPrenom() + ";" + this.getDateNaissance() + ";" + this.getNbSecuriteSociale() + "\n");
        bufferedWriter.close();
        fileWriter.close();
        listePatient.add(this);


        initFichier();
    }

    public void modif() {
        FileReader fileReader;
        Scanner sc;
        FileWriter fileWriter;
        String oldLine = "";
        String newLine;
        String oldContent = "";
        String newContent;

        try {
            File file = new File("patient.txt");

            fileReader = new FileReader(file.getAbsoluteFile());
            sc = new Scanner(fileReader);

            newLine = this.getId() + ";" + this.getNom() + ";" + this.getPrenom() + ";" + this.getDateNaissance() + ";" + this.getNbSecuriteSociale();

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                oldContent += line + "\n";

                if (Integer.parseInt(String.valueOf(line.charAt(0))) == this.id) {
                    oldLine = line;
                }
            }

            newContent = oldContent.replaceAll(oldLine, newLine);

            fileWriter = new FileWriter("patient.txt");
            fileWriter.append(newContent);
            fileWriter.flush();

            fileReader.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initFichier() {
        try {
            File file = new File("patient.txt");

            if (file.exists()) {
                FileWriter fileWriter = new FileWriter("patient.txt");
                fileWriter.close();
            }
            else {
                file.createNewFile();
            }

            for (int i = 0; i < Patient.listePatient.size(); i ++) {
                Patient.listePatient.get(i).setIdFichier(i+1);
            }

            for(Patient patient : listePatient) {
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(patient.getId() + ";" + patient.getNom() + ";" + patient.getPrenom() + ";" + patient.getDateNaissance() + ";" + patient.getNbSecuriteSociale() + "\n");
                bufferedWriter.close();
                fileWriter.close();
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
                    if (!split[4].equals(Patient.listePatient.get(i).getNbSecuriteSociale())) {
                        continue;
                    }
                    contains = true;
                }


                if (!contains) {
                    listePatient.add(new Patient(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier inexistant");
        }
    }

    public void supprimerPatient() {
        listePatient.remove(this);
        initFichier();
    }

    public static int recherche(String value) throws FileNotFoundException, PatientNotFoundException {
        FileReader fileReader;
        Scanner sc;
        int id;

        fileReader = new FileReader("patient.txt");
        sc = new Scanner(fileReader);

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split(";");

            if (line.contains(value)) {
                id = Integer.parseInt(split[0]);
                return id;
            }
        }
        throw new PatientNotFoundException("Patient introuvable");
    }



}
