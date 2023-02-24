package Buildings;

import Villagers.*;

import java.util.List;
import java.util.ArrayList;

public class ArcherTower extends Building {
    
    private List<Archer> archers = new ArrayList<Archer>();

    public ArcherTower(boolean locked) {
        super(locked);
        setWidth(2);
        setLength(2);
        setHitPoints(25);
        setType("Archer Tower");
        NamesList name = new NamesList();
        Archer a1 = new Archer(name.getFirstName(), name.getLastName(), 20);
        name.newName();
        Archer a2 = new Archer(name.getFirstName(), name.getLastName(), 20);
        archers.add(a1);
        archers.add(a2);
        for (Archer a : archers) {
            a.setWeapon(Weapons.Bow);
            a.setArmour(Armour.leather);
        }
    }
    public List<Archer> getArchers() {
        return archers;
    }
    public void addArcher(Archer archer) {
        archers.add(archer);
    }
    public void removeArcher(Archer archer) {
        archers.remove(archer);
    }
    
}
