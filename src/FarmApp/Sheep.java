package FarmApp;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Sheep (subclass of Animal)
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public class Sheep extends Animal implements Serializable {
    /**
     * default constructor of sheep
     */
    Sheep() {
        super();
    }

    /**
     * constructor with parameters for Sheep
     * @param tagNo tagno of sheep
     * @param gender gender of sheep
     * @param purchased purchased ino. of sheep
     * @param dateOfBirth birthdate of sheep
     * @param milking milking of sheep
     */
    public Sheep(int tagNo, String gender, boolean purchased, Calendar dateOfBirth, HashMap<String,Double> milking) {
        super(tagNo,gender,purchased,dateOfBirth,milking);
    }


    /**
     * Sheep's feeding conditions
     *
     * @return
     */
    public String feeding() {

        if((this.getGender().equals("Female") || this.getGender().equals("female") || this.getGender().equals("F")) && this.getAge() > 8)
            return "Total mixed ration (TMR) diet!";
        else if((this.getGender().equals("Male") || this.getGender().equals("male") || this.getGender().equals("M")) && this.getAge() > 5)
            return  "Total mixed ration (TMR) diet!";
        else if((this.getGender().equals("Female") || this.getGender().equals("female") || this.getGender().equals("F")) && this.getAge() < 8)
            return "Only Grass!";
        else if((this.getGender().equals("Male") || this.getGender().equals("male") || this.getGender().equals("M")) && this.getAge() < 5)
            return "Only Grass!";


        else return null;
    }





}
