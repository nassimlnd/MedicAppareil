package org;

import Exceptions.ConsultationNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Consultation {

    // Attributs

    private int id = 0;
    private String date;
    private String description;
    private Patient patient;
    private String nomMedecin;
    int cb = 0;

    ArrayList<Consultation> listeConsultation = new ArrayList<Consultation>();

    // Constructeurs

    public Consultation(String date, String description, Patient patient, String nomMedecin) {
        try {
            this.setId();
            this.date = date;
            this.description = description;
            this.patient = patient;
            this.nomMedecin = nomMedecin;
            ajouterConsultation(this.patient);
        }
        catch (IOException ioException) {

        }
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId() throws IOException {
        FileReader fileReader;
        Scanner sc;
        File file = new File("consultation.txt");

        if (file.exists()) {
            fileReader = new FileReader("consultation.txt");
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
        }
        else {
            file.createNewFile();
            this.id = 1;
        }

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String antecedents) {
        this.description = antecedents;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    // Methodes

    void ajouterConsultation(Patient patient) {
        try {

            File file = new File("consultation.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            Scanner sc = new Scanner(fileReader);

            boolean contains = false;

            /*while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.toLowerCase().contains(String.valueOf(this.getId())) || line.toLowerCase().contains(this.getDateNaissance())) {
                    contains = true;
                }
            }*/

            fileReader.close();

            if (contains == false) {
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(this.getId() + ";" + this.getDate() + ";" + this.getNomMedecin() + ";" + this.patient.getNom() + ";" + this.getDescription() + "\n");
                bufferedWriter.close();
                fileWriter.close();
                listeConsultation.add(this);
                patient.ajouterConsultation(this);
            } else {
                System.out.println("org.Consultation déjà existante.");
            }

        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    void supprimerConsultation(Patient patient) throws ConsultationNotFoundException {
        if (patient.getListeConsultationPatient().contains(this)) {
            patient.supprimerConsulation(this);
        }
        else throw new ConsultationNotFoundException();
    }


}
