package FarmApp;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.time.LocalDate;

/**
 * Farm that includes Cow,Sheep,Treatment,Veterinary,FarmWorker,Medication
 * Class interacts with the user
 * @author Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public class FarmApp implements Serializable{

    public ArrayList<Animal> animals;
    public ArrayList<Employee> employee;
    /**
     * Just main, where the User and FarmApp interacts. Also, inlcudes Employees and Animals
     * @param args
     */
    public static void main(String[] args) throws Exception{
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Employee> employee = new ArrayList<Employee>();
        FarmApp myFarm = new FarmApp(animals,employee);
       // PopulateData p = new PopulateData();  // If you want to read my code with populateData, you can uncomment it.
       // p.Populate(myFarm); // It is more easy to evaluate, since I think writing again all cows,sheeps,vets, etc. takes a lot of time.


        DataStorage ds = new DataStorage();
        ds.populateAnimals(myFarm);
        ds.populateEmployee(myFarm);


        LocalDate localTime = LocalDate.now();


        // Thread object that checks cow and sheep objects from file and compares digests
        ThreadClass myThread = new ThreadClass();
        Thread checkThread = new Thread(myThread);
        checkThread.start();


        // Thread object that starts GUI
        Thread guiThread = new Thread(new Runnable() {
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        JMenuBar menuBar = new JMenuBar();
                        JMenu item1 = new JMenu("Menu Bar");
                        JMenu item2 = new JMenu("Settings");
                        JMenuItem saveBar = new JMenuItem("Test");
                        item1.add(saveBar);
                        menuBar.add(item1);
                        menuBar.add(item2);
                        interface1 int1 = new interface1(myFarm);
                        int1.setSize(1000,1000);
                        int1.setJMenuBar(menuBar);
                        int1.setVisible(true);
                    }
                });
            }
        });
        guiThread.start();


    }



    /**
     * Constructor with parameters
     * @param animals ListArray that includes Cow & Sheep objects
     * @param employee ListArray that includdes Vet & FarmWorker objects
     */
    FarmApp(ArrayList<Animal> animals,ArrayList<Employee> employee) { // constructor
        this.animals = animals;
        this.employee = employee;
    }
    /**
     * Default Constructor
     */
    FarmApp() {
        this.animals = new ArrayList<Animal>(); // default constructor
    }

    /**
     * Adds cow to the Animals
     */
    public void addCow() { // ADD COW
        Scanner inp = new Scanner(System.in);

        System.out.println("Please enter the Cow's Tag Number: ");
        int tagno = inp.nextInt();
        int flag = 1;

        do { // Checks the uniqueness of the tagNo
            for(int i=0;i< animals.size();i++) {
                if(animals.get(i).getTagNo() == tagno) {
                    flag = 0;
                    System.out.println("This tag number already exists !" + '\n' + "Please enter an unique tag number: ");
                    tagno = inp.nextInt();
                    break;

                }
                if(animals.get(animals.size()-1).getTagNo() != tagno) flag=1;

            }

        } while(flag == 0);


        inp.nextLine();
        System.out.println("Please enter the Cow's Gender: ");
        String gender = inp.nextLine();

        System.out.println("Please enter true if the cow is purchased or not: ");
        boolean purchased = inp.nextBoolean();
        inp.nextLine();
        System.out.println("Please enter the birth date of the cow (dd/mm/yy): ");
        String str = inp.nextLine();

        String dates[] = str.split("[ / ]");
        int year = Integer.parseInt(dates[2]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[0]);
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day, 0, 0);

        HashMap<String,Double> hMap = new HashMap<>();


        System.out.println("Please enter the weight of the cow: ");
        int weight = inp.nextInt();
        inp.nextLine();

        Cow myCow = new Cow(tagno, gender, purchased, birthDate,hMap,weight);
        animals.add(myCow);


    }
    /**
     * Deletes cow, regarding the tagNo
     * @param tagNo tag number of the animal
     */
    public void deleteCow(int tagNo) {
        int check = 0;
        for (int i = 0; i < animals.size(); i++) {
            if (tagNo == animals.get(i).getTagNo()) {
                animals.remove(i);
                check = 1;
            }
        }
        if (check == 1) System.out.println("Cow Deleted!");
        else System.out.println("Cow Not Found!");
    }
    /**
     *Displays the desired cow's details
     * @param tagNo tag number of the animal
     *
     */
    public void getCowDetails(int tagNo) {
        int check = 0;
        for (int i = 0; i < animals.size(); i++) {
            if (tagNo == animals.get(i).getTagNo()) {
                try { // try-catch blocks in order to see whether the object can be type-casted, or not.
                    Cow c = (Cow) animals.get(i);
                    check = 1;
                    System.out.println(
                            "Tag No: " + c.getTagNo() + '\n' +
                                    "Gender: " + c.getGender() + '\n' +
                                    "Weight: " + c.getWeight() + '\n' +
                                    "Purchased: " + c.getPurchased());

                    for (Object m : c.getMilking().keySet()) {
                        System.out.println("Date: " + m + " => Milk amount: " + c.getMilking().get(m));
                    }


                    System.out.println("Birth Date: " + c.getDateOfBirth().get(Calendar.DATE) + "/"
                            + c.getDateOfBirth().get(Calendar.MONTH) + "/" + c.getDateOfBirth().get(Calendar.YEAR));
                } catch (Exception e) {
                    System.out.println("This tag number is not associated with cow!");
                    check=1;
                }


            }
        }
        if (check == 0) System.out.println("Cow Not Found!");
    }
    /**
     * Adds sheep to the Animals
     */
    public void addSheep() {
        Scanner inp = new Scanner(System.in);

        System.out.println("Please enter the Sheep's Tag Number: ");
        int tagno = inp.nextInt();
        int flag = 1;

        do { // Checks the uniqueness of the tagNo
            for(int i=0;i< animals.size();i++) {
                if(animals.get(i).getTagNo() == tagno) {
                    flag = 0;
                    System.out.println("This tag number already exists !" + '\n' + "Please enter an unique tag number: ");
                    tagno = inp.nextInt();
                    break;

                }
                if(animals.get(animals.size()-1).getTagNo() != tagno) flag=1;

            }

        } while(flag == 0);


        inp.nextLine();
        System.out.println("Please enter the Sheep's Gender: ");
        String gender = inp.nextLine();

        System.out.println("Please enter true if the Sheep is purchased or not: ");
        boolean purchased = inp.nextBoolean();
        inp.nextLine();
        System.out.println("Please enter the birth date of the Sheep (dd/mm/yy): ");
        String str = inp.nextLine();

        String dates[] = str.split("[ / ]");
        int year = Integer.parseInt(dates[2]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[0]);
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day, 0, 0);

        HashMap<String,Double> hMap = new HashMap<>();



        Sheep mySheep = new Sheep(tagno, gender, purchased, birthDate,hMap);
        animals.add(mySheep);


    }
    /**
     * Adds vet to the Employees
     */
    public void addVet() {
        Scanner inp = new Scanner(System.in);

        System.out.println("Please enter the Vet's ID: ");
        int vetID = inp.nextInt();
        int flag = 1;

        do {  // Checks the uniqueness of the vet ID
            for(int i=0;i< employee.size();i++) {
                if(employee.get(i).getEmpID() == vetID) {
                    flag = 0;
                    System.out.println("This Employee ID already exists !" + '\n' + "Please enter an unique Employee ID: ");
                    vetID = inp.nextInt();
                    break;

                }
                if(employee.get(employee.size()-1).getEmpID() != vetID) flag=1;

            }

        } while(flag == 0);

        inp.nextLine();
        System.out.println("Please enter the gender of Vet: ");
        String gender = inp.nextLine();

        System.out.println("Please enter the birth date of the Vet (dd/mm/yy): ");
        String str = inp.nextLine();

        String dates[] = str.split("[ / ]");
        int year = Integer.parseInt(dates[2]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[0]);
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day, 0, 0);
        System.out.println("Please enter true if Vet has BSc Degree: ");
        boolean degree = inp.nextBoolean();
        inp.nextLine();
        System.out.println("Please enter the graduation date of Vet: ");
        String str2 = inp.nextLine();
        String dates2[] = str2.split("[ / ]");
        int year2 = Integer.parseInt(dates2[2]);
        int month2 = Integer.parseInt(dates2[1]);
        int day2 = Integer.parseInt(dates[0]);
        Calendar gradDate = Calendar.getInstance();
        gradDate.set(year2,month2,day2,0,0);
        System.out.println("Please enter the expertise level of Vet: ");
        int level = inp.nextInt();
        inp.nextLine();

        Veterinary myVet = new Veterinary(vetID, gender, birthDate, degree,gradDate,level);
        employee.add(myVet);
    }

    /**
     * Adds farmworker to the VetArray
     */
    public void addFarmWorker() {
        Scanner inp = new Scanner(System.in);

        System.out.println("Please enter the Farm Worker's ID: ");
        int farmWorkerID = inp.nextInt();
        int flag = 1;

        do {  // Checks the uniqueness of the vet ID
            for(int i=0;i< employee.size();i++) {
                if(employee.get(i).getEmpID() == farmWorkerID) {
                    flag = 0;
                    System.out.println("This Employee ID already exists !" + '\n' + "Please enter an unique Employee ID: ");
                    farmWorkerID = inp.nextInt();
                    break;

                }
                if(employee.get(employee.size()-1).getEmpID() != farmWorkerID) flag=1;

            }

        } while(flag == 0);

        inp.nextLine();
        System.out.println("Please enter the gender of farm worker: ");
        String gender = inp.nextLine();

        System.out.println("Please enter the birth date of the farm worker (dd/mm/yy): ");
        String str = inp.nextLine();

        String dates[] = str.split("[ / ]");
        int year = Integer.parseInt(dates[2]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[0]);
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day, 0, 0);
        System.out.println("Please enter the previous farm name for the farm worker: ");
        String previousFarm = inp.nextLine();
        System.out.println("Please enter the work experience of the farm worker: ");
        int experience = inp.nextInt();
        inp.nextLine();

        FarmWorker myFarmWorker = new FarmWorker(farmWorkerID, gender, birthDate, previousFarm,experience);
        employee.add(myFarmWorker);

    }
    /**
     *Deletes desired Vet, with taken employeeID
     * @param vetID Unique id of employee
     *
     */
    public void deleteVet(int vetID) {
        int check = 0;
        for (int i = 0; i < employee.size(); i++) {
            if (vetID == employee.get(i).getEmpID()) {
                employee.remove(i);
                check = 1;
            }
        }
        if (check == 1) System.out.println("Vet Deleted!");
        else System.out.println("Vet Not Found!");

    }
    /**
     *Displays vet details, regarding to the vetID
     * @param vetID Unique id of Vet
     *
     */
    public void getVetDetails(int vetID) {
        int check = 0,flag=0;
        for (int i = 0; i < employee.size(); i++) {
            if (vetID == employee.get(i).getEmpID()) {
                try { // try-catch block in order to see , if employee is Vet or not. Exception occurs if program can not type-cast.
                    Veterinary v = (Veterinary) employee.get(i);

                    check = 1;
                    System.out.println(
                            "Employee ID: " + v.getEmpID() + '\n' +
                                    "Gender: " + v.getGender() + '\n' +
                                    "Birth Date: " + v.getDateOfBirth().get(Calendar.DATE) + "/"
                                    + v.getDateOfBirth().get(Calendar.MONTH) + "/" + v.getDateOfBirth().get(Calendar.YEAR) + '\n' +
                                    "BScDegree: " + v.getBScDegree() + '\n' +
                                    "Date of Graduation: " + v.getDateOfGraduation().get(Calendar.DATE) + "/"
                                    + v.getDateOfGraduation().get(Calendar.MONTH) + "/" + v.getDateOfGraduation().get(Calendar.YEAR) + '\n' +
                                    "Expertise Level: " + v.getExpertiseLevel());
                } catch (Exception e) {
                    flag = 1;
                }
            }
        }
        if (check == 0 && flag ==0 ) System.out.println("Vet Not Found!");
        if(flag == 1) System.out.println("Your input: " + vetID + " is not a Vet, this employee is farm worker. Please check your employee again!");
    }




    /**
     * Lists all the vets in the farm
     */
    public void listVet() {
        for(int i=0;i< employee.size();i++) {
            if(employee.get(i) instanceof Veterinary) {
                Veterinary v = (Veterinary) employee.get(i);


                System.out.println(
                        "Employee ID: " + v.getEmpID() + '\n' +
                                "Gender: " + v.getGender() + '\n' +
                                "Birth Date: " + v.getDateOfBirth().get(Calendar.DATE) + "/"
                                + v.getDateOfBirth().get(Calendar.MONTH) + "/" + v.getDateOfBirth().get(Calendar.YEAR) + '\n' +
                                "BScDegree: " + v.getBScDegree() + '\n' +
                                "Date of Graduation: " + v.getDateOfGraduation().get(Calendar.DATE) + "/"
                                + v.getDateOfGraduation().get(Calendar.MONTH) + "/" + v.getDateOfBirth().get(Calendar.YEAR) + '\n' +
                                "Expertise Level: " + v.getExpertiseLevel());
                System.out.println();

            }

        }
    }

    /**
     * List all the cows in the farm
     */
    public void listCow() {
        for(int i =0;i<animals.size();i++) {
            if(animals.get(i) instanceof Cow) {
                Cow c = (Cow) animals.get(i);
                System.out.println(
                        "Tag No: " + c.getTagNo() + '\n' +
                                "Gender: " + c.getGender() + '\n' +
                                "Weight: " + c.getWeight() + '\n' +
                                "Purchased: " + c.getPurchased());

                for (Object m : c.getMilking().keySet()) {
                    System.out.println("Date: " + m + " => Milk amount: " + c.getMilking().get(m));
                }


                System.out.println("Birth Date: " + c.getDateOfBirth().get(Calendar.DATE) + "/"
                        + c.getDateOfBirth().get(Calendar.MONTH) + "/" + c.getDateOfBirth().get(Calendar.YEAR));
                System.out.println();

            }
        }

    }
    /**
     *Adds treatment to the taken tagNo , also matches the treatment with taken vet or farmworker.
     * @param empID Unique id of employee
     * @param tagNo tag number of the animal
     *
     */
    public void addTreatment(int empID, int tagNo) {
        int flag = 0;
        int flag2 = 0;
        for(int i=0;i<animals.size();i++) {

            if(animals.get(i).getTagNo() == tagNo) {
                flag = 1;


                for(int k=0;k<employee.size();k++) {
                    if(employee.get(k).getEmpID() == empID) {
                        flag2 = 1;

                        Scanner inp = new Scanner(System.in);

                        System.out.println("Please enter the date of the treatment (dd/mm/yy): ");
                        String str = inp.nextLine();

                        String dates[] = str.split("[ / ]");
                        int year = Integer.parseInt(dates[2]);
                        int month = Integer.parseInt(dates[1]);
                        int day = Integer.parseInt(dates[0]);
                        Calendar treatmentDate = Calendar.getInstance();
                        treatmentDate.set(year, month, day, 0, 0);

                        if(employee.get(k) instanceof FarmWorker && flag2 == 1) {
                            FarmWorker fw = (FarmWorker) employee.get(k);

                            System.out.println("Please enter the materials used: ");
                            String str2 = inp.nextLine();
                            CleaningTreatment cleaningTreatment = new CleaningTreatment(treatmentDate,str2, fw);
                            animals.get(i).getTreatments().add(cleaningTreatment); // cleaning treatment added

                        }
                        else if(flag2 == 1) {
                            Veterinary v = (Veterinary) employee.get(k);

                            System.out.println("Please enter true if it is emergency: ");
                            boolean emergencyOrNot = inp.nextBoolean();
                            inp.nextLine();
                            HealthTreatment healthTreatment = new HealthTreatment(treatmentDate,emergencyOrNot,v);
                            animals.get(i).getTreatments().add(healthTreatment); // health treatment added

                            System.out.println("Please enter the details of medication: ");
                            String detail = inp.nextLine();
                            System.out.println("Please enter the duration of medication: ");
                            int duration = inp.nextInt();
                            inp.nextLine();
                            System.out.println("Please enter the start date of medication: ");
                            String starDate = inp.nextLine();
                            String startDates[] = starDate.split("[ / ]");
                            int startyear = Integer.parseInt(dates[2]);
                            int startmonth = Integer.parseInt(dates[1]);
                            int startday = Integer.parseInt(dates[0]);
                            Calendar startDate = Calendar.getInstance();
                            startDate.set(startyear, startmonth, startday, 0, 0);
                            System.out.println("Please enter the dosage of medication: ");
                            double dosage = inp.nextDouble();
                            inp.nextLine();
                            System.out.println("Please enter notes about medication: ");
                            String note = inp.nextLine();

                            Medication meds = new Medication(detail,duration,startDate,dosage,note);
                            healthTreatment.getMedications().add(meds);






                        }

                    }
                }


            }
        }
        if(flag == 0) System.out.println("Animal not found!");
        else if(flag2 == 0) System.out.println("Employee not found!");
        else System.out.println("Treatment information is saved!");
    }


    /**
     *Displays treatment and medications of the taken cow's tagNo
     * @param tagNo tag number of the cow
     *
     */
    public void getCowTreatment(int tagNo) {
        int flag = 0;

        for (int i = 0; i < animals.size(); i++) {
            if (tagNo == animals.get(i).getTagNo()) {
                try { // try-catch blocks in order to see whether animal is Cow or not !
                    Cow c = (Cow) animals.get(i);
                    flag = 1;

                    for (int j = 0; j < c.getTreatments().size(); j++) {
                        System.out.println("-- Treatment " + (j + 1) + " details given below --");

                        System.out.println("Treatment Date: " + animals.get(i).getTreatments().get(j).getDateOfTreatment().get(Calendar.DATE)
                                + '/' + animals.get(i).getTreatments().get(j).getDateOfTreatment().get(Calendar.MONTH)
                                + '/' + animals.get(i).getTreatments().get(j).getDateOfTreatment().get(Calendar.YEAR));

                        if (animals.get(i).getTreatments().get(j) instanceof CleaningTreatment) {
                            CleaningTreatment ct = (CleaningTreatment) animals.get(i).getTreatments().get(j);

                            System.out.println("Materials used: " + ct.getMaterialsused());

                            System.out.println("Farm Worker associated with this treatment: " + ct.getResponsibleFarmworker().getEmpID());
                            System.out.println();
                        } else {
                            HealthTreatment ht = (HealthTreatment) animals.get(i).getTreatments().get(j);

                            System.out.println("Emergency: " + ht.getEmergency());

                            System.out.println("vetID associated with this treatment: " + ht.getResponsibleVet().getEmpID());


                            for (int k = 0; k < ht.getMedications().size(); k++) {


                                System.out.println("Medication " + (k + 1) + " Detail: " + ht.getMedications().get(k).getDetails());

                                System.out.println("Medication " + (k + 1) + " Duration:" + ht.getMedications().get(k).getDuration());

                                System.out.println("Medication " + (k + 1) + " Dosage: " + ht.getMedications().get(k).getDosage());

                                System.out.println("Medication " + (k + 1) + " Start Date: " + ht.getMedications().get(k).getStartDate().get(Calendar.DATE)
                                        + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.MONTH)
                                        + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.YEAR));

                                System.out.println("Medication " + (k + 1) + " Note: " + ht.getMedications().get(k).getNotes());

                            }
                            System.out.println();
                        }

                    }
                    break;
                } catch (Exception e) {
                    System.out.println("This tag no is not associated with cow!");
                    flag = 1;
                }

            }
        }
        if (flag == 0) System.out.println("Cow Not Found!");


    }
    /**
     *Displays specific treatment with taken date of treatment ,and medications of the taken cow's tagNo
     * @param tagNo tag number of the cow
     * @param dateOfTreatment Treatment date of the cow
     *
     */
    public void getCowTreatment(int tagNo,Calendar dateOfTreatment) {
        int flag = 0;
        int cowFlag = 0;
        for (int i = 0; i < animals.size(); i++)
        {
            if (tagNo == animals.get(i).getTagNo())
            {
                try {
                    Cow c = (Cow) animals.get(i);
                    cowFlag = 1;

                    for (int j = 0; j < c.getTreatments().size(); j++) {
                        if (dateOfTreatment.get(Calendar.YEAR) == c.getTreatments().get(j).getDateOfTreatment().get(Calendar.YEAR)
                                && dateOfTreatment.get(Calendar.MONTH) == c.getTreatments().get(j).getDateOfTreatment().get(Calendar.MONTH)
                                && dateOfTreatment.get(Calendar.DATE) == c.getTreatments().get(j).getDateOfTreatment().get(Calendar.DATE)) {
                            flag = 1;

                            if (animals.get(i).getTreatments().get(j) instanceof CleaningTreatment) {


                                CleaningTreatment ct = (CleaningTreatment) animals.get(i).getTreatments().get(j);

                                System.out.println("Materials used: " + ct.getMaterialsused());

                                System.out.println("Farm Worker associated with this treatment: " + ct.getResponsibleFarmworker().getEmpID());
                                System.out.println();
                            } else {

                                HealthTreatment ht = (HealthTreatment) animals.get(i).getTreatments().get(j);

                                System.out.println("Emergency: " + ht.getEmergency());

                                System.out.println("vetID associated with this treatment: " + ht.getResponsibleVet().getEmpID());


                                for (int k = 0; k < ht.getMedications().size(); k++) {

                                    System.out.println("Medication " + (k + 1) + " Detail: " + ht.getMedications().get(k).getDetails());

                                    System.out.println("Medication " + (k + 1) + " Duration:" + ht.getMedications().get(k).getDuration());

                                    System.out.println("Medication " + (k + 1) + " Dosage: " + ht.getMedications().get(k).getDosage());

                                    System.out.println("Medication " + (k + 1) + " Start Date: " + ht.getMedications().get(k).getStartDate().get(Calendar.DATE)
                                            + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.MONTH)
                                            + '/' + ht.getMedications().get(k).getStartDate().get(Calendar.YEAR));

                                    System.out.println("Medication " + (k + 1) + " Note: " + ht.getMedications().get(k).getNotes());

                                }

                            }

                            System.out.println();
                        }


                    }
                } catch (Exception e) {
                    System.out.println("This tag no is not associated with cow!");
                    cowFlag = 1;
                    flag = 1;
                }


            }


        }
        if(cowFlag == 0) System.out.println("Cow Not Found!");
        else if(flag == 0) System.out.println("Treatment With This Date Can Not Found!");

    }

    /**
     * Calculates the salary of employee
     * @param empID
     * @return salary
     */
    public double getEmpSalary(int empID) {
        int flag = 0;
        int index= -1;
        LocalDate myLocalDate = LocalDate.now();

        for(int i=0;i<employee.size();i++) {
            if(employee.get(i).getEmpID() == empID) { flag = 1; index = i; break;}
        }
        if(flag == 0) {
            return 0;
        }

        else if(employee.get(index) instanceof Veterinary) {
            Veterinary v = (Veterinary) employee.get(index);
            return (v.getSalary() + ((0.10 * v.getSalary()) * (myLocalDate.getYear() - v.getDateOfGraduation().get(Calendar.YEAR))));
        }

        else {
            FarmWorker fw = (FarmWorker) employee.get(index);
            return fw.getSalary() + (0.02 * fw.getWorkexperience() * fw.getSalary());

        }



    }

    /**
     * Displays the feeding method of animal regard to animal's attributes.
     * @param tagNo
     */
    public void feedingAnimal(int tagNo) {
        int flag = 0;
        int index = -1;
        for(int i = 0; i < animals.size(); i++) {
            if(tagNo == animals.get(i).getTagNo()) {
                flag = 1;
                index = i;
            }
        }
        if(flag == 1) {
            animals.get(index).feeding();
        }
        else System.out.println("Animal not found!");



    }

    /**
     * Records amount of milk for the day, to any animal with given TagNo.
     * @param tagNo
     * @param amount
     */
    public void addMilkingMeasurement(int tagNo, double amount) {
        LocalDate date = LocalDate.now();
        int flag = 0;


        for(int i = 0; i < animals.size(); i++) {
            if(tagNo == animals.get(i).getTagNo()) {
                flag = 1;
                animals.get(i).setMilking(String.valueOf(date),amount);

            }

        }
        if(flag==0) System.out.println("Animal not found!");
        else System.out.println("Milking measurement added!");
    }
    /**
     * Displays menu
     */
    public void menu() {

        System.out.println("-- Please Select an Option --");
        System.out.println("1) Add Cow");
        System.out.println("2) Add Sheep");
        System.out.println("3) Delete Cow");
        System.out.println("4) Get Cow Details");
        System.out.println("5) Add Vet");
        System.out.println("6) Add FarmWorker");
        System.out.println("7) Delete Vet");
        System.out.println("8) Get Vet Details");
        System.out.println("9) Add Treatment");
        System.out.println("10) Get Cow Treatment");
        System.out.println("11) Get Cow Treatment With Specific Date");
        System.out.println("12) List Cow");
        System.out.println("13) List Vet");
        System.out.println("14) Animal Feeding");
        System.out.println("15) Get Employee Salary");
        System.out.println("16) Add Milking Measurement");
        System.out.println("17) Exit");

    }
    /**
     * Terminates the menu and exits
     */
    public void exit() {
        System.out.println("Logged out of the Smart FarmApp!");
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Employee> getEmployee() {
        return employee;
    }
}
