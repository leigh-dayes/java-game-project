package Villagers;

import java.util.Scanner;
import java.util.Random;

public class Knight extends Villager {

    //private Weapons weapon;
    private int strength;
    //private Armour armour;
    private int numWeapons = 5;
    private int numArmour = 3;
    private String[] occupationStrings = {
        "I am my enemies worst nightmare, a royal Knight",
        "I solve other peoples problems with violence, I am a Knight",
        "Get in my way and feel my wrath, I am a Knight"
    };
    public String[] adviceStrings = {
        "Someone as small as you could never be a knight!",
        "If you venturing to the South East, you better be prepared, those barbarians will make light work of you!",
        "I've heard the King in the South hides in his castle, sorrounded by many men!",
        "You need to build an army if you want to take on the men in the south, a knight can be bought.. for a price."
    };

    public Knight(String FirstName, String LastName, int Age) {
        super(FirstName, LastName, Age);
        generateStrength();
        setKnightWeapon();
        setKnightArmour();
        //System.out.println("\n** Knight Created **\n");
        //printInfo();
    }
    //getters
    
    public int getStrength() {
        return strength;
    }
    //setters
    public void setKnightWeapon() {
        Random rand = new Random();
        int selection = rand.nextInt(numWeapons);
        switch(selection) {
            case 0:
                setWeapon(Weapons.Club);
                break;
            case 1:
                setWeapon(Weapons.Dagger);
                break;
            case 2:
                setWeapon(Weapons.Lance);
                break;
            case 3:
                setWeapon(Weapons.Mace);
                break;
            case 4:
                setWeapon(Weapons.Sword);
                break;
        }

    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setKnightArmour() {
        Random rand = new Random();
        int selection = rand.nextInt(numArmour);
        switch(selection) {
            case 0:
                setArmour(Armour.leather);
                break;
            case 1:
                setArmour(Armour.steel);
                break;
            case 2:
                setArmour(Armour.none);
                break;
        }
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
                setWeapon(w);
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
                setArmour(a);
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
    public void speakOccupation() {
        Random rand = new Random();
        System.out.println(occupationStrings[rand.nextInt(occupationStrings.length)]);
    }
    public void giveAdvice() {
        Random rand = new Random();
        System.out.println(adviceStrings[rand.nextInt(adviceStrings.length)]);
    }
}
