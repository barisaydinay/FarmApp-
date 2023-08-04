package FarmApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;

public class salaryInterface extends  JFrame{
    private JPanel salaryMainPanel;
    private JButton backToMenuButton;
    private JTextField textField1;
    private JButton displaySalaryButton;

    public salaryInterface(FarmApp myFarm) {
        add(salaryMainPanel);
        salaryMainPanel.setVisible(true);
        setSize(500,500);
        setTitle("Farm App / Employee Salary");
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
        displaySalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empIDS = textField1.getText();
                int empID = Integer.parseInt(empIDS);
                double s = salary(myFarm,empID);
                if(s == 0) JOptionPane.showMessageDialog(displaySalaryButton,"Employee Not Found!");
                else { JOptionPane.showMessageDialog(displaySalaryButton,s+"$"); }


            }
        });
    }

    public double salary(FarmApp myFarm, int empID) {

        int flag = 0;
        int index= -1;
        LocalDate myLocalDate = LocalDate.now();

        for(int i=0;i<myFarm.getEmployee().size();i++) {
            if(myFarm.getEmployee().get(i).getEmpID() == empID) { flag = 1; index = i; break;}
        }
        if(flag == 0) {
            return 0;
        }

        else if(myFarm.getEmployee().get(index) instanceof Veterinary) {
            Veterinary v = (Veterinary) myFarm.getEmployee().get(index);
            return (v.getSalary() + ((0.10 * v.getSalary()) * (myLocalDate.getYear() - v.getDateOfGraduation().get(Calendar.YEAR))));
        }

        else {
            FarmWorker fw = (FarmWorker) myFarm.getEmployee().get(index);
            return fw.getSalary() + (0.02 * fw.getWorkexperience() * fw.getSalary());

        }





    }


}
