package FarmApp;
import java.io.Serializable;
import java.util.*;
import java.lang.String;


/**
 * Treatment class (Animal's treatments)
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public class Treatment implements Serializable {
    private Calendar dateOfTreatment;

    /**
     * default constructor
     */
    Treatment() {
        this.dateOfTreatment = Calendar.getInstance();
        dateOfTreatment.set(0,0,0,0,0);
    }

    /**
     * constructor with parameters for treatment
     * @param dateOfTreatment date of treatment
     */
    Treatment(Calendar dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * get method for date of treatment
     * @return Calendar:dateoftreatment
     */
    public Calendar getDateOfTreatment() {
        return dateOfTreatment;
    }

    /**
     * set method for date of treatment
     * @param dateOfTreatment Calendar: dateoftreatment
     */

    public void setDateOfTreatment(Calendar dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;


    }




}
