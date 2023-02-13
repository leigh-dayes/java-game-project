package Buildings;
public class Castle extends Building {

    public Castle(boolean locked) {
        super(locked);
        setLength(5);
        setWidth(5);
        setHitPoints(200);
    }
    
}
