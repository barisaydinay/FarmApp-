package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class milkingInterface extends JFrame{
    private JPanel milkingPanel;
    private JButton backToMenuButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton saveMilkingButton;

    public milkingInterface(FarmApp myFarm) {
        add(milkingPanel);
        milkingPanel.setVisible(true);
        setSize(500,500);
        setTitle("Farm App / Milking");
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
        saveMilkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tagNoS = textField1.getText();
                int tagNo = Integer.parseInt(tagNoS);
                String milkS = textField2.getText();
                double milk = Double.parseDouble(milkS);
                addMilking(myFarm,tagNo,milk);


            }
        });
    }


    public void addMilking(FarmApp myFarm,int tagNo,double amount) {

        LocalDate date = LocalDate.now();
        int flag = 0;
        int flag2 = 0;
        int index = -1;


        for(int i = 0; i < myFarm.getAnimals().size(); i++) {

            if(tagNo == myFarm.getAnimals().get(i).getTagNo()) {

                index = i;
                for (Object m : myFarm.getAnimals().get(i).getMilking().keySet()) {
                    if (m.equals(String.valueOf(date)))
                    { flag2 = 1; JOptionPane.showMessageDialog(saveMilkingButton,"You can not store multiple milk measurements at the same day!"); }
                }

                flag = 1;
                if(flag2 == 0) myFarm.getAnimals().get(i).setMilking(String.valueOf(date),amount);

            }

        }
        if(flag==0) {JOptionPane.showMessageDialog(saveMilkingButton,"Animal Not Found!");}
        else if(flag2 != 1) {JOptionPane.showMessageDialog(saveMilkingButton,"Milking measurement saved!");}

        if(flag2 != 1 && flag != 0) {

            if(myFarm.getAnimals().get(index) instanceof Cow) {
                try {
                    ObjectOutputStream outCow = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("cow.dat", false)));

                    for (Animal animal : myFarm.getAnimals()) {
                        if (animal instanceof Cow) {
                            outCow.writeObject(animal);
                        }
                    }
                    outCow.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            else {
                try {
                    ObjectOutputStream outCow = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("sheep.dat",false)));

                    for(Animal animal:myFarm.getAnimals()){
                        if(animal instanceof Sheep) {
                            outCow.writeObject(animal);
                        }
                    }
                    outCow.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }




        }

    }



}
