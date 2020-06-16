
package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author david
 * @version 10/26/18
 */
public class Bicycle extends AbstractVehicle implements Vehicle {
    /**
     * 
     */
    private static final int DEATH_TIME = 35;

    /**
     * 
     * @param theX x coordinate value.
     * @param theY y coordinate value.
     * @param theDir direction value.
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    // Travel on streets, crosswalks, and trails
    // prefers to travel on trails
    // if trail then straight
    // no trail --> street
    // ignores green light
    // stops for yellow and red lights
    // dude.... i hate bicyclist

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        return (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN)
               || (theTerrain == Terrain.LIGHT && theLight == Light.GREEN)
               || theTerrain == Terrain.STREET || theTerrain == Terrain.TRAIL;

    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final ArrayList<Direction> dirs = getStraightLeftRight();

        for (Direction directionToCheck : dirs) {

            final Terrain terrainToCheck = theNeighbors.get(directionToCheck);

            if (Terrain.TRAIL == terrainToCheck) {

                return directionToCheck;
            }

        }
        final ArrayList<Terrain> legalTerrains = new ArrayList<Terrain>();
        legalTerrains.add(Terrain.STREET);
        legalTerrains.add(Terrain.LIGHT);
        legalTerrains.add(Terrain.CROSSWALK);

        return getDirectionCheck(dirs, theNeighbors, legalTerrains);

    }

}
