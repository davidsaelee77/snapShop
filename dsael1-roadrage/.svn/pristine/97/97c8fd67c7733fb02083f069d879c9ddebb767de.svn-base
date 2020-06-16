
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author David Saelee
 * @version 10/24/2018
 *
 */
public class Car extends AbstractVehicle implements Vehicle {
    /**
     * 
     */
    private static final int DEATH_TIME = 15;

    /**
     * 
     * @param theX x coordinate value.
     * @param theY y coordinate value.
     * @param theDir direction value.
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);

    }

    // Car travels only on street and through lights and crosswalks
    // Drives staright, turns left, right, turns around
    // Car stops for red lights
    // Car ignores yellow and green lights
    // Car stops for red and yellow crosswalk lights, drive through green
    // crosswalk lights

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        return (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN)
               || (theTerrain == Terrain.LIGHT && theLight != Light.RED)
               || theTerrain == Terrain.STREET;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        final ArrayList<Direction> dirs = getStraightLeftRight();

        final ArrayList<Terrain> legalTerrains = new ArrayList<Terrain>();
        legalTerrains.add(Terrain.STREET);
        legalTerrains.add(Terrain.LIGHT);
        legalTerrains.add(Terrain.CROSSWALK);

        return getDirectionCheck(dirs, theNeighbors, legalTerrains);
    }
}
