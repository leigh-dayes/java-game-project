package Villagers;

import java.util.Random;

public class Blacksmith extends Villager {

    private BlacksmithRanking ranking;
    //prefer not to hardcode this might move enum to this file
    // and use BlacksmithRanking.values().length()
    int numRankings = 3;

    public Blacksmith(String FirstName, String LastName, int Age) {
        super(FirstName, LastName, Age);
        generateRanking();
    }
    /**
     * A function for generating the ranking of a Blacksmith
     */
    public void generateRanking(){
        Random rand = new Random();
        int selection = rand.nextInt(numRankings);
        switch (selection) {
            case 0:
                ranking = BlacksmithRanking.apprentice;
                break;
            case 1:
                ranking = BlacksmithRanking.journeyman;
                break;
            case 2:
                ranking = BlacksmithRanking.master_smith;
                break;
            default:
                ranking = BlacksmithRanking.apprentice;
        }
    }
    public BlacksmithRanking getRanking() {
        return ranking;
    }
    public void setRanking(BlacksmithRanking rank) {
        ranking = rank;
    }
    public void printInfo() {
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Age: " + getAge());
        System.out.println(getFirstName() + " " + getLastName() + " is Blacksmith rank: " + ranking.toString());
    }
}
