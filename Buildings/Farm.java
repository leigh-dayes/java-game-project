package Buildings;

import Villagers.*;
import java.util.Scanner;
import java.util.Date;

public class Farm extends Building {

    private Farmer farmer;
    private Date date = new Date();
    //can only milk cows every 2 minutes
    private long cowMillis = 120000;
    // can only harvest every 3 minutes
    private long harvestMillis = 180000;
    private int cowMoney = 5;
    private int harvestMoney = 10;

    public Farm(boolean locked) {
        super(locked);
        setLength(7);
        setWidth(7);
        setHitPoints(10);
        setType("Farm");
        NamesList name = new NamesList();
        farmer = new Farmer(name.getFirstName(), name.getLastName(), 60);
        farmer.setWeapon(Weapons.Club);
        farmer.setArmour(Armour.none);
    }
    public Farmer getFarmer() {
        return farmer;
    }
    public void doWork(Villager worker) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validCoice = false;
        System.out.println("\nWelcome to my Farm! i could always do with a hand and i pay well!");
        do {
            System.out.println("\n###########################################");
            System.out.println("#        PLEASE CHOOSE AN OPTION          #");
            System.out.println("#-----------------------------------------#");
            System.out.println("#  <3 = " + worker.getHealth() + "%" + "      $ = " + worker.getWallet() + "                   #");
            System.out.println("#                                         #");
            System.out.println("# 1: Milk Cows                            #");
            System.out.println("#                                         #");
            System.out.println("# 2: Harvest Grain                        #");
            System.out.println("#                                         #");
            System.out.println("# 0: EXIT                                 #");
            System.out.println("#                                         #");
            System.out.println("###########################################");
            System.out.print("YOUR SELECTION: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >=0 && choice < 3) {
                    validCoice = true;
                    //scanner.nextLine();
                }
                else {
                    System.out.println("\nOops! please choose an integer that corresponds to a menu option i.e. 1 to milk the cows!\n");
                    //scanner.nextLine();
                }
            }
            else {
                System.out.println("\nOops! please choose an integer that corresponds to a menu option i.e. 1 to milk the cows!\n");
                //scanner.nextLine();
            }
            scanner.nextLine();
        } while (!validCoice);
        switch(choice) {
            case 1: 
                Date lapsed = new Date(date.getTime() + cowMillis);
                Date current = new Date();
                if (current.compareTo(lapsed) > 0) {
                    date = new Date();
                    System.out.println("\nWhy thanks! that was a great help! here's your wage");
                    System.out.println(farmer.getFirstName() + " paid you $" + cowMoney);
                    worker.incWallet(cowMoney);
                    doWork(worker);
                }
                else {
                    //not enough time lapsed
                    System.out.println("\nUnfortunately my Cows arent ready to be milked yet! please come back later");
                }
                break;
            case 2:
                Date hlapsed = new Date(date.getTime() + harvestMillis);
                Date hcurrent = new Date();
                if (hcurrent.compareTo(hlapsed) > 0) {
                    date = new Date();
                    System.out.println("\nWhy thanks! that was a great help! here's your wage");
                    System.out.println(farmer.getFirstName() + " paid you $" + harvestMoney);
                    worker.incWallet(harvestMoney);
                    doWork(worker);
                }
                else {
                    //not enough time lapsed
                    System.out.println("\nUnfortunately my grain doesnt need harvesting yet! please come back later");
                }
                break;
            case 0:
                System.out.println("\nThanks for helping out!, see you next time.");
                break;
        }
    }
        
}
