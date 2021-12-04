import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Consultation {

    private int id;
    private int date;
    private String antecedents;
    private Patient patient;

    ArrayList<Consultation> listeConsultation = new ArrayList<Consultation>();

    public Consultation(int id, int date, String antecedents, Patient patient) {
        this.id = id;
        this.date = date;
        this.antecedents = antecedents;
        this.patient = patient;
    }

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
}
