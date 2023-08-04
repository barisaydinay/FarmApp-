package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class cowDetailsInterface extends JFrame {
    private JPanel cowDetailPanel;
    private JButton backToMenuButton;
    private JTextField textField1;
    private JPanel tagPanel;
    private JPanel displayPanel;
    private JTextArea textArea1;
    private JButton displayButton;

    public cowDetailsInterface(FarmApp myFarm) {
        add(cowDetailPanel);
        cowDetailPanel.setVisible(true);
        tagPanel.setVisible(true);
        displayPanel.setVisible(false);
        setSize(1000,800);
        setTitle("Farm App / Cow Details");

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
                displayPanel.setVisible(true);
                String s = textField1.getText();
                int tagno = Integer.parseInt(s);
                displayT(myFarm,tagno);
            }
        });
    }

    public void displayT(FarmApp myFarm, int tagNo) {

        int check = 0;
        for (int i = 0; i < myFarm.getAnimals().size(); i++) {
            if (tagNo == myFarm.getAnimals().get(i).getTagNo()) {
                try { // try-catch blocks in order to see whether the object can be type-casted, or not.
                    Cow c = (Cow) myFarm.getAnimals().get(i);
                    check = 1;
                    textArea1.setText(textArea1.getText() + "Tag No: " + c.getTagNo() + '\n' +
                            "Gender: " + c.getGender() + '\n' +
                            "Weight: " + c.getWeight() + '\n' +
                            "Purchased: " + c.getPurchased()+ '\n');


                    for (Object m : c.getMilking().keySet()) {
                        textArea1.setText(textArea1.getText() + "Date: " + m + " => Milk amount: " + c.getMilking().get(m)+ '\n');

                    }

                    textArea1.setText(textArea1.getText() + "Birth Date: " + c.getDateOfBirth().get(Calendar.DATE) + "/"
                            + c.getDateOfBirth().get(Calendar.MONTH) + "/" + c.getDateOfBirth().get(Calendar.YEAR)+ '\n');

                } catch (Exception e) {
                    displayPanel.setVisible(false);
                    JOptionPane.showMessageDialog(displayButton,"This tag no is not associated with cow!");
                    check=1;
                }


            }
        }
        if (check == 0) { displayPanel.setVisible(false);   JOptionPane.showMessageDialog(displayButton,"Cow Not Found!");   }



    }
}
