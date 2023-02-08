package Villagers;

abstract class Villager {
    //attributes
    private String FirstName;
    private String LastName;
    private int Age;
    
    //methods
    //constructer
    public Villager(String FirstName, String LastName, int Age) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = Age;
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
}
