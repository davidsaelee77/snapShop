
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
/**
 * 
 * @author David Saelee
 * @version 10/24/2018
 *
 */
public class Human extends AbstractVehicle implements Vehicle {
    /**
     *
     */
    private static final int DEATH_TIME = 45;
    /**
     * 
     * @param theX x coordinate value.
     * @param theY y coordinate value.
     * @param theDir direction value.
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);

    }

    // Implment random direction

    // Human never reverses direction unless no other option
    // if next to crosswalk --> turn and face crosswalk

    // Humans ignore the color of traffic lights

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {

        // moves only on grass or crosswalks
        // humans stop at crosswalk when light is green.
        // humans will wait until light is yellow before crossing crosswalk.
        // crosses crosswalk on yellow or red light.

        return theTerrain == Terrain.GRASS 
                        || (theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        ArrayList<Direction> dirs = getStraightLeftRight();

        for (Direction directionToCheck : dirs) {

            Terrain terrainToCheck = theNeighbors.get(directionToCheck);

            if (Terrain.CROSSWALK == terrainToCheck) {

                return directionToCheck;
            }

        }
        
        Collections.shuffle(dirs);
        
        ArrayList<Terrain> legalTerrains = new ArrayList<Terrain>();
        legalTerrains.add(Terrain.GRASS);
        legalTerrains.add(Terrain.CROSSWALK);

        return getDirectionCheck(dirs, theNeighbors, legalTerrains);

    }

}
