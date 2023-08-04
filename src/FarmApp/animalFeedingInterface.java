package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class animalFeedingInterface extends JFrame{
    private JPanel feedingPanel;
    private JButton backToMenuButton;
    private JTextField textField1;
    private JButton displayFeedingButton;

    public animalFeedingInterface(FarmApp myFarm) {
        add(feedingPanel);
        feedingPanel.setVisible(true);
        setSize(500,500);
        setTitle("Farm App / Animal Feeding");
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
        displayFeedingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tagNoS = textField1.getText();
                int tagNo = Integer.parseInt(tagNoS);
                animalFeeding(myFarm,tagNo);


            }
        });
    }

    public void animalFeeding(FarmApp myFarm, int tagNo) {

        int flag = 0;
        int index = -1;
        for(int i = 0; i < myFarm.getAnimals().size(); i++) {
            if(tagNo == myFarm.getAnimals().get(i).getTagNo()) {
                flag = 1;
                index = i;
            }
        }
        if(flag == 1) {
            JOptionPane.showMessageDialog(displayFeedingButton,myFarm.getAnimals().get(index).feeding());
        }
        else JOptionPane.showMessageDialog(displayFeedingButton,"Animal Not Found!");


    }

}
