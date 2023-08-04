package FarmApp;
import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.lang.*;

/**
 * Abstract Employee Class
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */


public abstract class Employee implements Payment,Comparable<Employee> , Serializable {
    private int empID;
    private String gender;
    private Calendar dateOfBirth;

    /**
     * Default constructor of employee
     */
    Employee() {
        this.empID = 0;
        this.gender = "not defined";
        Calendar empty = Calendar.getInstance();
        empty.set(0,0,0,0,0);

    }

    /**
     * Constructor with parameters for Employee
     * @param empID employee's unique ID
     * @param gender gender of employee
     * @param dateOfBirth birthdate of employee
     */
    Employee(int empID,String gender,Calendar dateOfBirth) {
        this.empID = empID;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * CompareTo method that provided from Payment interface, compares salaries of any type of two employees.
     * @param e the object to be compared.
     * @return integer value
     */
    public int compareTo(Employee e) { // Compares salaries of any type of two employees

        if(this instanceof Veterinary) {
            Veterinary v = (Veterinary) this;
            if(e instanceof Veterinary) {
                Veterinary v2 = (Veterinary) e;
                if(v.getSalary() == v2.getSalary()) return 0;
                else if(v.getSalary() < v2.getSalary()) return 1;
                else return -1;
            }
            else {
                FarmWorker fw = (FarmWorker) e;
                if(v.getSalary() == fw.getSalary()) return 0;
                else if(v.getSalary() < fw.getSalary()) return 1;
                else return -1;
            }
        }
        else {
            FarmWorker fw = (FarmWorker) this;
            if(e instanceof Veterinary) {
                Veterinary v2 = (Veterinary) e;
                if(fw.getSalary() == v2.getSalary()) return 0;
                else if(fw.getSalary() < v2.getSalary()) return 1;
                else return -1;
            }
            else {
                FarmWorker fw2 = (FarmWorker) e;
                if(fw.getSalary() == fw2.getSalary()) return 0;
                else if(fw.getSalary() < fw2.getSalary()) return 1;
                else return -1;
            }

        }

    }


    /**
     * Returns Payment.GrossSalary
     * @return grossSalary
     */
    public double getSalary() {
        return Payment.grossSalary;
    }

    /**
     *Get method for vetID
     * @return empID
     *
     */
    public int getEmpID() {
        return empID;
    }
    /**
     *Get method for gender
     * @return gender
     *
     */
    public String getGender() {
        return gender;
    }
    /**
     *Get method for Birth Date of employee
     * @return Calendar: Date of Birth
     *
     */
    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * Setting the birthdate of employee
     * @param dateOfBirth birth date of employee
     *
     */
    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    /**
     *Setting the employee's ID
     * @param empID employee's ID
     *
     *
     */
    public void setEmpID(int empID) {
        this.empID = empID;
    }

    /**
     * Setting the employee's gender
     * @param gender gender of employee
     *
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


}
