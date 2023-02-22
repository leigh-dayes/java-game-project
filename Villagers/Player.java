package Villagers;

public class Player extends Villager {


    public Player(String FirstName, String LastName, int Age) {
        super(FirstName, LastName, Age);
        setWeapon(Weapons.Fists);
        setArmour(Armour.none);
    }
    
}
