package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;

public class addFWInterface extends JFrame{
    private JButton backToMenuButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JPanel addFWPanel;
    private JButton addButton;

    public addFWInterface(FarmApp myFarm) {
        add(addFWPanel);
        addFWPanel.setVisible(true);
        setSize(650,650);
        setTitle("Farm App / Add FarmWorker");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interface1 int1 = new interface1(myFarm);
                int1.setVisible(true);
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFW(myFarm);
            }
        });
    }

    public void addFW(FarmApp myFarm) {

        String farmWorkerIDS = textField1.getText();
        int farmWorkerID = Integer.parseInt(farmWorkerIDS);
        int flag = 1;

            for(int i=0;i< myFarm.getEmployee().size();i++) {
                if(myFarm.getEmployee().get(i).getEmpID() == farmWorkerID) {
                    flag = 0;
                }
            }

        String gender = textField2.getText();
        String str = textField3.getText();
        String dates[] = str.split("[ / ]");
        String yearS = dates[2];
        int year = Integer.parseInt(yearS);
        String monthS = dates[1];
        int month = Integer.parseInt(monthS);
        String dayS = dates[0];
        int day = Integer.parseInt(dayS);
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day, 0, 0);
        String previousFarm = textField4.getText();
        int experience = comboBox1.getSelectedIndex();

        if(flag == 1) {
            FarmWorker myFarmWorker = new FarmWorker(farmWorkerID, gender, birthDate, previousFarm, experience);
            myFarm.getEmployee().add(myFarmWorker);
            JOptionPane.showMessageDialog(addButton,"Employee (FarmWorker) added!");
        }
        else JOptionPane.showMessageDialog(addButton,"Employee ID already exists!");

        if(flag == 1) {


            try
            {

                String myDriver = "com.mysql.cj.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/FarmAppDB";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "cng443user", "1234");
                String query = " insert into Employee (empID,gender,dateOfBirth,type,BScDegree,dateOfGraduation,expertiseLevel,previousFarmName,workExperience)" + " values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                preparedStmt.setInt(1,farmWorkerID);
                if(gender.equals("Female")) {preparedStmt.setString(2, "F");}
                else {preparedStmt.setString(2, "M");}

                int yearInt = birthDate.get(Calendar.YEAR);
                String yearString = Integer.toString(yearInt);
                int monthInt = birthDate.get(Calendar.MONTH);
                String monthString = Integer.toString(monthInt);
                int dateInt = birthDate.get(Calendar.DATE);
                String dateString = Integer.toString(dateInt);
                preparedStmt.setDate(3, Date.valueOf(yearString + "-" + monthString + "-" + dateString));

                preparedStmt.setString(4,"F");

                preparedStmt.setInt(5,0);

                preparedStmt.setDate(6, Date.valueOf("0001-01-01"));

                preparedStmt.setInt(7,0);
                preparedStmt.setString(8,previousFarm);
                preparedStmt.setInt(9,experience);

                preparedStmt.execute();

                conn.close();
            }
            catch (Exception e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }



        }





    }

}
