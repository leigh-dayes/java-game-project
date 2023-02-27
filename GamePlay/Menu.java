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
    private List<Villager> allies = new ArrayList<Villager>();
    private List<Villager> enemies = new ArrayList<Villager>();
    private Player user;
    private int[] lastPlayerPosition = new int[2];
    //bonuses for capturing buildings
    private int aBonus = 40;
    private int fBonus = 10;
    private int bBonus = 25;
    private int hBonus = 10;

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
        //map.printMap();
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
        if (!isExit()) {
            currentGame.printMiniMap(user);
            Scanner scanner = new Scanner(System.in);
            int choice = 0;
            boolean isVillagerOption = false;
            boolean isBuildingOption = false;
            boolean validCoice = false;
            do {
                System.out.println("\n###########################################");
                System.out.println("#        PLEASE CHOOSE AN OPTION          #");
                System.out.println("#-----------------------------------------#");
                System.out.println("#  <3 = " + user.getHealth() + "%" + "      $ = " + user.getWallet() + "                   #");
                System.out.println("#                                         #");
                System.out.println("# 1: HELP ME                              #");
                System.out.println("#                                         #");
                System.out.println("# 2: MOVE NORTH   ^                       #");
                System.out.println("#                                         #");
                System.out.println("# 3: MOVE EAST    >                       #");
                System.out.println("#                                         #");
                System.out.println("# 4: MOVE SOUTH   v                       #");
                System.out.println("#                                         #");
                System.out.println("# 5: MOVE WEST    <                       #");
                System.out.println("#                                         #");
                if (nearSomething(user.getLocation()[0], user.getLocation()[1])){
                    if (!closeBuildings.isEmpty() && !closeVillagers.isEmpty()){
                        System.out.println("# 6: ENTER BUILDING                       #");
                        System.out.println("#                                         #");
                        System.out.println("# 7: TALK TO VILLAGER                     #");
                        System.out.println("#                                         #");
                        isBuildingOption = true;
                        isVillagerOption = true;
                    }
                    else if (!closeVillagers.isEmpty()) {
                        System.out.println("# 7: TALK TO VILLAGER                     #");
                        System.out.println("#                                         #");
                        isVillagerOption = true;
                        isBuildingOption = false;
                    }
                    else if (!closeBuildings.isEmpty()) {
                        System.out.println("# 6: ENTER BUILDING                       #");
                        System.out.println("#                                         #");
                        isBuildingOption = true;
                        isVillagerOption = false;
                    }
                }else {
                    isBuildingOption = false;
                    isVillagerOption = false;
                }
                System.out.println("# 0: EXIT                                 #");
                System.out.println("#                                         #");
                System.out.println("###########################################");
                //System.out.println("x-loc: " + user.getLocation()[0] + "   y-loc: " +user.getLocation()[1]);
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
                    if (isBuildingOption) {
                        enterBuilding();
                    } else {
                        System.out.println("\nYou need to be near a building to enter it!");
                        gamePlayMenu();
                    }
                    break;
                case 7:
                    if(isVillagerOption) {
                        sayHello();
                        interactWithVillager();
                    } else {
                        System.out.println("\nYou need to be near a villager to talk to them!");
                        gamePlayMenu();
                    }
                    
                    break;
                case 0:
                    exitMenu();
                    break;
                }
            scanner.close();
        }
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
            //building strings
            else if (!currentGame.getWorld()[x-1][y].equals(" . ")) {
                somethingClose = true;
                String str = (String) currentGame.getWorld()[x-1][y];
                closeBuildings.add(bStrToBuilding(x-1,y,str));
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
            //building strings
            else if (!currentGame.getWorld()[x-1][y+1].equals(" . ")) {
                somethingClose = true;
                String str = (String) currentGame.getWorld()[x-1][y+1];
                closeBuildings.add(bStrToBuilding(x-1,y+1,str));
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
            //building strings
            else if (!currentGame.getWorld()[x][y+1].equals(" . ")) {
                somethingClose = true;
                String str = (String) currentGame.getWorld()[x][y+1];
                closeBuildings.add(bStrToBuilding(x,y+1,str));
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
            //building strings
            else if (!currentGame.getWorld()[x+1][y+1].equals(" . ")) {
                somethingClose = true;
                String str = (String) currentGame.getWorld()[x+1][y+1];
                closeBuildings.add(bStrToBuilding(x+1,y+1,str));
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
            //building strings
            else if (!currentGame.getWorld()[x+1][y].equals(" . ")) {
                somethingClose = true;
                String str = (String) currentGame.getWorld()[x+1][y];
                closeBuildings.add(bStrToBuilding(x+1,y,str));
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
            //building strings
            else if (!currentGame.getWorld()[x+1][y-1].equals(" . ")) {
                somethingClose = true;
                String str = (String) currentGame.getWorld()[x+1][y-1];
                closeBuildings.add(bStrToBuilding(x+1,y-1,str));
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
            //building strings
            else if (!currentGame.getWorld()[x][y-1].equals(" . ")) {
                somethingClose = true;
                String str = (String) currentGame.getWorld()[x][y-1];
                closeBuildings.add(bStrToBuilding(x,y-1,str));
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
            //building strings
            else if (!currentGame.getWorld()[x-1][y-1].equals(" . ")) {
                somethingClose = true;
                String str = (String) currentGame.getWorld()[x-1][y-1];
                closeBuildings.add(bStrToBuilding(x-1,y-1,str));
            }
        }
        
        return somethingClose;
    }
    /**
     * A function to detect the object that is represented by building strings
     * 
     * 
     */
    public Object bStrToBuilding(int x, int y, String str){
        Object building = " . ";
        // building object is always placed top left
        // iterate up rows until top edge of building
        boolean xfound = false;
        boolean foundBuilding = false;
        while(!xfound) {
            //still going up
            if(currentGame.getWorld()[x-1][y] == str) {
                x = x-1;
            }
            //found building
            else if (currentGame.getWorld()[x-1][y] instanceof Building) {
                building = currentGame.getWorld()[x-1][y];
                xfound = true;
                foundBuilding = true;
            }
            else {
                xfound = true;
            }
        }
        while(!foundBuilding) {
            // still going left
            if(currentGame.getWorld()[x][y-1] == str) {
                y = y-1;
            }
            //found building
            else {
                building = currentGame.getWorld()[x][y-1];
                foundBuilding= true;
            }

        }
        return building;
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
        System.out.println("#             MENU ITEMS                  #");
        System.out.println("#-----------------------------------------#");
        System.out.println("# <3 - life percentage left               #");
        System.out.println("# $  - amount of money in wallet          #");
        System.out.println("#                                         #");
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
        System.out.println("# - - off the map                         #");
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
        gamePlayMenu();
    }
    public void interactWithVillager() {
        //currentGame.printMiniMap(user);
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validCoice = false;
        do {
            System.out.println("\n###########################################");
            System.out.println("#        PLEASE CHOOSE AN OPTION          #");
            System.out.println("#-----------------------------------------#");
            System.out.println("#  <3 = " + user.getHealth() + "%" + "      $ = " + user.getWallet() + "                   #");
            System.out.println("#                                         #");
            System.out.println("# 1: ASK VILLAGER THEIR OCCUPATION        #");
            System.out.println("#                                         #");
            System.out.println("# 2: ASK VILLAGER FOR ADVICE              #");
            System.out.println("#                                         #");
            System.out.println("# 3: FIGHT VILLAGER                       #");
            System.out.println("#                                         #");
            System.out.println("# 4: ASK VILLAGER TO BE YOUR ALLY         #");
            System.out.println("#                                         #");
            System.out.println("# 0: EXIT                                 #");
            System.out.println("#                                         #");
            System.out.println("###########################################");
            //System.out.println("x-loc: " + user.getLocation()[0] + "   y-loc: " +user.getLocation()[1]);
            System.out.print("YOUR SELECTION: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >=0 && choice < 5) {
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
                getOccupation();
                break;
            case 2:
                getSomeAdvice();
                break;
            case 3:
                doFighting(false, false);
                break;
            case 4:
                recruitAllies();
                break;
            case 0:
                gamePlayMenu();
                break;
        }
        //scanner.close();
        gamePlayMenu();
    }
    /**
     * A function for answering occupation
     */
    public void getOccupation() {
        int i = 1;
        for (Object v: closeVillagers) {
            System.out.print("\nVILLAGER " + i + ": ");
            if (v instanceof Archer) {
                Archer a = (Archer) v;
                a.speakOccupation();
            }
            else if (v instanceof Blacksmith) {
                Blacksmith b = (Blacksmith) v;
                b.speakOccupation();
            }
            else if (v instanceof Farmer) {
                Farmer f = (Farmer) v;
                f.speakOccupation();
            }
            else if (v instanceof Knight) {
                Knight k = (Knight) v;
                k.speakOccupation();
            }
            i++;
        }
        interactWithVillager();
    }
    /**
     * A funciton to say villagers names
     */
    public void sayHello() {
        int i = 1;
        for (Object v : closeVillagers) {
            Villager vill = (Villager) v;
            System.out.print("\nVILLAGER " + i + ": ");
            vill.sayHello();
            i++;
        }
    }
    /**
     * A function to use the fight class and set oponenets
     */
    public void doFighting(boolean inBuilding, boolean king) {
        boolean inB = inBuilding;
        // assign close villagers as enemies
        for ( Object v : closeVillagers) {
            Villager vill = (Villager) v;
            enemies.add(vill);
        }
        //empty the close villagers list, as it will be repopulated with only living villagers post fight
        closeVillagers.clear();
        Fight fight = new Fight(user);
        fight.setFriendlies(allies);
        fight.setEnemies(enemies);
        fight.fighting();
        if (fight.userAlive()) {
            // if not in a building just return to where they were
            if (!inB) {
                gamePlayMenu();
            }
            else {
                //handle in building stuff, i.e. fight the king
                if(king) {
                    System.out.println("\nYou have assassinated the King!! and beat the game!!");
                    System.out.println("##### WELL DONE ######");
                    setExit();
                    gamePlayMenu();
                }
                else {
                    inBuildingVictory();
                }
            }
        }
        else {
            System.out.println("It would seem you were bested! better luck next time");
            setExit();
            gamePlayMenu();
        }
    }
    /**
     * A function for returning advice from villagers
     */
    public void getSomeAdvice() {
        System.out.println("\nYou ask for some advice.");
        int i = 1;
        for (Object v : closeVillagers) {
            System.out.print("\nVILLAGER " + i + ": ");
            if (v instanceof Archer) {
                Archer vill = (Archer) v;
                vill.giveAdvice();
            }
            else if (v instanceof Blacksmith) {
                Blacksmith vill = (Blacksmith) v;
                vill.giveAdvice();
            }
            else if (v instanceof Farmer) {
                Farmer vill = (Farmer) v;
                vill.giveAdvice();
            }
            else if (v instanceof Knight) {
                Knight vill = (Knight) v;
                vill.giveAdvice();
            }
            i++;
        }
        interactWithVillager();
    }
    /**
     * A function to assign allies
     */
    public void recruitAllies() {
        System.out.println("\nYou: Will you join me on my noble quest?");
        int i = 1;
        for (Object v : closeVillagers) {
            Villager vill = (Villager) v;
            //check if villager is an enemy or friendly
            //enemy
            if (currentGame.getTerritories().get(1).getVillagers().contains(vill)) {
                System.out.println("\nVILLAGER " + i + ": You are my sworn enemy! i will not pledge alligence to you!");
                i++;
                enemies.add(vill);
                doFighting(false, false);
            }
            else {
                //freindly
                // farmers and blacksmiths dont join
                if (vill instanceof Farmer) {
                    System.out.println("\nVILLAGER "+ i + ": I am a farmer not a warrior, I cannot join you.");
                }
                else if (vill instanceof Blacksmith) {
                    System.out.println("\nVILLAGER "+ i + ": I am a blacksmith, I have no thirst for battle.");
                }
                else if (vill instanceof Archer) {
                    //make sure not already an ally
                    if (allies.contains(vill)) {
                        System.out.println("\nVILLAGER " + i + ": Have you hit your head? we are already allies remember?."); 
                    } else {
                        System.out.println("\nVILLAGER " + i + ": You have my bow, our quest will be epic.");
                        allies.add(vill);
                    }
                }
                else {
                    //knight
                    if (allies.contains(vill)) {
                        System.out.println("\nVILLAGER " + i + ": Have you hit your head? we are already allies remember?."); 
                    } else {
                        System.out.println("\nVILLAGER " + i + ": I will join you, they shall right songs about our great conquest.");
                        allies.add(vill);
                    }
                }
                i++;
            }
        }
        gamePlayMenu();
    }
    /**
     * A function for entering buildings
     */
    public void enterBuilding() {
        // remove player from map so they appear to be inside the building
        lastPlayerPosition[0] = user.getLocation()[0];
        lastPlayerPosition[1] = user.getLocation()[1];
        currentGame.setPosition(user.getLocation()[0], user.getLocation()[1], " . ");
        currentGame.printMiniMap(user);
        //friendly buildings
        List<Building> fBuildings = currentGame.getTerritories().get(0).getBulidings();
        //enemy buildings
        //List<Building> eBuildings = currentGame.getTerritories().get(1).getBulidings();
        for (int i = 0; i < closeBuildings.size(); i++) {
            Building building = (Building) closeBuildings.get(i);
            enemies.clear();
            //Archer Tower
            if (building instanceof ArcherTower) {
                ArcherTower at = (ArcherTower) building;
                List<Archer> archers = at.getArchers();
                //friendly
                if (fBuildings.contains(building)) {
                    archers.get(0).sayHello();
                    archers.get(1).sayHello();
                    System.out.println("\nWelcome to our Archer tower!");
                    System.out.println("\nWe think its time you leave.. Goodbye");
                    currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
                    gamePlayMenu();
                }
                else {
                    //enemy 
                    System.out.println("Hey! You're not supposed to be in here!!!");
                    enemies.add(archers.get(0));
                    enemies.add(archers.get(1));
                    doFighting(true, false);
                }
            }

            //Castle
            else if (building instanceof Castle) {
                Castle c = (Castle) building;
                List<Knight> kingsKnights = c.getKingsKnights();
                //friendly
                if (fBuildings.contains(building)) {
                    for (Knight k : kingsKnights) {
                        k.sayHello();
                    }
                    System.out.println("\nWelcome to our grand Castle.. You have no business with the king.. please leave");
                    currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
                    gamePlayMenu();
                }
                else {
                    //enemy 
                    System.out.println("Hey! You're not supposed to be in here!!!");
                    System.out.println("\nThe Kings gaurd is made up of " + kingsKnights.size() + " brave knights!, you will need to defeat all of us to make it to our king!");
                    for (Knight k : kingsKnights) {
                        enemies.add(k);
                    }
                    //currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
                    doFighting(true, false);
                }
            }

            //Blacksmith shop
            else if (building instanceof BlackSmithShop) {
                BlackSmithShop bs = (BlackSmithShop) building;
                if(fBuildings.contains(building)) {
                    bs.getBlacksmith().sayHello();
                    bs.buyWeapon(user);
                    currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
                    gamePlayMenu();
                }
                //enemy
                else {
                    System.out.println("Hey! You're not supposed to be in here!!!");
                    enemies.add(bs.getBlacksmith());
                    //currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
                    doFighting(true, false);
                }

            }

            // Farm
            else if (building instanceof Farm) {
                Farm f = (Farm) building;
                if(fBuildings.contains(building)) {
                    f.getFarmer().sayHello();
                    f.doWork(user);
                    currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
                    gamePlayMenu();
                }
                //enemy
                else {
                    System.out.println("\nYou've come to the wrong farm!!");
                    enemies.add(f.getFarmer());
                    doFighting(true, false);
                }
            }

            // House
            else {
                House h = (House) building;
                if(fBuildings.contains(building)){ 
                    h.getResident().sayHello();
                    System.out.println("\nI heard you can get work at the farm and use the moeny you earn to upgrade your weapon!");
                    System.out.println("\nWell you best be on your way! Good Luck");
                    currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
                    gamePlayMenu();
                }
                //enemy
                else {
                    System.out.println("\nYou've come to the wrong house!!");
                    enemies.add(h.getResident());
                    doFighting(true, false);
                }
            }
        }

    }
    /**
     * A function to display a menu inside a building
     */
    public void inBuildingVictory() {
        // check to see what building we are in
        for (Object b : closeBuildings) {
            Building building = (Building) b;
            //should only be in one building hopefully
            //freindly.. should not have been fighting in freindly building at this stage
            //friendly buildings
            List<Building> fBuildings = currentGame.getTerritories().get(0).getBulidings();
            if (fBuildings.contains(building)) {
                System.out.println("\nYou should not have faught in this place.... ");
                currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
                gamePlayMenu();
            }
            //enemy buildings, if defeated an enemy building owner get reward or fight the king
            else {
                if (building instanceof ArcherTower) {
                    enemyBuildingDefeat("Archer Tower", aBonus);
                }
                else if (building instanceof BlackSmithShop) {
                    enemyBuildingDefeat("Black Smith Shop", bBonus);
                }
                else if (building instanceof Farm) {
                    enemyBuildingDefeat("Farm", fBonus);
                }
                else if (building instanceof House) {
                    enemyBuildingDefeat("House", hBonus);
                }
                else {
                    //castle
                    System.out.println("\nYou have defeated the Kings Gaurd!!");
                    System.out.println("Time to finish what you started!!");
                    enemies.clear();
                    Castle c = (Castle) building;
                    enemies.add(c.getKing());
                    doFighting(true, true);
                }
            }
        }
    }
    public void enemyBuildingDefeat(String bName, int bonus) {
        System.out.println("\nYou have defeated an enemy "+bName+" !");
        System.out.println("You recieve $" + bonus + " for your efforts!");
        user.incWallet(bonus);
        currentGame.setPosition(lastPlayerPosition[0], lastPlayerPosition[1], user);
        gamePlayMenu();
    }
}
