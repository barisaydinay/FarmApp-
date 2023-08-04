package FarmApp;
import java.io.Serializable;
import java.util.*;
import java.lang.String;

/**
 * Class for Medications used for a treatment
 * @author  Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public class Medication implements Serializable {
    private String details;
    private int duration;
    private Calendar startDate;
    private double dosage;
    private String notes;

    /**
     * Default Constructor for Medication
     */
    Medication() {
        this.details = "not provided";
        this.duration = 0;
        Calendar time = Calendar.getInstance();
        time.set(0,0,0,0,0);
        this.startDate = time;
        this.dosage = 0.0;
        this.notes = "not provided";
    }

    /**
     *Constructor with parameters for Medication
     * @param details details of medication
     * @param duration duration of medications
     * @param startDate start date of medication
     * @param dosage medication dosage
     * @param notes notes about the medication
     *
     */
    Medication(String details,int duration,Calendar startDate,double dosage,String notes) {
        this.details =details;
        this.duration = duration;
        this.startDate = startDate;
        this.dosage = dosage;
        this.notes = notes;
    }
    /**
     * Setting the medication details
     * @param details details of medication
     *
     */

    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * Setting the dosage of medication
     * @param dosage dosage of medication
     *
     */

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }
    /**
     * Setting the duration of medication
     * @param duration duration of medication
     *
     */

    public void setDuration(int duration) {
        this.duration = duration;
    }
    /**
     * Setting the notes for the medication
     * @param notes notes about the medication
     *
     */

    public void setNotes(String notes) {
        this.notes = notes;
    }
    /**
     * Setting the start date of medication
     * @param startDate Calendar: start date of medication
     *
     */

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    /**
     *Get method for start date of medication
     * @return Calendar: start date of medication
     *
     */

    public Calendar getStartDate() {
        return startDate;
    }
    /**
     *Get method for the dosage of medication
     * @return dosage of medication
     *
     */

    public double getDosage() {
        return dosage;
    }
    /**
     *Get method for duration of medication
     * @return duration of medication
     *
     */

    public int getDuration() {
        return duration;
    }
    /**
     *Get method for details of medication
     * @return details of medication
     *
     */

    public String getDetails() {
        return details;
    }
    /**
     *Get method for the notes of medication
     * @return notes about medication
     *
     */

    public String getNotes() {
        return notes;
    }


}
