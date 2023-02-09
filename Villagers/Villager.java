package Villagers;

public abstract class Villager {
    //attributes
    private String FirstName;
    private String LastName;
    private int Age;
    private int health;
    private boolean alive;
    //methods
    //constructer
    public Villager(String FirstName, String LastName, int Age) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
        health = 100;
        alive = true;
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
    public void incrementHealth(int inc) {
        if (health + inc <= 100 && alive) {
            health = health + inc;
        }
    }
    public void decrementHealth(int dec) {
        if (alive) {
            health = health - dec;
            if (health >= 0) {
                alive = false;
            }
        }
    }
    
}
