package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class listVetInterface extends JFrame{
    private JButton backToMenuButton;
    private JTextArea textArea1;
    private JPanel listVetPanel;

    public listVetInterface(FarmApp myFarm) {
        add(listVetPanel);
        listVetPanel.setVisible(true);
        setSize(1000,800);
        setTitle("Farm App / Vets");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        listV(myFarm);




        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interface1 int1 = new interface1(myFarm);
                int1.setVisible(true);
                dispose();
            }
        });
    }

    public void listV(FarmApp myFarm) {

        for(int i=0;i< myFarm.getEmployee().size();i++) {
            if(myFarm.getEmployee().get(i) instanceof Veterinary) {
                Veterinary v = (Veterinary) myFarm.getEmployee().get(i);

                textArea1.setText(textArea1.getText() + '\n' + "Employee ID: " + v.getEmpID() + '\n' +
                        "Gender: " + v.getGender() + '\n' +
                        "Birth Date: " + v.getDateOfBirth().get(Calendar.DATE) + "/"
                        + v.getDateOfBirth().get(Calendar.MONTH) + "/" + v.getDateOfBirth().get(Calendar.YEAR) + '\n' +
                        "BScDegree: " + v.getBScDegree() + '\n' +
                        "Date of Graduation: " + v.getDateOfGraduation().get(Calendar.DATE) + "/"
                        + v.getDateOfGraduation().get(Calendar.MONTH) + "/" + v.getDateOfBirth().get(Calendar.YEAR) + '\n' +
                        "Expertise Level: " + v.getExpertiseLevel() + '\n');

            }

        }





















    }


}
