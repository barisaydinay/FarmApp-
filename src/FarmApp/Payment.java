package FarmApp;
import java.io.Serializable;

/**
 * Interface implemented from Employee
 * @author  Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public interface Payment extends Serializable {
    /**
     * grossSalary = 10.000
     */
    double grossSalary = 10000;

    /**
     * Calculating Salary for Veterinary and Farmworker
     * @return double : Salary of Employee
     */
    double getSalary();

}
