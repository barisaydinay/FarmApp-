package FarmApp;
import java.util.*;
import java.time.LocalDate;

/**
 * Dummy Class for populating the Farm initially
 * @author  Baris Aydinay 2452977
 * @version SE Development Kit 19.0.1
 */

public class PopulateData {
    public Cow cow;
    public Sheep sheep;
    public Veterinary vet;
    public FarmWorker farmWorker;
    public HealthTreatment ht;
    public CleaningTreatment ct;
    public Medication med;

    /**
     * Default constructor for PopulateData
     */
    PopulateData() {
        this.cow = new Cow();
        this.sheep = new Sheep();
        this.vet = new Veterinary();
        this.farmWorker = new FarmWorker();
        this.ht = new HealthTreatment();
        this.ct = new CleaningTreatment();
        this.med = new Medication();
    }

    /**
     * consturctor with paramters for populateData
     * @param myFarm inital empty farm
     */
    public void Populate(FarmApp myFarm) { // (vet / farmworker) -> animals -> treatment -> medications

        Calendar time1 = Calendar.getInstance();
        time1.set(2000,10,5,0,0);
        Calendar time2 = Calendar.getInstance();
        time2.set(2022,11,16,0,0);


        Veterinary vet1 = new Veterinary(1,"Male",time1,true,time2,10);

        Calendar time3 = Calendar.getInstance();
        time3.set(2000,1,1,0,0);
        Calendar time4 = Calendar.getInstance();
        time4.set(2023,10,10,0,0);

        Veterinary vet2 = new Veterinary(2,"Female",time3,true,time4,9);

        Calendar time5 = Calendar.getInstance();
        time5.set(2010,8,7,0,0);
        HashMap<String,Double> hMap =new HashMap<>();

        Cow cow1 = new Cow(100,"Male",true,time5,hMap,200);

        Calendar time6 = Calendar.getInstance();
        time6.set(2011,4,9,0,0);
        HashMap<String,Double> hMap2 =new HashMap<>();

        Cow cow2 = new Cow(101,"Female",false,time6,hMap2,250);

        Calendar time7 = Calendar.getInstance();
        time7.set(2011,11,10,0,0);
        HashMap<String,Double> hMap3 =new HashMap<>();

        Sheep sheep1 = new Sheep(102,"Male",true,time7,hMap3);

        Calendar time8 = Calendar.getInstance();
        time8.set(2013,5,5,0,0);
        HashMap<String,Double> hMap4 =new HashMap<>();

        Sheep sheep2 = new Sheep(103,"Female",false,time8,hMap4);

        Calendar time9 = Calendar.getInstance();
        time9.set(1999,10,5,0,0);

        FarmWorker farmWorker1 = new FarmWorker(3,"Male",time9,"Happy Farm",7);

        Calendar time10 = Calendar.getInstance();
        time10.set(1998,11,2,0,0);

        FarmWorker farmWorker2 = new FarmWorker(4,"Female",time10,"NCC Farm",9);

        Calendar time11 = Calendar.getInstance();
        time11.set(2022,11,2,0,0);

        HealthTreatment ht1 = new HealthTreatment(time11,true,vet1);
        Medication med1 = new Medication("Surgery",100,time11,150.0,"Rest the animal");
        ht1.getMedications().add(med1);
        cow1.getTreatments().add(ht1);

        Calendar time12 = Calendar.getInstance();
        time12.set(2022,10,12,0,0);

        CleaningTreatment ct1 = new CleaningTreatment(time12,"Brush",farmWorker1);
        cow1.getTreatments().add(ct1);
        myFarm.animals.add(cow1); // Cow 1 added with all types of treatments, medications.

        Calendar time13 = Calendar.getInstance();
        time13.set(2022,9,9,0,0);

        HealthTreatment ht2 = new HealthTreatment(time13,false,vet2);
        Medication med2 = new Medication("Routine Health Check",10,time13,10,"No problem on animal");
        ht2.getMedications().add(med2);
        cow2.getTreatments().add(ht2);

        Calendar time14 = Calendar.getInstance();
        time14.set(2022,1,2,0,0);
        CleaningTreatment ct2 = new CleaningTreatment(time14,"Cold Water",farmWorker2);
        cow2.getTreatments().add(ct2);
        myFarm.animals.add(cow2); // Cow 2 added with all types of treatments, medications.

        Calendar time15 = Calendar.getInstance();
        time15.set(2022,7,4,0,0);
        HealthTreatment ht3 = new HealthTreatment(time15,true,vet1);
        Medication med3 = new Medication("Illness",15,time15,60,"Anti-biotics given.");
        ht3.getMedications().add(med3);
        sheep1.getTreatments().add(ht3);

        time15.set(2022,5,19,0,0);
        CleaningTreatment ct3 = new CleaningTreatment(time15,"Hot Water",farmWorker2);
        sheep1.getTreatments().add(ct3);
        myFarm.animals.add(sheep1); // Sheep 1 added with all types of treatments, medications.

        time15.set(2023,10,5,0,0);
        HealthTreatment ht4 = new HealthTreatment(time15,false,vet1);
        Medication med4 = new Medication("Birth Aches",90,time15,45,"Pills given and taken to hospital for check.");
        ht4.getMedications().add(med4);
        sheep2.getTreatments().add(ht4);

        time15.set(2023,9,17);
        CleaningTreatment ct4 = new CleaningTreatment(time15,"Clippers",farmWorker1);
        sheep2.getTreatments().add(ct4);
        myFarm.animals.add(sheep2); // Sheep 2 added with all types of treatments, medications.

        myFarm.employee.add(vet1);
        myFarm.employee.add(vet2);
        myFarm.employee.add(farmWorker1);
        myFarm.employee.add(farmWorker2);




    }








}
