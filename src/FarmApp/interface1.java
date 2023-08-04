package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class interface1 extends JFrame {

    private JPanel menuPanel;
    private JButton deleteCowButton;
    private JButton listCowsButton;
    private JButton addCowButton;
    private JButton addSheepButton;
    private JButton displayCowTreatmentsButton;
    private JButton displayCowDetailsButton;
    private JButton addVetButton;
    private JButton addFarmWorkerButton;
    private JButton deleteVetButton;
    private JButton displayVetDetailsButton;
    private JButton addTreatmentButton;
    private JButton displayCowTreatmentsWithButton;
    private JButton listVetsButton;
    private JButton animalFeedingButton;
    private JButton displayEmployeeSalaryButton;
    private JButton addMilkingMeasurementButton;
    private JButton exitButton;

    public interface1(FarmApp myFarm) {

        menuPanel.setLocation(100, 100);
        add(menuPanel);
        setSize(1500,1500);
        setTitle("Farm App");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        listCowsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                listCowInterface lci = new listCowInterface(myFarm);
                lci.setVisible(true);
                dispose();



            }
        });



        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);
        deleteCowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCowInterface dci = new deleteCowInterface(myFarm);
                dci.setVisible(true);
                dispose();



            }
        });
        addCowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addcowInterface aci = new addcowInterface(myFarm);
                aci.setVisible(true);
                dispose();

            }
        });
        addSheepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSheepInterface asi = new addSheepInterface(myFarm);
                asi.setVisible(true);
                dispose();

            }
        });
        displayCowTreatmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getcowtreatmentsInterface gct = new getcowtreatmentsInterface(myFarm);
                gct.setVisible(true);
                dispose();
            }
        });
        displayCowDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cowDetailsInterface cdi = new cowDetailsInterface(myFarm);
                cdi.setVisible(true);
                dispose();
            }
        });
        addVetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVetInterface avi = new addVetInterface(myFarm);
                avi.setVisible(true);
                dispose();
            }
        });
        addFarmWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFWInterface afwi = new addFWInterface(myFarm);
                afwi.setVisible(true);
                dispose();
            }
        });
        deleteVetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteVetInterface dvi = new deleteVetInterface(myFarm);
                dvi.setVisible(true);
                dispose();
            }
        });
        displayVetDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayVetInterface dvi = new displayVetInterface(myFarm);
                dvi.setVisible(true);
                dispose();
            }
        });
        addTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTreatmentInterface ati = new addTreatmentInterface(myFarm);
                ati.setVisible(true);
                dispose();
            }
        });
        displayCowTreatmentsWithButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                treatmentswithDateInterface twdi = new treatmentswithDateInterface(myFarm);
                twdi.setVisible(true);
                dispose();
            }
        });
        listVetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listVetInterface lvi = new listVetInterface(myFarm);
                lvi.setVisible(true);
                dispose();
            }
        });
        animalFeedingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animalFeedingInterface afi = new animalFeedingInterface(myFarm);
                afi.setVisible(true);
                dispose();
            }
        });
        displayEmployeeSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salaryInterface si = new salaryInterface(myFarm);
                si.setVisible(true);
                dispose();
            }
        });
        addMilkingMeasurementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                milkingInterface mi = new milkingInterface(myFarm);
                mi.setVisible(true);
                dispose();

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


}


