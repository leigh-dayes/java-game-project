package Buildings;

import Villagers.*;
import java.util.Random;

public class ArcherTower extends Building {
    

    public ArcherTower(boolean locked) {
        super(locked);
        setWidth(1);
        setLength(1);
        setHitPoints(25);
    }
    
}
