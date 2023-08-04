package FarmApp;
import java.io.Serializable;
import java.util.*;
import java.lang.String;


/**
 * CleaningTreatment(subclass of treatment)
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */


public class CleaningTreatment extends Treatment implements Serializable {
    private String materialsused;
    private Employee responsibleFarmworker;

    /**
     * default constructor
     */
    CleaningTreatment() {
        super();
        this.materialsused = "not defined";
        Employee emp = new FarmWorker();
        this.responsibleFarmworker = emp;
    }

    /**
     * constructors with parameters for cleaningTreatment
     * @param dateOfTreatment cleaningTreatment's date
     * @param materialsused used materials
     * @param responsibleFarmworker responsible farmWorkers for Cleaning treatments.
     */
    CleaningTreatment(Calendar dateOfTreatment, String materialsused,Employee responsibleFarmworker) {
        super(dateOfTreatment);
        this.materialsused = materialsused;
        this.responsibleFarmworker = responsibleFarmworker;

    }

    /**
     * get method for used materials
     * @return String : materialused
     */
    public String getMaterialsused() {
        return materialsused;
    }

    /**
     * set method for used materials
     * @param materialsused used materials
     */

    public void setMaterialsused(String materialsused) {
        this.materialsused = materialsused;
    }

    /**
     * set method for responsible farmWorkers
     * @param responsibleFarmworker FarmWorker: responsible farmworker
     */

    public void setResponsibleFarmworker(FarmWorker responsibleFarmworker) {
        this.responsibleFarmworker = responsibleFarmworker;
    }

    /**
     * get method for responsible farmworker
     * @return Employee:ResponsibleFarmwroker
     */

    public Employee getResponsibleFarmworker() {
        return this.responsibleFarmworker;
    }





}
