package Villagers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Villager {
    //attributes
    private String FirstName;
    private String LastName;
    private int Age;
    private int health;
    private boolean alive;
    private Weapons weapon;
    private Armour armour;
    private int maxAge = 120;
    private int[] location = new int[2];
    private List<Keys> keys = new ArrayList<Keys>();
    private int wallet;
    private String[] greetings = {
        "Good morrow, my name is ",
        "Hi, people call me ",
        "Good morrow, u can call me ",
        "Hi, my name be "
    };

    //methods
    //constructer
    public Villager(String FirstName, String LastName, int Age) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
        this.health = 100;
        this.alive = true;
        Random rand = new Random();
        wallet = rand.nextInt(10);
    }
    //getters
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public int getAge() {
        return Age;
    }
    public int getHealth() {
        return health;
    }
    public int getMaxAge() {
        return maxAge;
    }
    public int[] getLocation() {
        return location;
    }
    public boolean isAlive() {
        if (health > 0) {
            return true;
        } else {
            alive = false;
            return false;
        }
    }
    public List<Keys> getKeys() {
        return keys;
    }
    //setters
    public void setFirstName(String firstname) {
        FirstName = firstname;
    }
    public void setLasstName(String lastname) {
        LastName = lastname;
    }
    public void setAge(int age) {
        Age = age;
    }
    public void setLocation(int x, int y) {
        location[0] = x;
        location[1] = y;
    }
    public void addKey(Keys key) {
        keys.add(key);
    }
    public void removeKey(Keys key) {
        if (keys.contains(key)) {
            keys.remove(keys.indexOf(key));
        }
    }
    public void incrementHealth(int inc) {
        if (health + inc <= 100 && alive) {
            health = health + inc;
        }
    }
    public void decrementHealth(int dec) {
        if (alive) {
            health = health - dec;
            if (health >= 0) {
                alive = true;
            }
        }
    }
    // greeting
    public void sayHello() {
        Random rand = new Random();
        String greeting = greetings[rand.nextInt(greetings.length)];
        System.out.println(greeting + FirstName + " " + LastName);
    }
    public int getWallet() {
        return wallet;
    }
    public void setWallet(int w) {
        wallet = w;
    }
    public void incWallet(int w) {
        wallet = wallet + w;
    }
    public void decWallet(int w) {
        wallet = wallet - w;
    }
    public Armour getArmour() {
        return armour;
    }
    public Weapons getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }
    public void setArmour(Armour armour) {
        this.armour = armour;
    }
    //assist with fighting
    public int getAttackPower() {
        Random rand = new Random();
        int power;
        if (weapon == Weapons.Fists) {
            power = rand.nextInt(1,5);
        }
        else if (weapon == Weapons.Club) {
            power = rand.nextInt(5, 10);
        }
        else if (weapon == Weapons.Dagger) {
            power = rand.nextInt(8, 13);
        }
        else if (weapon == Weapons.Lance) {
            power = rand.nextInt(10, 16);
        }
        else if (weapon == Weapons.Mace) {
            power = rand.nextInt(12, 18);
        }
        else if (weapon == Weapons.Bow) {
            power = rand.nextInt(3, 20);
        }
        else {
            // sword
            power = rand.nextInt(16, 24);
        }
        return power;
    }
    public int getArmourDefence() {
        int defence;
        Random rand = new Random();
        if (armour == Armour.none) {
            defence = 0;
        }
        else if (armour == Armour.leather) {
            defence = rand.nextInt(1,10);
        }
        else {
            //steeel armour
            defence = rand.nextInt(6,20);
        }
        return defence;
    }
}
