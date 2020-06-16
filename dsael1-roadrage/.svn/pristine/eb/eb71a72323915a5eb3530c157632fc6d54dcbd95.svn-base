
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * 
 * @author david
 * @version 10/28/18
 */
public class Atv extends AbstractVehicle implements Vehicle {
    /**
     * 
     */
    private static final int DEATH_TIME = 25;
    /**
     * 
     * @param theX thh.
     * @param theY yjr.
     * @param theDir the.
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    /**
     * 
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        return theTerrain != Terrain.WALL;

    }

    /**
     * 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        final ArrayList<Direction> dirs = getStraightLeftRight();

        Collections.shuffle(dirs); // random order

        final ArrayList<Terrain> legalTerrains = new ArrayList<Terrain>();
        legalTerrains.add(Terrain.STREET);
        legalTerrains.add(Terrain.LIGHT);
        legalTerrains.add(Terrain.CROSSWALK);
        legalTerrains.add(Terrain.GRASS);
        legalTerrains.add(Terrain.TRAIL);

        return getDirectionCheck(dirs, theNeighbors, legalTerrains);
    }

}
