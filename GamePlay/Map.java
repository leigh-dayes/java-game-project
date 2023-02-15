package GamePlay;

import java.util.ArrayList;
import java.util.List;
import Territory.*;
import java.util.Random;
import Buildings.*;
import Villagers.*;

public class Map {

    static final int X = 50;
    static final int Y = 50;
    private Object[][] world = new Object[X][Y];
    private List<Territory> territories = new ArrayList<Territory>();

    public Map() {
        // Add an enemy and user territory
        Territory userTerrritory = new Territory();
        Territory enemyTerritory = new Territory("Badland", 20);
        territories.add(userTerrritory);
        territories.add(enemyTerritory);
        populateMap();
        placeTerritories(userTerrritory, enemyTerritory);
        //printMap();
    }
    /**
     * A function for placing territories down on the map
     */
    public void placeTerritories(Territory userTerrritory, Territory enemyTerritory) {
        // Find a random spot to place each castle
        Random rand = new Random();
        // castles
        Building userCastle = userTerrritory.getBulidings().get(0);
        Building enemyCastle = enemyTerritory.getBulidings().get(0);
        // user territory in top left quadrant
        int xUserCastle = rand.nextInt(X/5);
        int yUserCastle = rand.nextInt(Y/5);
        world[xUserCastle][yUserCastle] = userCastle;
        userCastle.setXLocation(xUserCastle);
        userCastle.setYLocation(yUserCastle);
        // populate all of the squares that the castle covers with the String "user_castle"
        int castleWidth = userCastle.getWidth();
        int castleLength = userCastle.getLength();
        drawBuilding(xUserCastle, yUserCastle, castleLength, castleWidth, userCastle, " uc"); 
        // enemy castle bottom right quadrant
        int xEnemyCastle = rand.nextInt(4*X/5, X-castleWidth);
        int yEnemyCastle = rand.nextInt(4*Y/5, Y-castleLength);
        world[xEnemyCastle][yEnemyCastle] = enemyCastle;
        enemyCastle.setXLocation(xEnemyCastle);
        enemyCastle.setYLocation(yEnemyCastle);
        // populate all of the squares that the enemy castle covers with the String "enemy_castle"
        drawBuilding(xEnemyCastle, yEnemyCastle, castleLength, castleWidth, enemyCastle, " ec");
        // now we have a farm, a house, a blacksmith shop and an archer tower to place

        // Farms
        Building userFarm = userTerrritory.getBulidings().get(1);
        Building enemyFarm = enemyTerritory.getBulidings().get(1);
        int[] userFarmLoc = new int[2];
        int[] enemyFarmLoc = new int[2];
        locateSetAndDraw(userFarmLoc, enemyFarmLoc, xUserCastle, yUserCastle, xEnemyCastle, yEnemyCastle, castleLength, castleWidth, userFarm, enemyFarm, " uf", " ef");
       
        // Blacksmith shops
        Building userBlack = userTerrritory.getBulidings().get(2);
        Building enemyBlack = enemyTerritory.getBulidings().get(2);
        int[] userBlackLoc = new int[2];
        int[] enemyBlackLoc = new int[2];
        locateSetAndDraw(userBlackLoc, enemyBlackLoc, xUserCastle, yUserCastle, xEnemyCastle, yEnemyCastle, castleLength, castleWidth, userBlack, enemyBlack, " ub", " eb");

        // Archer towers
        Building userATower = userTerrritory.getBulidings().get(3);
        Building enemyATower = enemyTerritory.getBulidings().get(3);
        int[] userATowerLoc = new int[2];
        int[] enemyATowerLoc = new int[2];
        locateSetAndDraw(userATowerLoc, enemyATowerLoc, xUserCastle, yUserCastle, xEnemyCastle, yEnemyCastle, castleLength, castleWidth, userATower, enemyATower, " ua", " ea");

        // Houses
        Building userHouse = userTerrritory.getBulidings().get(4);
        Building enemyHouse = enemyTerritory.getBulidings().get(4);
        int[] userHouseLoc = new int[2];
        int[] enemyHouseLoc = new int[2];
        locateSetAndDraw(userHouseLoc, enemyHouseLoc, xUserCastle, yUserCastle, xEnemyCastle, yEnemyCastle, castleLength, castleWidth, userHouse, enemyHouse, " uh", " eh");

        // All buildings down now place Villagers on map
        // place friendly villagers in top left quadrant
        placeVillagers(userTerrritory.getVillagers(), 0, X/2, 0, Y/2);
        placeVillagers(enemyTerritory.getVillagers(), X/2, X, Y/2, Y);
    }
    /**
     * A function to print out the map to console
     */
    public void printMap() {
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if(world[i][j] instanceof Building) {
                    if (world[i][j] instanceof Farm) {
                        System.out.print(" F ");
                    }
                    else if (world[i][j] instanceof Castle) {
                        System.out.print(" C ");
                    }
                    else if (world[i][j] instanceof ArcherTower) {
                        System.out.print(" A ");
                    }
                    else if (world[i][j] instanceof House) {
                        System.out.print(" H ");
                    }
                    else {
                        System.out.print(" B ");
                    }
                }
                else if (world[i][j] instanceof Villager) {
                    if (world[i][j] instanceof Archer) {
                        System.out.print(" a ");
                    }
                    else if (world[i][j] instanceof Blacksmith) {
                        System.out.print(" b ");
                    }
                    else if (world[i][j] instanceof Farmer) {
                        System.out.print(" f ");
                    }
                    else if (world[i][j] instanceof King) {
                        System.out.print(" K ");
                    }
                    else if (world[i][j] instanceof Player) {
                        System.out.print(" P ");
                    }
                    else {
                        System.out.print(" k ");
                    }
                }
                else {
                    System.out.print(world[i][j]);
                }
            }
            System.out.println("");
        }
    }
    /**
     * A function to create a "blank" map
     */
    public void populateMap(){
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                world[i][j] = " . ";
            }
        }
    }
    /**
     * A function to add strings to the map to distinguish buildings
     */
    public void drawBuilding(int xLoc, int yLoc, int length, int width, Building building, String name) {
        for (int i = xLoc; i < width + xLoc; i++) {
            for (int j = yLoc; j < length + yLoc; j++) {
                if (world[i][j] != building) {
                    world[i][j] = name;
                }
            }
        }    
    }
    /**
     *  A function to find a random location to place the next building
     */ 
    public int[] findLoc(int xLoc, int yLoc, int length, int width, int newLength, int newWidth) {
        Random rand = new Random();
        int[] loc = new int[2];
        int locX;
        int locY;
        //go in a random direction from the castle until we find a clear spot
        boolean locFound = false;
        while(!locFound) {
            int direction = rand.nextInt(8);
            int seperation = rand.nextInt(4,8);
            switch(direction){
                //north
                case 0:
                    locY = yLoc;
                    locX = xLoc - seperation - newWidth;
                    if (willFit(locX, locY, newLength, newWidth)) {
                        loc[0] = locX;
                        loc[1] = locY;
                        locFound = true;
                    }
                    break;
                //north east
                case 1:
                    locY = yLoc + seperation;
                    locX = xLoc - seperation - newWidth;
                    if (willFit(locX, locY, newLength, newWidth)) {
                        loc[0] = locX;
                        loc[1] = locY;
                        locFound = true;
                    }
                    break;
                //east
                case 2:
                    locY = yLoc + length + seperation;
                    locX = xLoc;
                    if (willFit(locX, locY, newLength, newWidth)) {
                        loc[0] = locX;
                        loc[1] = locY;
                        locFound = true;
                    }
                    break;
                //south east
                case 3:
                    locY = yLoc + seperation + length;
                    locX = xLoc + seperation + width;
                    if (willFit(locX, locY, newLength, newWidth)) {
                        loc[0] = locX;
                        loc[1] = locY;
                        locFound = true;
                    }
                    break;
                //south
                case 4:
                    locY = yLoc;
                    locX = xLoc + seperation + width;
                    if (willFit(locX, locY, newLength, newWidth)) {
                        loc[0] = locX;
                        loc[1] = locY;
                        locFound = true;
                    }
                    break;
                //south west
                case 5:
                    locY = yLoc - seperation - newLength;
                    locX = xLoc + seperation + width;
                    if (willFit(locX, locY, newLength, newWidth)) {
                        loc[0] = locX;
                        loc[1] = locY;
                        locFound = true;
                    }
                    break;
                //west
                case 6:
                    locY = yLoc - seperation - newLength;
                    locX = xLoc;
                    if (willFit(locX, locY, newLength, newWidth)) {
                        loc[0] = locX;
                        loc[1] = locY;
                        locFound = true;
                    }
                    break;
                //north west
                case 7:
                    locY = yLoc - seperation - newLength;
                    locX = xLoc - seperation - newWidth;
                    if (willFit(locX, locY, newLength, newWidth)) {
                        loc[0] = locX;
                        loc[1] = locY;
                        locFound = true;
                    }
                    break;
            }
        }
        return loc;
    }
    /**
     * A function to see if there is space to place the new building in the new location
     */
    public boolean willFit(int x, int y, int length, int width) {
        boolean allClear = true;
        boolean inBounds = true;
        if (x < 0 || x + width > X || y < 0 || y + length > Y) {
            inBounds = false;
            allClear = false;
        }
        if (inBounds) {
            for (int i = x; i < x + width; i++) {
                for(int j = y; j < y + length; j++) {
                    if(!world[i][j].equals(" . ")) {
                        allClear = false;
                    }
                }
            }
        }
        return allClear;
    }
    /**
     * A function for setting location and drawing new buildings
     */
    public void setAndDraw(Building newBuilding, int[] loc, String name) {
        world[loc[0]][loc[1]] = newBuilding;
        newBuilding.setXLocation(loc[0]);
        newBuilding.setYLocation(loc[1]);
        drawBuilding(loc[0], loc[1], newBuilding.getLength(), newBuilding.getWidth(), newBuilding, name);
    }
    /**
     * A function for finding a location, setting that location and drawing a new building
     */
    public void locateSetAndDraw(int[] userLoc, int[] enemyLoc, int xUserCastle, int yUserCastle, int xEnemyCastle, int yEnemyCastle,
    int castleLength, int castleWidth, Building userBuilding, Building enemyBuilding, String userName, String enemyName){
        userLoc =  findLoc(xUserCastle, yUserCastle, castleLength, castleWidth, userBuilding.getLength(), userBuilding.getWidth());
        setAndDraw(userBuilding, userLoc, userName);
        enemyLoc = findLoc(xEnemyCastle, yEnemyCastle, castleLength, castleWidth, enemyBuilding.getLength(), enemyBuilding.getWidth());
        setAndDraw(enemyBuilding, enemyLoc, enemyName);
    }
    /**
     * A function to place villagers
     */
    public void placeVillagers(List<Villager> villagers, int x1, int x2, int y1, int y2) {
        Random rand = new Random();
        for (Villager v : villagers) {
            boolean validVplace = false;
            while (!validVplace) {
                int vLocx = rand.nextInt(x1,x2);
                int vLocy = rand.nextInt(y1,y2);
                if (world[vLocx][vLocy].equals(" . ")){
                    validVplace = true;
                    world[vLocx][vLocy] = v;
                    v.setLocation(vLocx, vLocy);
                }
            }
        }
    }
    /**
     * A function for placing the playable Character
     */
    public void placePlayer(Villager player, int x1, int x2, int y1, int y2) {
        Random rand = new Random();
        boolean valid = false;
        while(!valid) {
            int vLocx = rand.nextInt(x1,x2);
            int vLocy = rand.nextInt(y1,y2);
            if (world[vLocx][vLocy].equals(" . ")){
                valid = true;
                world[vLocx][vLocy] = player;
                player.setLocation(vLocx, vLocy);
            } 
        }
    }
    /**
     * A function for accessing territories
     */
    public List<Territory> getTerritories() {
        return territories;
    }
}
