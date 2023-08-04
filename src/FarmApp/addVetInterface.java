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

public class addVetInterface extends JFrame{
    private JButton backToMenuButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JCheckBox checkBox1;
    private JPanel addVetPanel;
    private JButton addButton;

    public addVetInterface(FarmApp myFarm) {
        add(addVetPanel);
        addVetPanel.setVisible(true);
        setSize(650,650);
        setTitle("Farm App / Add Vet");
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
                addV(myFarm);
            }
        });
    }

    public void addV(FarmApp myFarm) {

        String vetIDS = textField1.getText();
        int vetID = Integer.parseInt(vetIDS);

        int flag = 1;


            for(int i=0;i< myFarm.getEmployee().size();i++) {
                if(myFarm.getEmployee().get(i).getEmpID() == vetID) {
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


        boolean degree = checkBox1.isSelected();


        String str2 = textField4.getText();
        String dates2[] = str2.split("[ / ]");
        String year2S = dates2[2];
        int year2 = Integer.parseInt(year2S);
        String month2S = dates2[1];
        int month2 = Integer.parseInt(month2S);
        String day2S = dates2[0];
        int day2 = Integer.parseInt(day2S);
        Calendar gradDate = Calendar.getInstance();
        gradDate.set(year2,month2,day2,0,0);


        String levelS = textField5.getText();
        int level = Integer.parseInt(levelS);

        if(flag == 1) {
            JOptionPane.showMessageDialog(addButton,"Employee (Vet) added!");
            Veterinary myVet = new Veterinary(vetID, gender, birthDate, degree, gradDate, level);
            myFarm.getEmployee().add(myVet);
        }
        else JOptionPane.showMessageDialog(addButton,"Employee ID already exists!");

        if(flag==1) {


            try
            {

                String myDriver = "com.mysql.cj.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/FarmAppDB";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "cng443user", "1234");
                String query = " insert into Employee (empID,gender,dateOfBirth,type,BScDegree,dateOfGraduation,expertiseLevel,previousFarmName,workExperience)" + " values (?,?,?,?,?,?,?,?,?)";


                PreparedStatement preparedStmt = conn.prepareStatement(query);

                preparedStmt.setInt(1,vetID);
                if(gender.equals("Female")) {preparedStmt.setString(2, "F");}
                else {preparedStmt.setString(2, "M");}

                int yearInt = birthDate.get(Calendar.YEAR);
                String yearString = Integer.toString(yearInt);
                int monthInt = birthDate.get(Calendar.MONTH);
                String monthString = Integer.toString(monthInt);
                int dateInt = birthDate.get(Calendar.DATE);
                String dateString = Integer.toString(dateInt);
                preparedStmt.setDate(3, Date.valueOf(yearString + "-" + monthString + "-" + dateString));

                preparedStmt.setString(4,"V");

                if(degree) {preparedStmt.setInt(5, 1);}
                else {preparedStmt.setInt(5, 0);}

                int yearInt2 = gradDate.get(Calendar.YEAR);
                String yearString2 = Integer.toString(yearInt2);
                int monthInt2 = gradDate.get(Calendar.MONTH);
                String monthString2 = Integer.toString(monthInt2);
                int dateInt2 = gradDate.get(Calendar.DATE);
                String dateString2 = Integer.toString(dateInt2);
                preparedStmt.setDate(6, Date.valueOf(yearString2 + "-" + monthString2 + "-" + dateString2));

                preparedStmt.setInt(7,level);
                preparedStmt.setString(8,"NULL");
                preparedStmt.setInt(9,0);

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
