package FarmApp;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Cow (subclass of Animal)
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public class Cow extends Animal implements Serializable {
    private double weight;

    /**
     * default constructor
     */
    Cow() {
        super();
        this.weight = 0;
    }

    /**
     * constructor with parameters for Cow
     * @param tagNo tagNo of cow
     * @param gender gender of cow
     * @param purchased purchaed info. of cow
     * @param dateOfBirth birthdate of cow
     * @param milking milking of cow
     * @param weight weight of cow
     */
    Cow(int tagNo, String gender, boolean purchased, Calendar dateOfBirth, HashMap<String,Double> milking,double weight)
    {
        super(tagNo,gender,purchased,dateOfBirth,milking);
        this.weight = weight;
    }


    /**
     * Calculates the weight of cow
     * @return double:weight of cow
     */
    public double getWeight() {
        return weight;
    }

    /**
     * sets the weight of cow
     * @param weight double:weight
     */

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * feeding conditions of cow
     *
     * @return
     */

    public String feeding() {

        if(this.getAge() > 10) return "Grass and grains!";
        else if(this.getAge() > 5 && this.getWeight() < 500) return "Total mixed ration (TMR) diet!";
        else if(this.getAge() < 3) return "Only Grass!";
        else return "Grass and grains!";

    }

}
