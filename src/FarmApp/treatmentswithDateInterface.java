package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class treatmentswithDateInterface extends JFrame{
    private JPanel treatmentsMainPanel;
    private JPanel tagandDate;
    private JPanel displayTreatments;
    private JButton backToMenuButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JButton displayButton;

    public treatmentswithDateInterface(FarmApp myFarm) {
        add(treatmentsMainPanel);
        treatmentsMainPanel.setVisible(true);
        tagandDate.setVisible(true);
        displayTreatments.setVisible(false);
        setSize(1000,800);
        setTitle("Farm App / Cow Treatments");
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
                displayTreatments.setVisible(true);
                String s = textField1.getText();
                int tagno = Integer.parseInt(s);
                String str = textField2.getText();
                String dates[] = str.split("[ / ]");
                String yearS = dates[2];
                int year = Integer.parseInt(yearS);
                String monthS = dates[1];
                int month = Integer.parseInt(monthS);
                String dayS = dates[0];
                int day = Integer.parseInt(dayS);
                Calendar treatmentDate = Calendar.getInstance();
                treatmentDate.set(year, month, day, 0, 0);
                getT(myFarm,tagno,treatmentDate);
            }
        });
    }

    public void getT(FarmApp myFarm, int tagNo,Calendar treatmentDate) {

        int flag = 0;
        int cowFlag = 0;
        for (int i = 0; i < myFarm.getAnimals().size(); i++)
        {
            if (tagNo == myFarm.getAnimals().get(i).getTagNo())
            {
                try {
                    Cow c = (Cow) myFarm.getAnimals().get(i);
                    cowFlag = 1;

                    for (int j = 0; j < c.getTreatments().size(); j++) {
                        if (treatmentDate.get(Calendar.YEAR) == c.getTreatments().get(j).getDateOfTreatment().get(Calendar.YEAR)
                                && treatmentDate.get(Calendar.MONTH) == c.getTreatments().get(j).getDateOfTreatment().get(Calendar.MONTH)
                                && treatmentDate.get(Calendar.DATE) == c.getTreatments().get(j).getDateOfTreatment().get(Calendar.DATE)) {
                            flag = 1;

                            if (myFarm.getAnimals().get(i).getTreatments().get(j) instanceof CleaningTreatment) {


                                CleaningTreatment ct = (CleaningTreatment) myFarm.getAnimals().get(i).getTreatments().get(j);

                                //System.out.println("Materials used: " + ct.getMaterialsused());
                                textArea1.setText(textArea1.getText() + '\n' + "Materials used: " + ct.getMaterialsused() + '\n');

                                //System.out.println("Farm Worker associated with this treatment: " + ct.getResponsibleFarmworker().getEmpID());
                                textArea1.setText(textArea1.getText() + "Farm Worker associated with this treatment: " + ct.getResponsibleFarmworker().getEmpID() + '\n');
                                //System.out.println();
                            } else {

                                HealthTreatment ht = (HealthTreatment) myFarm.getAnimals().get(i).getTreatments().get(j);

                                //System.out.println("Emergency: " + ht.getEmergency());
                                textArea1.setText(textArea1.getText() + "Emergency: " + ht.getEmergency() + '\n');

                                //System.out.println("vetID associated with this treatment: " + ht.getResponsibleVet().getEmpID());
                                textArea1.setText(textArea1.getText() + "vetID associated with this treatment: " + ht.getResponsibleVet().getEmpID() + '\n');


                                for (int k = 0; k < ht.getMedications().size(); k++) {

                                    //System.out.println("Medication " + (k + 1) + " Detail: " + ht.getMedications().get(k).getDetails());
                                    textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Detail: " + ht.getMedications().get(k).getDetails() + '\n');

                                    //System.out.println("Medication " + (k + 1) + " Duration:" + ht.getMedications().get(k).getDuration());
                                    textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Duration:" + ht.getMedications().get(k).getDuration() + '\n');

                                    //System.out.println("Medication " + (k + 1) + " Dosage: " + ht.getMedications().get(k).getDosage());
                                    textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Dosage: " + ht.getMedications().get(k).getDosage() + '\n' );

                                    //System.out.println("Medication " + (k + 1) + " Start Date: " + ht.getMedications().get(k).getStartDate().get(Calendar.DATE)
                                      //      + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.MONTH)
                                        //    + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.YEAR));

                                    textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Start Date: " + ht.getMedications().get(k).getStartDate().get(Calendar.DATE)
                                            + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.MONTH)
                                            + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.YEAR) + '\n');

                                    //System.out.println("Medication " + (k + 1) + " Note: " + ht.getMedications().get(k).getNotes());
                                    textArea1.setText(textArea1.getText() + "Medication " + (k + 1) + " Note: " + ht.getMedications().get(k).getNotes() + '\n');

                                }

                            }

                            //System.out.println();
                        }


                    }
                } catch (Exception e) {
                    displayTreatments.setVisible(false);
                    JOptionPane.showMessageDialog(displayButton,"This tag no is not associated with cow!");
                    cowFlag = 1;
                    flag = 1;
                }


            }


        }
        if(cowFlag == 0) {displayTreatments.setVisible(false);   JOptionPane.showMessageDialog(displayButton,"Cow Not Found!"); }
        else if(flag == 0) {displayTreatments.setVisible(false);   JOptionPane.showMessageDialog(displayButton,"Treatment Not Found!");}


    }










}
