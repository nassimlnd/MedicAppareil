package org;

import Exceptions.ConsultationNotFoundException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Consultation {

    // Attributs

    private int id;
    private String date;
    private String pathologies;
    private Patient patient;
    private String nomMedecin;
    private String appareil;
    private boolean octroi;

    public static ArrayList<Consultation> listeConsultation = new ArrayList<Consultation>();

    // Constructeurs

    public Consultation(Patient patient, String pathologies, String nomMedecin) throws IOException {
        this.setId();
        this.setDate();
        this.pathologies = pathologies;
        this.patient = patient;
        this.nomMedecin = nomMedecin;
        ajouterConsultation(this.patient);
    }

    public Consultation(Patient patient, String pathologies, String appareil, String nomMedecin) throws IOException {
        this.setId();
        this.setDate();
        this.pathologies = pathologies;
        this.patient = patient;
        this.nomMedecin = nomMedecin;
        this.appareil = appareil;
        this.octroi = false;
        ajouterConsultation(this.patient);
    }

    public Consultation(int id, Patient patient, String nomMedecin, String date, String pathologies, String appareil, boolean octroi) {
        this.id = id;
        this.patient = patient;
        this.nomMedecin = nomMedecin;
        this.date = date;
        this.pathologies = pathologies;
        this.appareil = appareil;
        this.octroi = octroi;
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
                if (Integer.parseInt(split[0]) <= 0) {
                    this.id = 1;
                }
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

    public void setIdFichier(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {

        LocalDate localDate = LocalDate.now();

        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        this.date = formattedDate;
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

    public void setDate(String date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAppareil() {
        return appareil;
    }

    public void setAppareil(String appareil) {
        this.appareil = appareil;
    }

    public static ArrayList<Consultation> getListeConsultation() {
        return listeConsultation;
    }

    public void setListeConsultation(ArrayList<Consultation> listeConsultation) {
        this.listeConsultation = listeConsultation;
    }

    public boolean getOctroi() {
        return this.octroi;
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
            bufferedWriter.write(this.getId() + ";" + (this.getPatient().getId() - 1) + ";" + this.getNomMedecin() + ";" + this.getDate() + ";"  + this.getPathologies() + ";" + this.getAppareil() + ";" + this.getOctroi() + "\n");
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

    public static void initList() {
        FileReader fileReader;
        Scanner sc;
        Consultation consultation;
        try {
            fileReader = new FileReader("consultation.txt");
            sc = new Scanner(fileReader);

            listeConsultation.clear();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(";");
                consultation = new Consultation(Integer.parseInt(split[0]), Patient.getListePatient().get(Integer.parseInt(split[1])), split[2], split[3], split[4], split[5], Boolean.valueOf(split[6]).booleanValue());
                listeConsultation.add(consultation);
                Patient.getListePatient().get(Integer.parseInt(split[1])).ajouterConsultation(consultation);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier inexistant");
        }
    }

    public void initFichier() {
        try {
            File file = new File("consultation.txt");

            if (file.exists()) {
                FileWriter fileWriter = new FileWriter("consultation.txt");
                fileWriter.close();
            }
            else {
                file.createNewFile();
            }

            for (int i = 0; i < Consultation.getListeConsultation().size(); i ++) {
                Consultation.getListeConsultation().get(i).setIdFichier(i+1);
            }

            for(Consultation consultation : listeConsultation) {
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(consultation.getId() + ";" + (consultation.getPatient().getId() - 1) + ";" + consultation.getNomMedecin() + ";" + consultation.getDate() + ";"  + consultation.getPathologies() + ";" + consultation.getAppareil() + ";" + consultation.getOctroi() + "\n");
                bufferedWriter.close();
                fileWriter.close();
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
        String newContent;

        try {
            File file = new File("consultation.txt");

            fileReader = new FileReader(file.getAbsoluteFile());
            sc = new Scanner(fileReader);

            newLine = this.getId() + ";" + (this.getPatient().getId() - 1) + ";" + this.getNomMedecin() + ";" + this.getDate() + ";"  + this.getPathologies() + ";" + this.getAppareil() + ";" + this.getOctroi();

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                oldContent += line + "\n";

                if (Integer.parseInt(String.valueOf(line.charAt(0))) == this.id) {
                    oldLine = line;
                }
            }

            newContent = oldContent.replaceAll(oldLine, newLine);

            fileWriter = new FileWriter("consultation.txt");
            fileWriter.append(newContent);
            fileWriter.flush();

            fileReader.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void supprimerConsultation() {
        listeConsultation.remove(this);
        this.getPatient().supprimerConsulation(this);
        initFichier();
    }


}
