package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

public class addTreatmentInterface extends JFrame{
    private JPanel addTreatmentPanel;
    private JButton backToMenuButton;
    private JComboBox comboBox1;
    private JPanel typePanel;
    private JPanel healthPanel;
    private JPanel CleaningPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JButton saveTreatmentButton1;
    private JButton addButton;
    private JCheckBox checkBox2;
    private JButton saveTreatmentButton;

    public addTreatmentInterface(FarmApp myFarm) {
        add(addTreatmentPanel);
        addTreatmentPanel.setVisible(true);
        typePanel.setVisible(true);
        healthPanel.setVisible(false);
        CleaningPanel.setVisible(false);
        setSize(1000,1000);
        setTitle("Farm App / Add Treatment");
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int type = comboBox1.getSelectedIndex();
                if(type == 1) {
                    add(healthPanel,BorderLayout.SOUTH); CleaningPanel.setVisible(false); healthPanel.setVisible(true); }
                else if(type == 2) {
                    add(CleaningPanel,BorderLayout.SOUTH); healthPanel.setVisible(false); CleaningPanel.setVisible(true);  }

            }
        });
        saveTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHealthT(myFarm);

            }
        });
        saveTreatmentButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCleaningT(myFarm);
            }
        });
    }

    public void addCleaningT(FarmApp myFarm) {

        int myFlag = 1;
        String empIDS = textField9.getText();
        int empID = Integer.parseInt(empIDS);
        String tagNoS = textField10.getText();
        int tagNo = Integer.parseInt(tagNoS);

        int flag = 0;
        int flag2 = 0;

        for(int i=0;i<myFarm.getAnimals().size();i++) {

            if(myFarm.getAnimals().get(i).getTagNo() == tagNo) {
                flag = 1;


                for(int k=0;k<myFarm.getEmployee().size();k++) {
                    if(myFarm.getEmployee().get(k).getEmpID() == empID) {
                        flag2 = 1;

                        String str = textField11.getText();

                        String dates[] = str.split("[ / ]");
                        String yearS = dates[2];
                        int year = Integer.parseInt(yearS);
                        String monthS = dates[1];
                        int month = Integer.parseInt(monthS);
                        String dayS = dates[0];
                        int day = Integer.parseInt(dayS);
                        Calendar treatmentDate = Calendar.getInstance();
                        treatmentDate.set(year, month, day, 0, 0);


                            try {
                                FarmWorker fw = (FarmWorker) myFarm.getEmployee().get(k);

                                String str2 = textField12.getText();
                                CleaningTreatment cleaningTreatment = new CleaningTreatment(treatmentDate, str2, fw);
                                myFarm.getAnimals().get(i).getTreatments().add(cleaningTreatment);
                            }
                            catch (Exception e) {
                                myFlag = -1;
                                JOptionPane.showMessageDialog(saveTreatmentButton,"Employee type does not match with treatment!");
                                interface1 int1 = new interface1(myFarm);
                                int1.setVisible(true);
                                dispose();

                            }

                    }
                }


            }
        }

        if(flag == 0)  {  JOptionPane.showMessageDialog(saveTreatmentButton,"Animal does not exists!");
            interface1 int1 = new interface1(myFarm);
            int1.setVisible(true);
            dispose(); }

        else if(flag2 == 0) {  JOptionPane.showMessageDialog(saveTreatmentButton,"Employee does not exists!");
            interface1 int1 = new interface1(myFarm);
            int1.setVisible(true);
            dispose(); }

        else if(myFlag == 1) {
            JOptionPane.showMessageDialog(saveTreatmentButton,"Cleaning Treatment Saved!");
            interface1 int1 = new interface1(myFarm);
            int1.setVisible(true);
            dispose();
        }

        if(myFlag == 1) {
            try {
                ObjectOutputStream outCow = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("cow.dat",false)));

                for(Animal animal:myFarm.getAnimals()){
                    if(animal instanceof Cow) {
                        outCow.writeObject(animal);
                    }
                }
                outCow.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }



    public void addHealthT(FarmApp myFarm) {

        int myFlag = 1;
        String empIDS = textField1.getText();
        int empID = Integer.parseInt(empIDS);
        String tagNoS = textField2.getText();
        int tagNo = Integer.parseInt(tagNoS);

        int flag = 0;
        int flag2 = 0;

        for(int i=0;i<myFarm.getAnimals().size();i++) {

            if(myFarm.getAnimals().get(i).getTagNo() == tagNo) {
                flag = 1;


                for(int k=0;k<myFarm.getEmployee().size();k++) {

                    if(myFarm.getEmployee().get(k).getEmpID() == empID) {
                        flag2 = 1;


                        String str  = textField3.getText();
                        String dates[] = str.split("[ / ]");
                        String yearS = dates[2];
                        int year = Integer.parseInt(yearS);
                        String monthS = dates[1];
                        int month = Integer.parseInt(monthS);
                        String dayS = dates[0];
                        int day = Integer.parseInt(dayS);
                        Calendar treatmentDate = Calendar.getInstance();
                        treatmentDate.set(year, month, day, 0, 0);


                        try {
                            Veterinary v = (Veterinary) myFarm.employee.get(k);



                            boolean emergencyOrNot = checkBox2.isSelected();

                            HealthTreatment healthTreatment = new HealthTreatment(treatmentDate, emergencyOrNot, v);
                            myFarm.getAnimals().get(i).getTreatments().add(healthTreatment); // health treatment added

                            String detail = textField4.getText();

                            String durationS = textField5.getText();
                            int duration = Integer.parseInt(durationS);

                            String starDate = textField6.getText();
                            String startDates[] = starDate.split("[ / ]");
                            String year2S = dates[2];
                            int startyear = Integer.parseInt(year2S);
                            String month2S = dates[1];
                            int startmonth = Integer.parseInt(month2S);
                            String day2S = dates[0];
                            int startday = Integer.parseInt(day2S);
                            Calendar startDate = Calendar.getInstance();
                            startDate.set(startyear, startmonth, startday, 0, 0);

                            String dosageS = textField7.getText();
                            double dosage = Double.parseDouble(dosageS);

                            String note = textField8.getText();

                            Medication meds = new Medication(detail, duration, startDate, dosage, note);
                            healthTreatment.getMedications().add(meds);
                        }
                        catch (Exception e) {
                             myFlag = -1;
                            JOptionPane.showMessageDialog(saveTreatmentButton,"Employee type does not match with treatment!");
                            interface1 int1 = new interface1(myFarm);
                            int1.setVisible(true);
                            dispose();

                        }


                    }
                }


            }
        }

        if(flag == 0)  {  JOptionPane.showMessageDialog(saveTreatmentButton,"Animal does not exists!");
            interface1 int1 = new interface1(myFarm);
            int1.setVisible(true);
            dispose(); }

        else if(flag2 == 0) {  JOptionPane.showMessageDialog(saveTreatmentButton,"Employee does not exists!");
            interface1 int1 = new interface1(myFarm);
            int1.setVisible(true);
            dispose(); }

        else if(myFlag == 1) {
            JOptionPane.showMessageDialog(saveTreatmentButton,"Health Treatment Saved!");
            interface1 int1 = new interface1(myFarm);
            int1.setVisible(true);
            dispose();
        }

        if(myFlag == 1) {


            try {
                ObjectOutputStream outCow = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("cow.dat",false)));

                for(Animal animal:myFarm.getAnimals()){
                    if(animal instanceof Cow) {
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
