package Territory;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import Villagers.*;
import java.util.Scanner;
import java.util.Random;
import Buildings.*;

public class Territory {
    private String name;
    private List<Villager> villagers = new ArrayList<Villager>();
    private NamesList names = new NamesList();
    private List<Building> buildings = new ArrayList<Building>();
    // number of diffent types of villigers
    static final int numVillTypes = 4;
    static final int maxAge = 120;

    public Territory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWhat is the name of your Territory?: ");
        this.name = scanner.nextLine();
        boolean validNum = false;
        do {
            System.out.print("\nHow many Villagers live in " + name + ": ");
            try {
                int numV = scanner.nextInt();
                if (numV > 0) {
                    populateTerritory(numV);
                    validNum = true;
                }
                else {
                    System.out.println("Whoops must have atleast 1 villiger to occupy a territory!");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Whoops, make sure you enter a positive number! ");
                scanner.nextLine();
            }
        } while(!validNum);
        scanner.close();
    }
    // constructor for creating the enemy village
    public Territory(String name, int numVillagers) {
        this.name = name;
        populateTerritory(numVillagers);
    }
    /**
     * A function to populate a new territory
     */
    private void populateTerritory(int numVillagers){
        //Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        for (int i = 1; i <= numVillagers; i++) {
            // automatic assignment of villagers
            names.newName();
            String fname = names.getFirstName();
            String lname = names.getLastName();
            int villAge = rand.nextInt(maxAge+1);
            //Manual assignmnet of villagers
            /*
            System.out.print("Please enter the First Name of villager number " + i + ": ");
            String fname = scanner.nextLine();
            System.out.print("Please enter the Last Name of villager number " + i + ": ");
            String lname = scanner.nextLine();
            boolean validAge = false;
            int villAge = 0;
            do {
                System.out.print("what is the age of the villager?: ");
                try {
                    villAge = scanner.nextInt();
                    if (villAge > 0 && villAge < 121) {
                        validAge = true;
                        scanner.nextLine();
                    }
                    else {
                        System.out.println("Please enter a valid age between 0 and 120");
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number for age, between 0 and 120");
                    scanner.nextLine();
                }
            } while(!validAge);
            */
            // randomly assign characters to be Knights, Farmers or Blacksmiths
            int selection = rand.nextInt(numVillTypes);
            switch (selection) {
                case 0:
                    //System.out.println(fname + " " + lname + " has been allocated the role of Knight!\n");
                    this.villagers.add(new Knight(fname, lname, villAge));
                    break;
                case 1:
                    //System.out.println(fname + " " + lname + " has been allocated the role of Blacksmith!\n");
                    this.villagers.add(new Blacksmith(fname, lname, villAge));
                    break;
                case 2:
                    //System.out.println(fname + " " + lname + " has been allocated the role of Farmer!\n");
                    this.villagers.add(new Farmer(fname, lname, villAge));
                    break;
                case 3:
                    //System.out.println(fname + " " + lname + " has been allocated the role of Archer!\n");
                    this.villagers.add(new Archer(fname, lname, villAge));
                    break;
            }
            
        }
        //scanner.close();
        //create buildings, 1 castle, 2 farms, 1 blacksmithshop, 2 houses and an archer tower
        this.buildings.add(new Castle(true));
        this.buildings.add(new Farm(false));
        this.buildings.add(new BlackSmithShop(true));
        this.buildings.add(new ArcherTower(true));
        this.buildings.add(new House(true));
    }

    public String getName() {
        return name;
    }
    public List<Villager> getVillagers() {
        return villagers;
    }
    public List<Building> getBulidings() {
        return buildings;
    }
}
