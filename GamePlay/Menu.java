package GamePlay;

import java.util.Scanner;

import Territory.Territory;

import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import Villagers.*;

public class Menu {

    private boolean exit = false;
    private List<Map> games = new ArrayList<Map>();

    public Menu() {
        System.out.println("\n###### WELCOME TO MEDIEVAL ASSASSIN ######\n");
    }
    public void setExit() {
        exit = true;
    }
    public boolean isExit() {
        return exit;
    }
    /**
     * A function for producing a main menu
     */
    public void main() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validCoice = false;
        do {
            //potential to add load game, load a game from the Map list games
            System.out.println("###########################################");
            System.out.println("#        PLEASE CHOOSE AN OPTION          #");
            System.out.println("#-----------------------------------------#");
            System.out.println("#                                         #");
            System.out.println("# 1: NEW GAME                             #");
            System.out.println("#                                         #");
            System.out.println("# 2: INSTRUCTIONS                         #");
            System.out.println("#                                         #");
            System.out.println("# 0: EXIT                                 #");
            System.out.println("#                                         #");
            System.out.println("###########################################");
            System.out.print("YOUR SELECTION: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >=0 && choice < 3) {
                    validCoice = true;
                }
                else {
                    System.out.println("\nOops! please choose an integer that corresponds to a menu option i.e. 1 for a new game\n");
                    scanner.nextLine();
                }
            }
            else {
                System.out.println("\nOops! please choose an integer that corresponds to a menu option i.e. 1 for a new game\n");
                scanner.nextLine();
            }
        } while (!validCoice);
        switch(choice) {
            case 1: 
                newGame();
                break;
            case 2:
                instructionsMenu();
                break;
            case 0:
                exitMenu();
                break;
        }
        scanner.close();
    }
    public void newGame() {
        System.out.println("New Game created...");
        Map map = new Map();
        games.add(map);
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWhat is the first name of your character?: ");
        String fname = scanner.nextLine();
        System.out.print("\nWhat is the last name of your character?: ");
        String lname = scanner.nextLine();
        boolean validAge = false;
        int age = 0;
        do {
            System.out.print("what is the age of your character?: ");
            try {
                age = scanner.nextInt();
                if (age > 0 && age < 121) {
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
        //create player character
        Player user = new Player(fname, lname, age);
        //add player to user territory villager list
        List<Territory> territories = map.getTerritories();
        territories.get(0).addVilliger(user);
        // place player in user territory
        map.placePlayer(user, 0, Map.X/2, 0, Map.Y/2);
        map.printMap();
        scanner.close();
    }
    public void instructionsMenu() {
        System.out.println("Welcome to the instructions menu...");
    }
    public void exitMenu() {
        System.out.println("Thanks for playing medieval assassin..");
        setExit();
    }
    public List<Map> getMaps() {
        return games;
    }
}
