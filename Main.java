import Territory.Territory;
import Villagers.*;
import Buildings.*;
import GamePlay.*;


public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        int choice = menu.main();
        if (choice == 1) {
            menu.newGame();
            while(!menu.isExit()) {
                // Game loop
                menu.gamePlayMenu();
            }
        }
        else {
            System.out.println("\nThank you for playing Medieval assassin\n");
        }
    }
}
