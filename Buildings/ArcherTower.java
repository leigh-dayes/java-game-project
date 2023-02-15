package Buildings;

import Villagers.*;
import java.util.Random;

public class ArcherTower extends Building {
    

    public ArcherTower(boolean locked) {
        super(locked);
        setWidth(2);
        setLength(2);
        setHitPoints(25);
        setType("Archer Tower");
    }
    
}
