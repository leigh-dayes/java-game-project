package GamePlay;

import java.util.Scanner;
import Territory.Territory;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import Villagers.*;
import Buildings.*;

public class Menu {

    private boolean exit = false;
    private List<Map> games = new ArrayList<Map>();
    private Map currentGame;
    private List<Object> closeVillagers = new ArrayList<Object>();
    private List<Object> closeBuildings = new ArrayList<Object>();
    private Player user;

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
    public int main() {
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
            System.out.println("# 0: EXIT                                 #");
            System.out.println("#                                         #");
            System.out.println("###########################################");
            System.out.print("YOUR SELECTION: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >=0 && choice < 2) {
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
        //scanner.close();
        return choice;
    }
    /**
     * A menu for creating a new game
     */
    public void newGame() {
        System.out.println("New Game created...");
        Map map = new Map();
        games.add(map);
        currentGame = map;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWhat is the first name of your character?: ");
        String fname = scanner.nextLine();
        System.out.print("\nWhat is the last name of your character?: ");
        String lname = scanner.nextLine();
        boolean validAge = false;
        int age = 0;
        do {
            System.out.print("\nwhat is the age of your character?: ");
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
        this.user = user;
        // place player in user territory
        map.placePlayer(user, 0, Map.X/2, 0, Map.Y/2);
        map.printMap();
        //scanner.close();
        System.out.println("\nYou awaken on the ground, your head hurts.. you cant remember a thing.. ");
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
    /**
     * The main game play menu for exploring the map
     */
    public void gamePlayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean isVillagerOption = false;
        boolean isBuildingOption = false;
        boolean validCoice = false;
        do {
            System.out.println("\n###########################################");
            System.out.println("#        PLEASE CHOOSE AN OPTION          #");
            System.out.println("#-----------------------------------------#");
            System.out.println("#                                         #");
            System.out.println("# 1: HELP ME                              #");
            System.out.println("#                                         #");
            System.out.println("# 2: MOVE NORTH                           #");
            System.out.println("#                                         #");
            System.out.println("# 3: MOVE EAST                            #");
            System.out.println("#                                         #");
            System.out.println("# 4: MOVE SOUTH                           #");
            System.out.println("#                                         #");
            System.out.println("# 5: MOVE WEST                            #");
            System.out.println("#                                         #");
            if (nearSomething(user.getLocation()[0], user.getLocation()[1])){
                if (!closeBuildings.isEmpty() && !closeVillagers.isEmpty()){
                    System.out.println("# 6: ENTER BUILDING                       #");
                    System.out.println("#                                         #");
                    System.out.println("# 7: TALK TO VILLAGER                     #");
                    System.out.println("#                                         #");
                }
                else if (!closeVillagers.isEmpty()) {
                    System.out.println("# 7: TALK TO VILLAGER                     #");
                    System.out.println("#                                         #");
                }
                else if (!closeBuildings.isEmpty()) {
                    System.out.println("# 6: ENTER BUILDING                       #");
                    System.out.println("#                                         #");
                }
            }
            System.out.println("# 0: EXIT                                 #");
            System.out.println("#                                         #");
            System.out.println("###########################################");
            System.out.println("x-loc: " + user.getLocation()[0] + "   y-loc: " +user.getLocation()[1]);
            System.out.print("YOUR SELECTION: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >=0 && choice < 8) {
                    validCoice = true;
                    //scanner.nextLine();
                }
                else {
                    System.out.println("\nOops! please choose an integer that corresponds to a menu option i.e. 1 for help menu\n");
                    //scanner.nextLine();
                }
            }
            else {
                System.out.println("\nOops! please choose an integer that corresponds to a menu option i.e. 1 for help main\n");
                //scanner.nextLine();
            }
            scanner.nextLine();
        } while (!validCoice);
        switch(choice) {
            case 1: 
                helpMe();
                break;
            case 2:
                movePlayer("NORTH");
                break;
            case 3:
                movePlayer("EAST");
                break;
            case 4:
                movePlayer("SOUTH");
                break;
            case 5:
                movePlayer("WEST");
                break;
            case 6:
                break;
            case 7:
                break;
            case 0:
                exitMenu();
                break;
        }
        scanner.close();
    }
    /**
     * A function that checks the sorroundings of a player and updates closeVillagers
     * and closeBuildings list as applicable to enable player interaction with the world
     * 
     * @param x x-location of player
     * @param y y-location of player
     * 
     * @return true if a building or villager is close to player else false
     */
    public boolean nearSomething(int x, int y) {
        boolean somethingClose = false;
        closeBuildings.clear();
        closeVillagers.clear();
        //pretty gross function, need to make sure i dont get an index out of bounds exception
        //north
        if(x-1 >= 0) {
            if (currentGame.getWorld()[x-1][y] instanceof Building) {
                somethingClose = true;
                closeBuildings.add(currentGame.getWorld()[x-1][y]);
            } 
            else if (currentGame.getWorld()[x-1][y] instanceof Villager) {
                somethingClose = true;
                closeVillagers.add(currentGame.getWorld()[x-1][y]);
            }
        }
        
        //north east
        if (x-1 >= 0 && y+1 < Map.Y) {
            if (currentGame.getWorld()[x-1][y+1] instanceof Building) {
                somethingClose = true;
                closeBuildings.add(currentGame.getWorld()[x-1][y+1]);
            }
            else if (currentGame.getWorld()[x-1][y+1] instanceof Villager) {
                somethingClose = true;
                closeVillagers.add(currentGame.getWorld()[x-1][y+1]);
            }
        }

        //east
        if (y+1 < Map.Y) {
            if (currentGame.getWorld()[x][y+1] instanceof Building) {
                somethingClose = true;
                closeBuildings.add(currentGame.getWorld()[x][y+1]);
            }
            else if (currentGame.getWorld()[x][y+1] instanceof Villager) {
                somethingClose = true;
                closeVillagers.add(currentGame.getWorld()[x][y+1]);
            }
        }
        
        //south east
        if (x+1 < Map.X && y+1 < Map.Y) {
            if (currentGame.getWorld()[x+1][y+1] instanceof Building) {
                somethingClose = true;
                closeBuildings.add(currentGame.getWorld()[x+1][y+1]);
            }
            else if (currentGame.getWorld()[x+1][y+1] instanceof Villager) {
                somethingClose = true;
                closeVillagers.add(currentGame.getWorld()[x+1][y+1]);
            }
        }
        
        //south
        if (x+1 < Map.X) {
            if (currentGame.getWorld()[x+1][y] instanceof Building) {
                somethingClose = true;
                closeBuildings.add(currentGame.getWorld()[x+1][y]);
            }
            else if (currentGame.getWorld()[x+1][y] instanceof Villager) {
                somethingClose = true;
                closeVillagers.add(currentGame.getWorld()[x+1][y]);
            }
        }
        
        //south west
        if (x+1 < Map.X && y-1 >= 0) {
            if (currentGame.getWorld()[x+1][y-1] instanceof Building) {
                somethingClose = true;
                closeBuildings.add(currentGame.getWorld()[x+1][y-1]);
            }
            else if (currentGame.getWorld()[x+1][y-1] instanceof Villager) {
                somethingClose = true;
                closeVillagers.add(currentGame.getWorld()[x+1][y-1]);
            }
        }
        
        //west
        if (y-1 >= 0) {
            if (currentGame.getWorld()[x][y-1] instanceof Building) {
                somethingClose = true;
                closeBuildings.add(currentGame.getWorld()[x][y-1]);
            }
            else if (currentGame.getWorld()[x][y-1] instanceof Villager) {
                somethingClose = true;
                closeVillagers.add(currentGame.getWorld()[x][y-1]);
            }
        }
        
        //north west
        if (x-1 >= 0 && y-1 >= 0) {
            if (currentGame.getWorld()[x-1][y-1] instanceof Building) {
                somethingClose = true;
                closeBuildings.add(currentGame.getWorld()[x-1][y-1]);
            }
            else if (currentGame.getWorld()[x-1][y-1] instanceof Villager) {
                somethingClose = true;
                closeVillagers.add(currentGame.getWorld()[x-1][y-1]);
            }
        }
        
        return somethingClose;
    }
    /**
     * A menu to help players understand the game
     */
    public void helpMe() {
        System.out.println("\n###########################################");
        System.out.println("#              HELP ME!!                  #");
        System.out.println("#-----------------------------------------#");
        System.out.println("# The aim of the game is to assassinate   #");
        System.out.println("# the enemy king! to do this you must     #");
        System.out.println("# navigate your way to the enemy castle.  #");
        System.out.println("#                                         #");
        System.out.println("# you will need to equip yourself with a  #");
        System.out.println("# weapon and may need to fight off enemy  #");
        System.out.println("# knights along the way!                  #");
        System.out.println("#-----------------------------------------#");
        System.out.println("#             MAP LEGEND                  #");
        System.out.println("#-----------------------------------------#");
        System.out.println("# A/ua - user archer tower                #");
        System.out.println("# C/uc - user castle                      #");
        System.out.println("# F/uf - user farm                        #");
        System.out.println("# B/ub - user blacksmith shop             #");
        System.out.println("# H/uh - user house                       #");
        System.out.println("#                                         #");
        System.out.println("# A/ea - enemy archer tower               #");
        System.out.println("# C/ec - enemy castle                     #");
        System.out.println("# F/ef - enemy farm                       #");
        System.out.println("# B/eb - enemy blacksmith shop            #");
        System.out.println("# H/eh - enemy house                      #");
        System.out.println("#                                         #");
        System.out.println("# P - player (you)                        #");
        System.out.println("# a - archer                              #");
        System.out.println("# k - knight                              #");
        System.out.println("# b - blacksmith                          #");
        System.out.println("# f - farmer                              #");
        System.out.println("# . - blank space                         #");
        System.out.println("###########################################");
        gamePlayMenu();
    }
    public void movePlayer(String direction) {
        int x = user.getLocation()[0];
        int y = user.getLocation()[1];
        Object[][] world = currentGame.getWorld();
        switch(direction) {
            case "NORTH":
                if (x > 0) {
                    if (world[x-1][y].equals(" . ")) {
                        user.setLocation(x - 1, y);
                        currentGame.setPosition(x , y," . ");
                        currentGame.setPosition(x-1, y, user);
                    }
                    else {
                        if (world[x-1][y] instanceof Building) {
                           //some logic to interact with building
                           //need to account for strings that represent buildings
                           System.out.println("Entered a building.. ");
                           user.setLocation(x - 1, y);
                           currentGame.setPosition(x , y," . ");
                           currentGame.setPosition(x-1, y, user);
                        }
                        // not building or empty space must be a villager
                        else {
                            System.out.println("Talk to a villiger!.. ");
                        }
                    }
                }
                else {
                    System.out.println("\nWhat lies to the North of you cannot be explored...");
                }
                break;

                case "EAST":
                    if (y <= Map.Y - 1) {
                        if (world[x][y+1].equals(" . ")) {
                            user.setLocation(x, y+1);
                            currentGame.setPosition(x , y," . ");
                            currentGame.setPosition(x, y + 1, user);
                        }
                        else {
                            if (world[x][y+1] instanceof Building) {
                            //some logic to interact with building
                            System.out.println("Entered a building.. ");
                            user.setLocation(x, y + 1);
                            currentGame.setPosition(x , y," . ");
                            currentGame.setPosition(x, y + 1, user);
                            }
                            // not building or empty space must be a villager
                            else {
                                System.out.println("Talk to a villiger!.. ");
                            }
                        }
                    }
                    else {
                        System.out.println("\nWhat lies to the East of you cannot be explored...");
                    }
                    break;
                
                case "SOUTH":
                    if (x < Map.X -1 ) {
                        if (world[x+1][y].equals(" . ")) {
                            user.setLocation(x+1, y);
                            currentGame.setPosition(x , y," . ");
                            currentGame.setPosition(x + 1, y, user);
                        }
                        else {
                            if (world[x+1][y] instanceof Building) {
                            //some logic to interact with building
                            System.out.println("Entered a building.. ");
                            user.setLocation(x+1, y);
                            currentGame.setPosition(x , y," . ");
                            currentGame.setPosition(x+1, y, user);
                            }
                            // not building or empty space must be a villager
                            else {
                                System.out.println("Talk to a villiger!.. ");
                            }
                        }
                    }
                    else {
                        System.out.println("\nWhat lies to the South of you cannot be explored...");
                    }
                    break;
                
                case "WEST":
                    if (y > 0) {
                        if (world[x][y-1].equals(" . ")) {
                            user.setLocation(x, y-1);
                            currentGame.setPosition(x , y," . ");
                            currentGame.setPosition(x, y-1, user);
                        }
                        else {
                            if (world[x][y-1] instanceof Building) {
                            //some logic to interact with building
                            System.out.println("Entered a building.. ");
                            user.setLocation(x, y-1);
                            currentGame.setPosition(x , y," . ");
                            currentGame.setPosition(x, y-1, user);
                            }
                            // not building or empty space must be a villager
                            else {
                                System.out.println("Talk to a villiger!.. ");
                            }
                        }
                    }
                    else {
                        System.out.println("\nWhat lies to the West of you cannot be explored...");
                    }
                break;
        }
        currentGame.printMiniMap(user);
        gamePlayMenu();
    }
}
