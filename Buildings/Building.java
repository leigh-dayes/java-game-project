package Buildings;
public abstract class Building {
    
    private int length;
    private int width;
    private boolean locked;

    public Building(int length, int width, boolean locked) {
        this.length = length;
        this.width = width;
        this.locked = locked;
    }
    public void setLength(int l) {
        length = l;
    }
    public void setWidth(int w) {
        width = w;
    }
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
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
