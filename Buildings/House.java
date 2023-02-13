package Buildings;
public class House extends Building {

    public House(boolean locked) {
        super(locked);
        setWidth(2);
        setLength(2);
        setHitPoints(10);
    }
    
}
