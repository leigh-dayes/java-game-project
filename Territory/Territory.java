package Territory;

import java.util.ArrayList;
import java.util.List;
import Villagers.*;
import java.util.Scanner;
import java.util.Random;

public class Territory {
    private String name;
    private List<Villager> villagers = new ArrayList<Villager>();
    // number of diffent types of villigers
    private int numVillTypes = 3;

    public Territory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWhat is the name of your Territory?: ");
        this.name = scanner.nextLine();
        boolean validNum = false;
        while (!validNum) {
            System.out.print("\nHow many Villagers live in " + name + ": ");
            if (scanner.hasNextInt()) {
                int numV = scanner.nextInt();
                if (numV > 0) {
                    populateTerritory(numV);
                    validNum = true;
                }
                else {
                    System.out.println("Whoops must have atleast 1 villiger to occupy a territory!");
                }
            }
            else {
                System.out.println("Whoops please enter a whole number greater than 0");
            }
        }
        scanner.close();
    }
    /**
     * A function to populate a new territory
     */
    private void populateTerritory(int numVillagers){
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= numVillagers; i++) {
            System.out.print("Please enter the First Name of villiger number " + i + ": ");
            String fname = scanner.nextLine();
            System.out.print("Please enter the Last Name of villiger number " + i + ": ");
            String lname = scanner.nextLine();
            boolean validAge = false;
            while (!validAge) {
                System.out.print("Please enter the age of " + fname + " " + lname + ": ");
                if (scanner.hasNextInt()) {
                    int villAge = scanner.nextInt();
                    if (villAge >= 0 && villAge < 121) {
                        validAge = true;
                    }
                    else {
                        System.out.print("Please enter a valid age between 0 and 120");
                    }
                }else {
                    System.out.print("Please enter a valid number for age, between 0 and 120");
                }
            }
            Random rand = new Random();
            int age = rand.nextInt(120);
            // randomly assign characters to be Knights, Farmers or Blacksmiths
            int selection = rand.nextInt(numVillTypes);
            switch (selection) {
                case 0:
                    this.villagers.add(new Knight(fname, lname, age));
                    break;
                case 1:
                    this.villagers.add(new Blacksmith(fname, lname, age));
                    break;
                case 2:
                    this.villagers.add(new Farmer(fname, lname, age));
                    break;
            }
            
        }
        scanner.close();
    }
}
