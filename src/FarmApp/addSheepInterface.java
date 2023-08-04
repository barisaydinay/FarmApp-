package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.HashMap;

public class addSheepInterface extends JFrame{
    private JPanel addSheepPanel;
    private JButton backToMenuButton;
    private JPanel addcowPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox checkBox1;
    private JTextField textField3;
    private JButton addButton;

    public addSheepInterface(FarmApp myFarm) {
        add(addSheepPanel);
        addSheepPanel.setVisible(true);
        setSize(800,800);
        setTitle("Farm App / Add Sheep");
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
                addS(myFarm);


            }
        });
    }

    public void addS(FarmApp myFarm) {

        String tagno = textField1.getText();
        int tagNo = Integer.parseInt(tagno);
        int flag = 1;


        for(int i=0;i< myFarm.getAnimals().size();i++) {
            if(myFarm.getAnimals().get(i).getTagNo() == tagNo) flag = 0;

        }



        String gender = textField2.getText();
        boolean purchased = checkBox1.isSelected();
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

        HashMap<String,Double> hMap = new HashMap<>();
        Sheep myCow = new Sheep(tagNo, gender, purchased, birthDate,hMap);

        if(flag == 1) { myFarm.getAnimals().add(myCow); JOptionPane.showMessageDialog(addButton,"Sheep Added"); }
        else JOptionPane.showMessageDialog(addButton,"Tag number already exists!");

        if(flag == 1) {

            try
            {

                String myDriver = "com.mysql.cj.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/FarmAppDB";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "cng443user", "1234");
                String query = " insert into Animal (tagNo, gender, dateOfBirth, purchased, type, Weight)" + " values (?, ?, ?, ?, ?,?)";

                PreparedStatement preparedStmt = conn.prepareStatement(query);

                preparedStmt.setInt(1,myCow.getTagNo());
                if(gender.equals("Female")) {preparedStmt.setString(2, "F");}
                else {preparedStmt.setString(2, "M");}

                int yearInt = birthDate.get(Calendar.YEAR);
                String yearString = Integer.toString(yearInt);
                int monthInt = birthDate.get(Calendar.MONTH);
                String monthString = Integer.toString(monthInt);
                int dateInt = birthDate.get(Calendar.DATE);
                String dateString = Integer.toString(dateInt);

                preparedStmt.setDate(3, Date.valueOf(yearString + "-" + monthString + "-" + dateString));
                if(purchased) {preparedStmt.setInt(4, 1);}
                else {preparedStmt.setInt(4, 0);}
                preparedStmt.setString(5, "S");
                preparedStmt.setInt(6, 0);

                preparedStmt.execute();

                conn.close();
            }
            catch (Exception e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }

            try {
                File myFile = new File("sheeps.dat");
                if(!myFile.exists()) myFile.createNewFile();
                ObjectOutputStream outCow = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("sheeps.dat",false)));

                for(Animal animal:myFarm.getAnimals()){
                    if(animal instanceof Sheep) {
                        outCow.writeObject(animal);
                    }
                }
                outCow.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            FileInputStream fis = null;
            try {
                fis = new FileInputStream("sheeps.dat");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int ch;
            while (true) {
                try {
                    if (!((ch = bis.read()) != -1)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                baos.write(ch);
            }

            byte buffer[] = baos.toByteArray();

            MessageDigest algorithm = null;
            try {
                algorithm = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            algorithm.reset();
            algorithm.update(buffer);
            byte digest[] = algorithm.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
                hexString.append(" ");
            }
            System.out.println("Sheep:" + hexString.toString());



            try {
                File myFile =  new File("securitySheep.txt");
                if(!myFile.exists()) {myFile.createNewFile();}


            } catch (IOException e) {
                System.out.println("exception security2 file!");
            }

            try {
                FileWriter wrt = new FileWriter("securitySheep.txt",false);
                wrt.write(hexString.toString());
                wrt.close();
            } catch (IOException e) {
                System.out.println("exception security write!");
            }



        }




    }
}
