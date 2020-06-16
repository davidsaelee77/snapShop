
package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * 
 * @author david
 * @version 10/25/18
 */
public class Taxi extends AbstractVehicle implements Vehicle {
    /**
     * 
     */
    private static final int DEATH_TIME = 15;
    /**
     * 
     */
    private static final int CROSSWALK_WAIT = 3;
    /**
     * 
     */
    private int myCounter;

    /**
     * 
     * @param theX x coordinate value.
     * @param theY y coordinate value.
     * @param theDir direction value.
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);

        myCounter = 0;

    }

    // Streets and crosswalks
    // straight, left, right, reverses
    // stops for red lights
    // ignores yellow and green lights
    // stops for red light crosswalks (temp),
    // drives through green and yellow crosswalk lights

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {

            myCounter++;

            if (myCounter == CROSSWALK_WAIT) {

                myCounter = 0;

                return true;

            }
        }
        if (theTerrain == Terrain.CROSSWALK && theLight != Light.RED) {

            myCounter = 0;

            return true;

        }

        return theTerrain == Terrain.STREET
               || (theTerrain == Terrain.LIGHT && theLight != Light.RED);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        ArrayList<Direction> dirs = getStraightLeftRight();

        ArrayList<Terrain> legalTerrains = new ArrayList<Terrain>();
        legalTerrains.add(Terrain.STREET);
        legalTerrains.add(Terrain.LIGHT);
        legalTerrains.add(Terrain.CROSSWALK);

        return getDirectionCheck(dirs, theNeighbors, legalTerrains);

    }

}
