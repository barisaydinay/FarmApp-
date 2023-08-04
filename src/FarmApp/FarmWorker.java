package FarmApp;
import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;

/**
 * FarmWorker (subclass of employee)
 * Class interacts with the user
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public class FarmWorker extends Employee implements Serializable {
    private String previousFarmName;
    private int workexperience;

    /**
     * default constructor of farmWorker
     */
    FarmWorker() {
        super();
        this.previousFarmName = "not defined";
        this.workexperience = 0;
    }

    /**
     * Constructor with parameters for farmworker.
     * @param empID farmworker id.
     * @param gender gender of farmworker.
     * @param dateOfBirth birthdate of farmworker.
     * @param previousFarmName farmworker's previos farm's name.
     * @param workexperience farmworker's experince level.
     */
    FarmWorker(int empID, String gender, Calendar dateOfBirth,String previousFarmName,int workexperience)
    {
        super(empID,gender,dateOfBirth);
        this.previousFarmName = previousFarmName;
        this.workexperience = workexperience;
    }

    /**
     * get method for previous farm name
     * @return String: previous farm name
     */
    public String getPreviousFarmName() {
        return previousFarmName;
    }

    /**
     * get method for work experience
     * @return int : workexperience
     */
    public int getWorkexperience() {
        return workexperience;
    }

    /**
     * set method for previous farm name
     * @param previousFarmName farmworker's prev. farm name
     */

    public void setPreviousFarmName(String previousFarmName) {
        this.previousFarmName = previousFarmName;
    }

    /**
     * set method for work experience
     * @param workexperience work experience of farmworker
     */

    public void setWorkexperience(int workexperience) {
        this.workexperience = workexperience;
    }


}
