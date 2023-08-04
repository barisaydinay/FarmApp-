package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class deleteCowInterface extends JFrame{
    private JButton backToMenuButton;
    private JTextField textField1;
    private JPanel deleteCowPanel;
    private JButton deleteButton;


    public deleteCowInterface(FarmApp myFarm) {
        add(deleteCowPanel);
        deleteCowPanel.setVisible(true);
        setSize(500,500);
        setTitle("Farm App / Delete Cow");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField1.getText();
                int tagno = Integer.parseInt(s);
                deleteC(myFarm,tagno);





            }
        });
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interface1 int1 = new interface1(myFarm);
                int1.setVisible(true);
                dispose();
            }
        });
    }


    public void deleteC(FarmApp myFarm,int tagNo) {
        int check = 0;
        int check2 = 0;
        for (int i = 0; i < myFarm.getAnimals().size(); i++) {
            if (tagNo == myFarm.getAnimals().get(i).getTagNo()) {
                if(myFarm.getAnimals().get(i) instanceof Sheep) {
                    check2 = 1;
                }
                else {
                    myFarm.getAnimals().remove(i);
                    check = 1;
                }
            }
        }
        if (check == 1) JOptionPane.showMessageDialog(deleteButton,"Cow deleted!");
        else if(check2 == 1) JOptionPane.showMessageDialog(deleteButton,"Tag Number does not belong to Cow!");
        else JOptionPane.showMessageDialog(deleteButton,"Animal does not exists!");

        if(check == 1) {


            try
            {

                String myDriver = "com.mysql.cj.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/FarmAppDB";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "cng443user", "1234");
                String query = "delete from Animal where tagNo = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, tagNo);
                preparedStmt.execute();
                conn.close();
            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }




            try {
                File myFile = new File("cows.dat");
                if(!myFile.exists()) myFile.createNewFile();
                ObjectOutputStream outCow = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("cows.dat",false)));

                for(Animal animal:myFarm.getAnimals()){
                    if(animal instanceof Cow) {
                        outCow.writeObject(animal);
                    }
                }
                outCow.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            FileInputStream fis = null;
            try {
                fis = new FileInputStream("cows.dat");
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



            try {
                File myFile =  new File("securityCow.txt");
                if(!myFile.exists()) {myFile.createNewFile();}


            } catch (IOException e) {
                System.out.println("exception security file!");
            }

            try {
                FileWriter wrt = new FileWriter("securityCow.txt",false);
                wrt.write(hexString.toString());
                wrt.close();
            } catch (IOException e) {
                System.out.println("exception security write!");
            }



        }



    }




}
