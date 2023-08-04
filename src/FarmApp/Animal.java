package FarmApp;
import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Abstract Animal Class
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */



public abstract class Animal implements Serializable {
    private int tagNo;
    private String gender;
    private Calendar dateOfBirth;
    private boolean purchased;
    private HashMap<String,Double> milking;

    private ArrayList<Treatment> treatments;

    /**
     * default constructor of animal
     */
    Animal() {
        this.tagNo = 0;
        this.gender = "not defined";
        Calendar time = Calendar.getInstance();
        time.set(0,0,0,0,0);
        this.dateOfBirth = time;
        HashMap<String,Double> hMap =new HashMap<>();
        this.milking = hMap;
        this.treatments = new ArrayList<Treatment>();

    }

    /**
     * constructor with parameters for animal
     * @param tagNo animal tagno
     * @param gender gender of animal
     * @param purchased purchased or not of animal
     * @param dateOfBirth birthdate o animal
     * @param milking milking hashMap for animal
     */
    Animal(int tagNo, String gender, boolean purchased, Calendar dateOfBirth, HashMap<String,Double> milking) {
        this.tagNo = tagNo;
        this.gender = gender;
        this.purchased = purchased;
        this.dateOfBirth = dateOfBirth;
        this.milking = milking;
        this.treatments = new ArrayList<Treatment>();

    }


    /**
     * get method for treatments
     * @return ArrayList: treatments
     */
    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    /**
     * get method for tagno
     * @return animals tag no.
     */
    public int getTagNo() {
        return tagNo;
    }

    /**
     * get method for animals gender
     * @return animals gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * get method for purchased information
     * @return boolean: purchased
     */
    public boolean getPurchased() {
        return this.purchased;
    }

    /**
     * get method for dateofbirth of animal
     * @return Calendar:dateofbirth
     */
    public Calendar getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * get method for milking hashMap
     * @return HashMap:milking
     */
    public HashMap getMilking() {
        return milking;
    }

    /**
     * Calculates the age of animal (LocalYear - animal's birth year)
     * @return int: age
     */
    public int getAge() {
        return (2022 - this.dateOfBirth.get(Calendar.YEAR));
    }

    /**
     * set method for tagno
     * @param tagno animals tagno
     */

    public void setTagNo(int tagno) {
        this.tagNo = tagno;
    }

    /**
     * set method for gender
     * @param gender animals gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * set method for purchased
     * @param p purchaed
     */
    public void setPurchased(boolean p) {
        this.purchased = p;
    }

    /**
     * set method for dateofbirth
     * @param dateOfBirth birthdate fo animal
     */
    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * set method for milking
     * @param localDate current year
     * @param amount milk amount
     */
    public void setMilking(String localDate,double amount) {
        milking.put(localDate,amount);
    }

    /**
     * abstract method that determines the feeding of animals
     *
     * @return
     */
    public abstract String feeding();  // abstract method


}
