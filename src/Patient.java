import java.util.ArrayList;

public class Patient {

    // Attributs

    private int id;
    private String nom;
    private ArrayList<Consultation> listeConsultationPatient;
    int cb = 0;

    // Constructeur

    public Patient(String nom) {
        cb++;
        this.id = cb;
        this.nom = nom;
        this.listeConsultationPatient = new ArrayList<Consultation>();
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    // Methodes


}
