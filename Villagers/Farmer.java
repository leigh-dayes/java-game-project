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

    public Farmer(String FirstName, String LastName, int Age) {
        super(FirstName, LastName, Age);
        generateType();
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
}
