
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author David Saelee
 * @version 10/24/2018
 *
 */
public class Truck extends AbstractVehicle implements Vehicle {
    /**
     * 
     */
    private static final int DEATH_TIME = 0;

    /**
     * 
     * @param theX x coordinate value.
     * @param theY y coordinate value.
     * @param theDir direction value.
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        // travel only on streets, through lights and crosswalks
        // Trucks drive through stop lights without stopping
        // trucks stop for red crosswalk lights, but drive through
        // yellow and green crosswalk lights without stopping.
        
        // random generator 1-3 for different direction.
        // if the number pick is valid (street) then go in that direction.
        // if number is not a street direction then choose another random
        // number.
        // check number again and repeat.

        return theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT
               || (theTerrain == Terrain.CROSSWALK && theLight != Light.RED);

    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
       ArrayList<Direction> dirs = getStraightLeftRight();
        
        Collections.shuffle(dirs); //random order
        
        ArrayList<Terrain> legalTerrains = new ArrayList<Terrain>();
        legalTerrains.add(Terrain.STREET);
        legalTerrains.add(Terrain.LIGHT);
        legalTerrains.add(Terrain.CROSSWALK);
        
        
       return getDirectionCheck(dirs, theNeighbors, legalTerrains);

    }
}
