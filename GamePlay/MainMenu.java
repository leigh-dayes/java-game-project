package GamePlay;

import java.util.Scanner;

public class MainMenu {

    private boolean exit = false;

    public MainMenu() {
        System.out.println("\n###### WELCOME TO MEDIEVAL ASSASSIN ######\n");
    }
    public void setExit() {
        exit = true;
    }
    public boolean isExit() {
        return exit;
    }
    public void main() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validCoice = false;
        do {
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
                    System.out.println("Oops! please choose an integer that corresponds to a menu option i.e. 1 for a new game");
                    scanner.nextLine();
                }
            }
            else {
                System.out.println("Oops! please choose an integer that corresponds to a menu option i.e. 1 for a new game");
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
    }
    public void instructionsMenu() {
        System.out.println("Welcome to the instructions menu...");
    }
    public void exitMenu() {
        System.out.println("Thanks for playing medieval assassin..");
    }
}
