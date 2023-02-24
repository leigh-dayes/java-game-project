package Buildings;

import java.util.Random;

import Villagers.*;

public class House extends Building {

    private Villager villager;

    public House(boolean locked) {
        super(locked);
        setWidth(2);
        setLength(2);
        setHitPoints(10);
        setType("House");
        setVillager();
    }
    public void setVillager() {
        NamesList name = new NamesList();
        Random rand = new Random();
        int choice = rand.nextInt(4);
        switch (choice) {
            case 0:
                villager = new Farmer(name.getFirstName(), name.getLastName(), rand.nextInt(20, 100));
                break;
            case 1:
                villager = new Blacksmith(name.getFirstName(), name.getLastName(), rand.nextInt(20, 100));
                break;
            case 2:
                villager = new Knight(name.getFirstName(), name.getLastName(), rand.nextInt(20, 100));
                break;
            case 3:
                villager = new Archer(name.getFirstName(), name.getLastName(), rand.nextInt(20, 100));
                break;
        }
        villager.setWeapon(Weapons.Dagger);
        villager.setArmour(Armour.none);
    }
}
