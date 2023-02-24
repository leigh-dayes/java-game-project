package Buildings;

import Villagers.*;

public class Farm extends Building {

    private Farmer farmer;

    public Farm(boolean locked) {
        super(locked);
        setLength(7);
        setWidth(7);
        setHitPoints(10);
        setType("Farm");
        NamesList name = new NamesList();
        farmer = new Farmer(name.getFirstName(), name.getLastName(), 60);
        farmer.setWeapon(Weapons.Club);
        farmer.setArmour(Armour.none);
    }
    public Farmer getFarmer() {
        return farmer;
    }
}
