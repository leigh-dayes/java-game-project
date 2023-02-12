package Buildings;

import Villagers.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Building {
    
    private int length;
    private int width;
    private boolean locked;
    private List<Villager> residents = new ArrayList<Villager>();


    public Building(boolean locked) {
        this.locked = locked;
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
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }
    public List<Villager> getResidents() {
        return residents;
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
}
