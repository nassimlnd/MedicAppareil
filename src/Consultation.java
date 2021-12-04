import java.util.ArrayList;

public class Consultation {

    // Attributs

    private int id;
    private int date;
    private String antecedents;
    private Patient patient;
    int cb = 0;

    ArrayList<Consultation> listeConsultation = new ArrayList<Consultation>();

    // Constructeurs

    public Consultation(int date, String antecedents, Patient patient) {
        cb++;
        this.id = cb;
        this.date = date;
        this.antecedents = antecedents;
        this.patient = patient;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    // Methodes


}
