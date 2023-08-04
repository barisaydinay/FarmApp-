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
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class deleteVetInterface extends JFrame{
    private JPanel deleteVetPanel;
    private JButton backToMenuButton;
    private JTextField textField1;
    private JButton deleteButton;

    public deleteVetInterface(FarmApp myFarm) {
        add(deleteVetPanel);
        deleteVetPanel.setVisible(true);
        setSize(500,500);
        setTitle("Farm App / Delete Vet");

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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vetIDS = textField1.getText();
                int vetID = Integer.parseInt(vetIDS);
                deleteV(myFarm,vetID);


            }
        });
    }

    public void deleteV(FarmApp myFarm,int vetID) {

        int check = 0;
        for (int i = 0; i < myFarm.getEmployee().size(); i++) {
            if (vetID == myFarm.getEmployee().get(i).getEmpID()) {
                myFarm.getEmployee().remove(i);
                check = 1;
            }
        }

        if (check == 1) JOptionPane.showMessageDialog(deleteButton,"Employee deleted!");
        else JOptionPane.showMessageDialog(deleteButton,"Employee does not exists!");

        if(check==1) {

            try
            {
                String myDriver = "com.mysql.cj.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/FarmAppDB";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "cng443user", "1234");
                String query = "delete from Employee where empID = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, vetID);
                preparedStmt.execute();

                conn.close();
            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }



        }


    }


}
