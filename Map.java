import java.util.ArrayList;
import java.util.List;
import Territory.*;
import java.util.Random;
import Buildings.*;

public class Map {

    static final int X = 40;
    static final int Y = 40;
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
        printMap();
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
        // populate all of the squares that the castle covers with the String "user_castle"
        int castleWidth = userCastle.getWidth();
        int castleLength = userCastle.getLength(); 
        for (int i = xUserCastle; i < castleWidth + xUserCastle; i++) {
            for (int j = yUserCastle; j < castleLength + yUserCastle; j++) {
                world[i][j] = " uc ";
            }
        }
        // enemy castle bottom right quadrant
        int xEnemyCastle = rand.nextInt(4*X/5, X-castleWidth);
        int yEnemyCastle = rand.nextInt(4*Y/5, Y-castleLength);
        world[xEnemyCastle][yEnemyCastle] = enemyCastle;
        // populate all of the squares that the enemy castle covers with the String "enemy_castle"
        for (int i = xEnemyCastle; i < castleWidth + xEnemyCastle; i++) {
            for (int j = yEnemyCastle; j < castleLength + yEnemyCastle; j++) {
                world[i][j] = " ec ";
            }
        }
    }
    /**
     * A function to print out the map to console
     */
    public void printMap() {
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if(world[i][j] instanceof Building) {
                    System.out.print(" Building ");
                }else {
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
}
