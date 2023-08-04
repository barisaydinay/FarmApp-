package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class listCowInterface extends JFrame{
    private JPanel listCowPanel;
    private JButton backToMenu;
    private JTextArea CowListTextArea;

    public listCowInterface(FarmApp myFarm) {
        add(listCowPanel);
        listCowPanel.setVisible(true);
        setSize(1000,800);
        setTitle("Farm App / Cows");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        listC(myFarm);




        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interface1 int1 = new interface1(myFarm);
                int1.setVisible(true);
                dispose();

            }
        });
    }



    public void listC(FarmApp myFarm) {


            for(int i =0;i<myFarm.getAnimals().size();i++) {
                if(myFarm.getAnimals().get(i) instanceof Cow) {
                    Cow c = (Cow) myFarm.getAnimals().get(i);



                    CowListTextArea.setText(CowListTextArea.getText() + "Tag No: " + c.getTagNo() + '\n' +
                            "Gender: " + c.getGender() + '\n' +
                            "Weight: " + c.getWeight() + '\n' +
                            "Purchased: " + c.getPurchased() + '\n');


                    for (Object m : c.getMilking().keySet()) {
                        CowListTextArea.setText(CowListTextArea.getText() + "Date: " + m + " => Milk amount: " + c.getMilking().get(m) + '\n');
                    }


                    CowListTextArea.setText(CowListTextArea.getText() + "Birth Date: " + c.getDateOfBirth().get(Calendar.DATE) + "/"
                            + c.getDateOfBirth().get(Calendar.MONTH) + "/" + c.getDateOfBirth().get(Calendar.YEAR) + '\n' + '\n');


                }
            }




    }




}
