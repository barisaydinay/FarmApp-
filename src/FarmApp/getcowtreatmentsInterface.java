package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class getcowtreatmentsInterface extends JFrame{
    private JPanel getCowTreatmentsInterface;
    private JButton backToMenuButton;
    private JPanel tagGet;
    private JTextField textField1;
    private JPanel displayTreatment;
    private JButton displayButton;
    private JTextArea textArea1;

    public getcowtreatmentsInterface(FarmApp myFarm) {
        add(getCowTreatmentsInterface);
        getCowTreatmentsInterface.setVisible(true);
        tagGet.setVisible(true);
        displayTreatment.setVisible(false);
        setSize(1000,800);
        setTitle("Farm App / Cow Treatments");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(" ");
                displayTreatment.setVisible(true);
                String s = textField1.getText();
                int tagno = Integer.parseInt(s);
                getT(myFarm,tagno);


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

    public void getT(FarmApp myFarm,int tagNo) {

        int flag = 0;

        for (int i = 0; i < myFarm.getAnimals().size(); i++) {
            if (tagNo == myFarm.getAnimals().get(i).getTagNo()) {
                try { // try-catch blocks in order to see whether animal is Cow or not !
                    Cow c = (Cow) myFarm.getAnimals().get(i);
                    flag = 1;

                    for (int j = 0; j < c.getTreatments().size(); j++) {
                        textArea1.setText(textArea1.getText() + '\n' + "-- Treatment " + (j + 1) + " details given below --" + '\n');


                        textArea1.setText(textArea1.getText() + "Treatment Date: " + myFarm.getAnimals().get(i).getTreatments().get(j).getDateOfTreatment().get(Calendar.DATE)
                                + '/' + myFarm.getAnimals().get(i).getTreatments().get(j).getDateOfTreatment().get(Calendar.MONTH)
                                + '/' + myFarm.getAnimals().get(i).getTreatments().get(j).getDateOfTreatment().get(Calendar.YEAR) + '\n');

                        if (myFarm.getAnimals().get(i).getTreatments().get(j) instanceof CleaningTreatment) {
                            CleaningTreatment ct = (CleaningTreatment) myFarm.getAnimals().get(i).getTreatments().get(j);

                            textArea1.setText(textArea1.getText() + "Materials used: " + ct.getMaterialsused() + '\n');


                            textArea1.setText(textArea1.getText() + "Farm Worker associated with this treatment: " + ct.getResponsibleFarmworker().getEmpID() + '\n');


                            System.out.println();
                        } else {
                            HealthTreatment ht = (HealthTreatment) myFarm.getAnimals().get(i).getTreatments().get(j);

                            textArea1.setText(textArea1.getText() + "Emergency: " + ht.getEmergency() + '\n');
                            textArea1.setText(textArea1.getText() + "vetID associated with this treatment: " + ht.getResponsibleVet().getEmpID() + '\n');



                            for (int k = 0; k < ht.getMedications().size(); k++) {

                                textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Detail: " + ht.getMedications().get(k).getDetails() + '\n');
                                textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Duration:" + ht.getMedications().get(k).getDuration()+ '\n' );
                                textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Dosage: " + ht.getMedications().get(k).getDosage()+ '\n');
                                textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Start Date: " + ht.getMedications().get(k).getStartDate().get(Calendar.DATE)
                                        + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.MONTH)
                                        + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.YEAR)+ '\n');
                                textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Note: " + ht.getMedications().get(k).getNotes()+ '\n');


                            }
                            System.out.println();
                        }

                    }
                    break;
                } catch (Exception e) {
                    displayTreatment.setVisible(false);
                    JOptionPane.showMessageDialog(displayButton,"This tag no is not associated with cow!");
                    flag = 1;
                }

            }
        }

        if (flag == 0) { displayTreatment.setVisible(false); JOptionPane.showMessageDialog(displayButton,"Cow Not Found!");   }



    }


}
