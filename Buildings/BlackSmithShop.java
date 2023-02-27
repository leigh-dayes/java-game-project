package Buildings;

import Villagers.*;
import java.util.Scanner;

public class BlackSmithShop extends Building {
    private Blacksmith blackie;
    private int swordPrice = 10;
    private int macePrice = 7;
    private int lancePrice = 10;
    private int daggerPrice = 5;
    private int bowPrice = 9;
    private int clubPrice = 5;
    private int leatherPrice = 10;
    private int steelPrice = 15;


    public BlackSmithShop(boolean locked) {
        super(locked);
        setLength(2);
        setWidth(2);
        setHitPoints(15);
        setType("Blacksmith Shop");
        NamesList name = new NamesList();
        blackie = new Blacksmith(name.getFirstName(), name.getLastName(), 20);
        blackie.setWeapon(Weapons.Mace);
        blackie.setArmour(Armour.none);
    }
    public Blacksmith getBlacksmith() {
        return blackie;
    }
    public void setBlackSmith(Blacksmith blacksmith) {
        blackie = blacksmith;
    }
    public void buyWeapon(Villager shopper) {
        int cash = shopper.getWallet();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validCoice = false;
        System.out.println("\nWelcome to Ye'Olde Blacksmith Shoppe!");
        do {
            System.out.println("\n###########################################");
            System.out.println("#        PLEASE CHOOSE AN OPTION          #");
            System.out.println("#-----------------------------------------#");
            System.out.println("#  <3 = " + shopper.getHealth() + "%" + "      $ = " + cash + "                   #");
            System.out.println("#                                         #");
            System.out.println("# 1: Buy Sword           $" + swordPrice + "                       #");
            System.out.println("#                                         #");
            System.out.println("# 2: Buy Mace            $" + macePrice + "                       #");
            System.out.println("#                                         #");
            System.out.println("# 3: Buy Lance           $" + lancePrice + "                       #");
            System.out.println("#                                         #");
            System.out.println("# 4: Buy Bow             $" + bowPrice + "                      #");
            System.out.println("#                                         #");
            System.out.println("# 5: Buy Club            $" + clubPrice + "                      #");
            System.out.println("#                                         #");
            System.out.println("# 6: Buy Dagger          $" + daggerPrice + "                       #");
            System.out.println("#                                         #");
            System.out.println("# 7: Buy Leather Armour  $" + leatherPrice + "                       #");
            System.out.println("#                                         #");
            System.out.println("# 8: Buy Steel Armour    $" + steelPrice + "                      #");
            System.out.println("#                                         #");
            System.out.println("# 0: EXIT                                 #");
            System.out.println("#                                         #");
            System.out.println("###########################################");
            //System.out.println("x-loc: " + user.getLocation()[0] + "   y-loc: " +user.getLocation()[1]);
            System.out.print("YOUR SELECTION: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >=0 && choice < 9) {
                    validCoice = true;
                    //scanner.nextLine();
                }
                else {
                    System.out.println("\nOops! please choose an integer that corresponds to a menu option i.e. 1 to buy a sword!\n");
                    //scanner.nextLine();
                }
            }
            else {
                System.out.println("\nOops! please choose an integer that corresponds to a menu option i.e. 1 to buy a sword\n");
                //scanner.nextLine();
            }
            scanner.nextLine();
        } while (!validCoice);
        switch(choice) {
            case 1: 
                if (cash >= swordPrice) {
                    pruchaseConfirmation("a Sword", swordPrice, shopper, Weapons.Sword);
                }
                else {
                    System.out.println("Hmm you dont have enough money to buy a Sword..");
                    buyWeapon(shopper);
                }
                break;
            case 2:
                if (cash >= macePrice) {
                    pruchaseConfirmation("a Mace", macePrice, shopper, Weapons.Mace);
                }
                else {
                    System.out.println("Hmm you dont have enough money to buy a Mace..");
                    buyWeapon(shopper);
                }
                break;
            case 3:
                if (cash >= swordPrice) {
                    pruchaseConfirmation("a Lance", lancePrice, shopper, Weapons.Lance);
                }
                else {
                    System.out.println("Hmm you dont have enough money to buy a Lance..");
                    buyWeapon(shopper);
                }
            case 4:
                if (cash >= swordPrice) {
                    pruchaseConfirmation("a Bow", bowPrice, shopper, Weapons.Bow);
                }
                else {
                    System.out.println("Hmm you dont have enough money to buy a Bow..");
                    buyWeapon(shopper);
                }
                break;
            case 5:
                if (cash >= swordPrice) {
                    pruchaseConfirmation("a Club", clubPrice, shopper, Weapons.Club);
                }
                else {
                    System.out.println("Hmm you dont have enough money to buy a Club..");
                    buyWeapon(shopper);
                }
                break;
            case 6:
                if (cash >= swordPrice) {
                    pruchaseConfirmation("a Dagger", daggerPrice, shopper, Weapons.Dagger);
                }
                else {
                    System.out.println("Hmm you dont have enough money to buy a Dagger..");
                    buyWeapon(shopper);
                }
                break;
            case 7:
                if (cash >= swordPrice) {
                    pruchaseConfirmation("Leather Armour", leatherPrice, shopper, Armour.leather);
                }
                else {
                    System.out.println("Hmm you dont have enough money to buy Leather Armour..");
                    buyWeapon(shopper);
                }
                break;
            case 8:
                if (cash >= swordPrice) {
                    pruchaseConfirmation("Steel Armour", steelPrice, shopper, Armour.steel);
                }
                else {
                    System.out.println("Hmm you dont have enough money to buy Steel Armour..");
                    buyWeapon(shopper);
                }
                break;
            case 0:
                System.out.println("\nThankyou for shopping with us!, see you next time.");
                break;
        }
    }
    public void pruchaseConfirmation(String name, int amount, Villager shopper, Weapons weapon) {
        System.out.println("You have purchased " + name);
        shopper.decWallet(amount);
        shopper.setWeapon(weapon);
        buyWeapon(shopper);
    }
    // overload for armour
    public void pruchaseConfirmation(String name, int amount, Villager shopper, Armour armour) {
        System.out.println("You have purchased " + name);
        shopper.decWallet(amount);
        shopper.setArmour(armour);
        buyWeapon(shopper);
    }
}
