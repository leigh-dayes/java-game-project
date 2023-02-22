package Villagers;

import java.util.Random;

public class Farmer extends Villager {

    private FarmerType type;
    private int numTypes = 3;
    private String[] occupationStrings = {
        "I produce the food that keeps this kingdom running, I'm a Farmer",
        "I work in the dirt and put food on the table, a Farmer",
        "my mother sewed the seeds that made this great kingdom of ours, I continue her legacy as a Farmer"
    };
    private String[] adviceStrings = {
        "I do know one thing, farming may be difficult, but it pays the bills",
        "My freind is always looking for an extra hand on the local farm, he pays $'s",
        "My advice to you is to head to the farm, earn an honest dollar"
    };

    public Farmer(String FirstName, String LastName, int Age) {
        super(FirstName, LastName, Age);
        generateType();
        setWeapon(Weapons.Fists);
        setArmour(Armour.none);
    }
    public void generateType() {
        Random rand = new Random();
        int selection = rand.nextInt(numTypes);
        switch(selection){
            case 0:
                type = FarmerType.cattle;
                break;
            case 1:
                type = FarmerType.grain;
                break;
            case 2:
                type = FarmerType.vegetable;
                break;
        }
    }
    public FarmerType getFarmerType() {
        return type;
    }
    public void setFarmerType(FarmerType ft) {
        type = ft;
    }
    public void printInfo() {
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Age: " + getAge());
        System.out.println(getFirstName() + " " + getLastName() + " is a " + type.toString() + " farmer.");
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
