package Buildings;

import Villagers.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Building {
    
    private int length;
    private int width;
    private boolean locked;
    private int hitPoints;
    private boolean destroyed;
    private List<Villager> residents = new ArrayList<Villager>();
    private int xLocation;
    private int yLocation;
    private String type;


    public Building(boolean locked) {
        this.locked = locked;
        destroyed = false;
    }
    public void setLength(int l) {
        length = l;
    }
    public void setWidth(int w) {
        width = w;
    }
    public void setResidents(List<Villager> res) {
        residents = res;
    }
    public void setHitPoints(int hp) {
        hitPoints = hp;
    }
    public void setXLocation(int x) {
        xLocation = x;
    }
    public void setYLocation(int y) {
        yLocation = y;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }
    public List<Villager> getResidents() {
        return residents;
    }
    public int getHitPoints() {
        return hitPoints;
    }
    public int getXLocation() {
        return xLocation;
    }
    public int getYlocation() {
        return yLocation;
    }
    public String getType() {
        return type;
    }
    public boolean isLocked() {
        return locked;
    }
    public void lock() {
        locked = true;
    }
    public void unlock() {
        locked = false;
    }
    public void causeDamage(int damage) {
        if (!destroyed) {
            hitPoints = hitPoints - damage;
            if (hitPoints <= 0) {
                destroyed = true;
            }
        }
    }
}
