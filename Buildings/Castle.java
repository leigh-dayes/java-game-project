package Buildings;

import Villagers.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Castle extends Building {

    private King king;
    private List<Knight> kingsKnights = new ArrayList<Knight>();
    private NamesList name = new NamesList();

    public Castle(boolean locked) {
        super(locked);
        setLength(5);
        setWidth(5);
        setHitPoints(200);
        setType("Castle");
        king = new King("King" + name.getFirstName(), name.getLastName(), 20);
        king.setWeapon(Weapons.Fists);
        king.setArmour(Armour.steel);
        addKnights();
    }
    public void addKnights() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            name.newName();
            kingsKnights.add(new Knight(name.getFirstName(), name.getLastName(), rand.nextInt(20,50)));
        }
        for (Knight k: kingsKnights) {
            k.setWeapon(Weapons.Sword);
            k.setArmour(Armour.steel);
        }
    }
    public King getKing() {
        return king;
    }
    public List<Knight> getKingsKnights() {
        return kingsKnights;
    }
    public void setKing(King king) {
        this.king = king;
    }
    public void addKnight(Knight knight) {
        kingsKnights.add(knight);
    }
    public void removeKnight(Knight knight) {
        kingsKnights.remove(knight);
    }
    
}
