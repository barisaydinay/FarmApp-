package FarmApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Data Storage class for mySQL
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */
public class DataStorage {

    /**
     * Fetches animal data from mySQL and save it to program everytime program terminates
     * @param myFarm includes all objects in farm
     */
    public void populateAnimals(FarmApp myFarm) {
        String userName = "cng443user";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/FarmAppDB";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);


            Statement statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("select * from Animal");

            while (resultSet.next())
            {

                String AnimalType = resultSet.getString("type");
                if(AnimalType.equals("C")) {
                    int tagNo = resultSet.getInt("tagNo");
                    String gender = resultSet.getString("gender");
                    Calendar dateOfBirth = Calendar.getInstance();
                    Date dateofbirth = resultSet.getDate("dateOfBirth");
                    dateOfBirth.setTime(dateofbirth);
                    int purchasedTinyInt = resultSet.getInt("purchased");
                    boolean purchased;
                    if(purchasedTinyInt == 1) purchased = true; else purchased = false;
                    double weight = resultSet.getDouble("Weight");
                    HashMap<String,Double> hMap =new HashMap<>();
                    Cow myCow = new Cow(tagNo,gender,purchased,dateOfBirth,hMap,weight);
                    myFarm.getAnimals().add(myCow);
                }
                else {
                    int tagNo = resultSet.getInt("tagNo");
                    String gender = resultSet.getString("gender");
                    Calendar dateOfBirth = Calendar.getInstance();
                    Date dateofbirth = resultSet.getDate("dateOfBirth");
                    dateOfBirth.setTime(dateofbirth);
                    int purchasedTinyInt = resultSet.getInt("purchased");
                    boolean purchased;
                    if(purchasedTinyInt == 1) purchased = true; else purchased = false;
                    HashMap<String,Double> hMap =new HashMap<>();
                    Sheep mySheep = new Sheep(tagNo,gender,purchased,dateOfBirth,hMap);
                    myFarm.getAnimals().add(mySheep);

                }


            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    /**
     * Fetches employee data from mySQL and save it to program everytime program terminates
     * @param myFarm includes all objects in farm
     */
    public void populateEmployee(FarmApp myFarm) {
        String userName = "cng443user";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/FarmAppDB";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);


            Statement statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("select * from Employee");

            while (resultSet.next())
            {

                String EmployeeType = resultSet.getString("type");
                if(EmployeeType.equals("V")) {
                    int empID = resultSet.getInt("empID");
                    String gender = resultSet.getString("gender");
                    Calendar dateOfBirth = Calendar.getInstance();
                    Date dateofbirth = resultSet.getDate("dateOfBirth");
                    dateOfBirth.setTime(dateofbirth);
                    boolean degree = resultSet.getBoolean("BScDegree");
                    Calendar dateOfGrad = Calendar.getInstance();
                    Date dateofgrad = resultSet.getDate("dateOfGraduation");
                    int level = resultSet.getInt("expertiseLevel");
                    Veterinary myVet = new Veterinary(empID,gender,dateOfBirth,degree,dateOfGrad,level);
                    myFarm.getEmployee().add(myVet);

                }
                else {
                    int empID = resultSet.getInt("empID");
                    String gender = resultSet.getString("gender");
                    Calendar dateOfBirth = Calendar.getInstance();
                    Date dateofbirth = resultSet.getDate("dateOfBirth");
                    dateOfBirth.setTime(dateofbirth);
                    String farmName = resultSet.getString("previousFarmName");
                    int experience = resultSet.getInt("workExperience");
                    FarmWorker myFW = new FarmWorker(empID,gender,dateOfBirth,farmName,experience);
                    myFarm.getEmployee().add(myFW);
                }


            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


}
