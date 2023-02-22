package Villagers;

import java.util.Random;

public class Archer extends Villager {

    private int range;
    private int accuracy;
    private int maxRange = 5;
    private int minRange = 1;
    private int minAccuracy = 3;
    private int maxAccuracy = 10;
    private String[] occupationStrings = {
        "I am the finest archer in all the land",
        "I am a Bowman of note",
        "I am an Archer same as my father and his father and his.. fathers father",
    };
    private String[] adviceStrings = {
        "I care not for helping you!",
        "You want my advice? go back home to mummy",
    };
    public Archer(String FirstName, String LastName, int Age) {
        super(FirstName, LastName, Age);
        generateAbilities();
        setWeapon(Weapons.Bow);
        setArmour(Armour.leather);
    }
    public int getRange() {
        return range;
    }
    public int getAccuracy() {
        return accuracy;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
    public void generateAbilities() {
        Random rand = new Random();
        this.range = rand.nextInt(minRange, maxRange+1);
        this.accuracy = rand.nextInt(minAccuracy, maxAccuracy+1);
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
