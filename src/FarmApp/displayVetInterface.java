package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class displayVetInterface extends JFrame{
    private JPanel displayVetPanel;
    private JPanel tagPanel;
    private JPanel detailsPanel;
    private JButton backToMenuButton;
    private JTextField textField1;
    private JButton displayButton;
    private JTextArea textArea1;

    public displayVetInterface(FarmApp myFarm) {
        add(displayVetPanel);
        displayVetPanel.setVisible(true);
        tagPanel.setVisible(true);
        detailsPanel.setVisible(false);
        setSize(700,700);
        setTitle("Farm App / Vet Details");

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
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(" ");
                detailsPanel.setVisible(true);
                String s = textField1.getText();
                int vetID = Integer.parseInt(s);
                displayV(myFarm,vetID);
            }
        });
    }

    public void displayV(FarmApp myFarm,int vetID) {

        int check = 0,flag=0;
        for (int i = 0; i < myFarm.getEmployee().size(); i++) {
            if (vetID == myFarm.getEmployee().get(i).getEmpID()) {
                try { // try-catch block in order to see , if employee is Vet or not. Exception occurs if program can not type-cast.
                    Veterinary v = (Veterinary) myFarm.getEmployee().get(i);

                    check = 1;
                    textArea1.setText(textArea1.getText() + '\n' + "Employee ID: " + v.getEmpID() + '\n' +
                            "Gender: " + v.getGender() + '\n' +
                            "Birth Date: " + v.getDateOfBirth().get(Calendar.DATE) + "/"
                            + v.getDateOfBirth().get(Calendar.MONTH) + "/" + v.getDateOfBirth().get(Calendar.YEAR) + '\n' +
                            "BScDegree: " + v.getBScDegree() + '\n' +
                            "Date of Graduation: " + v.getDateOfGraduation().get(Calendar.DATE) + "/"
                            + v.getDateOfGraduation().get(Calendar.MONTH) + "/" + v.getDateOfGraduation().get(Calendar.YEAR) + '\n' +
                            "Expertise Level: " + v.getExpertiseLevel() + '\n');

                } catch (Exception e) {
                    flag = 1;
                }
            }
        }
        if (check == 0 && flag ==0 ) { detailsPanel.setVisible(false);  JOptionPane.showMessageDialog(displayButton,"Vet does not exists!"); }
        if(flag == 1) { detailsPanel.setVisible(false);
            JOptionPane.showMessageDialog(displayButton, "Your input: " + vetID + " is not a Vet, this employee is farm worker. Please check your employee again!");
        }


    }
}
