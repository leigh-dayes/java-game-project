package Buildings;

import Villagers.*;

public class BlackSmithShop extends Building {
    private Blacksmith blackie;

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
}
