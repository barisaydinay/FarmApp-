package FarmApp;
import java.io.Serializable;
import java.util.*;
import java.lang.String;

/**
 * HealthTreatment(sublass of Treatment)
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public class HealthTreatment extends Treatment implements Serializable {
    private boolean emergency;
    private Employee responsibleVet;
    private ArrayList<Medication> medications;

    /**
     * default constructor
     */
    HealthTreatment() {
        super();
        this.emergency = false;
        Employee emp = new Veterinary();
        this.responsibleVet = emp;
        this.medications = new ArrayList<Medication>();

    }

    /**
     * constructor with parameters for HealthTreatment
     * @param dateOfTreatment treatment date
     * @param emergency Boolean:emergency
     * @param responsibleVet responsible veterinary
     */
    HealthTreatment(Calendar dateOfTreatment, boolean emergency, Employee responsibleVet) {
        super(dateOfTreatment);
        this.emergency = emergency;
        this.responsibleVet = responsibleVet;
        this.medications = new ArrayList<Medication>();
    }

    /**
     * get method for emergency
     * @return boolean:emergency
     */
    public boolean getEmergency() {
        return emergency;
    }

    /**
     * set method for emergency
     * @param emergency emergency
     */

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    /**
     * set method for responsible vet
     * @param responsibleVet Veterinary:responsible vet
     */

    public void setResponsibleVet(Veterinary responsibleVet) {
        this.responsibleVet = responsibleVet;
    }

    /**
     * get method for responsbile vet
     * @return Employee:responsibleVet
     */

    public Employee getResponsibleVet() {
        return this.responsibleVet;
    }

    /**
     * get method for medications
     * @return ArrayList: medications
     */

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    /**
     * set method for medications
     * @param medications ArrayList:medications of HealthTreatment
     */

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }
}
