package org;

import Exceptions.ConsultationNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Consultation {

    // Attributs

    private int id;
    private String date;
    private String pathologies;
    private Patient patient;
    private String nomMedecin;
    private boolean octroi;

    ArrayList<Consultation> listeConsultation = new ArrayList<Consultation>();

    // Constructeurs

    public Consultation(String date, String pathologies, Patient patient, String nomMedecin, boolean octroi) {
        try {
            this.setId();
            this.date = date;
            this.pathologies = pathologies;
            this.patient = patient;
            this.nomMedecin = nomMedecin;
            this.octroi = octroi;
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

    public String getPathologies() {
        return pathologies;
    }

    public void setPathologies(String pathologies) {
        this.pathologies = pathologies;
    }

    public String getNomMedecin() {
        return nomMedecin;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    public boolean isOctroi() {
        return octroi;
    }

    public void setOctroi(boolean octroi) {
        this.octroi = octroi;
    }

    // Methodes

    void ajouterConsultation(Patient patient) {
        try {

            File file = new File("consultation.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(file.getAbsoluteFile());

            fileReader.close();

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(this.getId() + ";" + this.getDate() + ";" + this.getNomMedecin() + ";" + this.patient.getNom() + ";" + this.getPathologies() + "\n");
            bufferedWriter.close();
            fileWriter.close();
            listeConsultation.add(this);
            patient.ajouterConsultation(this);

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
