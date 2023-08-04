package FarmApp;

import javax.swing.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Thread Class
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */
public class ThreadClass  implements Runnable{

    /**
     * Runnable method that compares digests and determines if the txt has been changed by anyone
     */
    @Override
    public void run() {


        //Controlling cow objects
        FileInputStream fis = null;
        try {
            File myFile = new File("cows.dat");
            if(!myFile.exists()) {
                try {
                    myFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            fis = new FileInputStream("cows.dat");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedInputStream bis = new BufferedInputStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int ch;
        while (true) {
            try {
                if (!((ch = bis.read()) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            baos.write(ch);
        }

        byte buffer[] = baos.toByteArray();

        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        algorithm.reset();
        algorithm.update(buffer);
        byte digest[] = algorithm.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
            hexString.append(" ");
        }


        try {
            File myTxt = new File("securityCow.txt");
            if(!myTxt.exists()) myTxt.createNewFile();
            Scanner myReader = new Scanner(myTxt);
            try {
                String data = myReader.nextLine();
                if(data.equals(hexString.toString())) JOptionPane.showMessageDialog(null,"COW DATA SAFE!");
                else JOptionPane.showMessageDialog(null,"COW DATA CHANGED!");
            }catch(NoSuchElementException e) {
                JOptionPane.showMessageDialog(null,"COW DATA SAFE!");

            }

        } catch (FileNotFoundException e) {
            System.out.println("Security file ex!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Controlling Sheep objects


        FileInputStream fis2 = null;
        try {
            File myFile = new File("sheeps.dat");
            if(!myFile.exists()) {
                try {
                    myFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            fis2 = new FileInputStream("sheeps.dat");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedInputStream bis2 = new BufferedInputStream(fis2);
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();

        int ch2;
        while (true) {
            try {
                if (!((ch2 = bis2.read()) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            baos2.write(ch2);
        }

        byte buffer2[] = baos2.toByteArray();

        MessageDigest algorithm2 = null;
        try {
            algorithm2 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        algorithm2.reset();
        algorithm2.update(buffer2);
        byte digest2[] = algorithm2.digest();
        StringBuffer hexString2 = new StringBuffer();
        for (int i = 0; i < digest2.length; i++) {
            hexString2.append(Integer.toHexString(0xFF & digest2[i]));
            hexString2.append(" ");
        }


        try {
            File myTxt = new File("securitySheep.txt");
            if(!myTxt.exists()) myTxt.createNewFile();
            Scanner myReader = new Scanner(myTxt);
            try {
                String data = myReader.nextLine();
                if(data.equals(hexString2.toString())) JOptionPane.showMessageDialog(null,"SHEEP DATA SAFE!");
                else JOptionPane.showMessageDialog(null,"SHEEP DATA CHANGED!");
            }catch(NoSuchElementException e) {
                JOptionPane.showMessageDialog(null,"SHEEP DATA SAFE!");

            }

        } catch (FileNotFoundException e) {
            System.out.println("Security file ex!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }




}
