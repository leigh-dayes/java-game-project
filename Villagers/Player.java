package Villagers;

public class Player extends Villager {

    private int wallet = 10;

    public Player(String FirstName, String LastName, int Age) {
        super(FirstName, LastName, Age);
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
}
