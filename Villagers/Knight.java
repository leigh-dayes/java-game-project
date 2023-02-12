package Villagers;

import java.util.Scanner;
import java.util.Random;

public class Knight extends Villager {

    private Weapons weapon;
    private int strength;
    private Armour armour;

    public Knight(String FirstName, String LastName, int Age) {
        super(FirstName, LastName, Age);
        generateStrength();
        selectWeapon();
        selectArmour();
        System.out.println("\n** Knight Created **\n");
        //printInfo();
    }
    //getters
    public Weapons getWeapon() {
        return weapon;
    }
    public int getStrength() {
        return strength;
    }
    public Armour getArmour() {
        return armour;
    }
    //setters
    public void setWeapon() {
        selectWeapon();
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setArmour() {
        selectArmour();
    }
    /**
     * A function for selecting a knights weapon
     */
    public void selectWeapon() {
        System.out.println("\n** Welcome to ye olde Weapon Shop **");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select a Weapon ");
        boolean validWeapon = false;
        int i = 1;
        for(Weapons w: Weapons.values()){
            System.out.println(i + ": " + w);
            i++;
        }
        System.out.print("Selection: ");
        String weap = scanner.nextLine();
        for (Weapons w: Weapons.values()){
            if (weap.equalsIgnoreCase(w.toString())){
                weapon = w;
                System.out.println(getFirstName() + " has choosen a " + weap);
                validWeapon = true;
                break;
            }
        }
        if (!validWeapon) {
            System.out.println("Oops we dont have " + weap + ", please choose a valid weapon i.e. Sword");
            selectWeapon();
        }
        //scanner.close();
    }
    /**
     * A function for selecting a knights armour
     */
    public void selectArmour() {
        System.out.println("\n** Welcome to ye olde Armour Shop **");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select a piece of Armour ");
        boolean validArmour = false;
        int i = 1;
        for(Armour a: Armour.values()){
            System.out.println(i + ": " + a);
            i++;
        }
        System.out.print("Selection: ");
        String arm = scanner.nextLine();
        for (Armour a: Armour.values()){
            if (arm.equalsIgnoreCase(a.toString())){
                armour = a;
                System.out.println(getFirstName() + " has choosen " + arm + " armour!");
                validArmour = true;
                break;
            }
        }
        if (!validArmour) {
            System.out.println("Oops we dont have " + arm + ", please choose valid amour i.e. leather");
            selectArmour();
        }
        // scanner.close();
    }
    /**
     * A function for printing out knights details
     */
    public void printInfo() {
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Age: " + getAge());
        System.out.println("Strength: " + getStrength());
        System.out.println("Weapon: " + getWeapon().toString());
        System.out.println("Armour: " + getArmour().toString());
    }
    /**
     * A function to randomly generate the knights strength
     */
    public void generateStrength() {
        Random rand = new Random();
        strength = rand.nextInt(1, 10);
    }
}
