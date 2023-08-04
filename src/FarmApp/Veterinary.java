package FarmApp;
import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;

/**
 * Veterinary (subclass of employee)
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */


public class Veterinary extends Employee implements Serializable {

    private boolean BScDegree;
    private Calendar dateOfGraduation;
    private int expertiseLevel;

    /**
     * default constructor of veterinary
     */
    Veterinary(){
        super();
        this.BScDegree = false;
        Calendar time = Calendar.getInstance();
        time.set(0,0,0,0,0);
        this.dateOfGraduation = time;
        this.expertiseLevel = 0;

    }

    /**
     * Constructor with parameters for Veterinary.
     * @param empID vetID
     * @param gender gender of vet
     * @param dateOfBirth birthdate of vet
     * @param BScDegree degree of vet
     * @param dateOfGraduation graduation date of vet
     * @param expertiseLevel expertise level of vet
     */
    Veterinary(int empID, String gender, Calendar dateOfBirth, boolean BScDegree,Calendar dateOfGraduation, int expertiseLevel)
    {
        super(empID,gender,dateOfBirth);
        this.BScDegree = BScDegree;
        this.dateOfGraduation = dateOfGraduation;
        this.expertiseLevel = expertiseLevel;
    }

    /**
     * get method for vet's bscDegree
     * @return true/false
     */
    public boolean getBScDegree() {
        return BScDegree;
    }

    /**
     * get method for vet's grad. date
     * @return Calendar: dateofgraduation
     */
    public Calendar getDateOfGraduation() {
        return dateOfGraduation;
    }

    /**
     * get method for vet's expertise level
     * @return int: expertiseLevel
     */
    public int getExpertiseLevel() {
        return expertiseLevel;
    }

    /**
     * set method for vet's bscdegree
     * @param BScDegree if vet has bscDegree it is true else it is false.
     */

    public void setBScDegree(boolean BScDegree) {
        this.BScDegree = BScDegree;
    }

    /**
     * set method for vet's graduation date.
     * @param dateOfGraduation grad. date of vet.
     */

    public void setDateOfGraduation(Calendar dateOfGraduation) {
        this.dateOfGraduation = dateOfGraduation;
    }

    /**
     * set method for vet's expertise level.
     * @param expertiseLevel Vet's expertise level.
     */

    public void setExpertiseLevel(int expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }




}
