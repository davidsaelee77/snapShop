
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author David Saelee
 * @version 10/24/2018
 *
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * 
     */
    private int myX;
    /**
     * 
     */
    private int myY;
    /**
     * 
     */
    private Direction myDirection;

    // initial position and direction
    /**
     * 
     */
    private final int myInitialX;
    /**
     * 
     */
    private final int myInitialY;
    /**
     * 
     */
    private final Direction myInitialDirection;
    /**
     * 
     */
    private int myTimeToRevive;
    /**
     * 
     */
    private final int myDeathTime;

    /**
     * 
     * @param theX x coordinate value
     * @param theY y coordinate value
     * @param theDir direction value
     * @param theDEATH_TIME death time value
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir,
                              final int theDEATH_TIME) {

        myTimeToRevive = 0;

        myInitialX = theX;
        myInitialY = theY;
        myInitialDirection = theDir;

        myX = theX;
        myY = theY;
        myDirection = theDir;
        myDeathTime = theDEATH_TIME;
    }

    @Override
    public void collide(final Vehicle theOther) {

        if (this.myDeathTime > theOther.getDeathTime()) {

            myTimeToRevive = this.getDeathTime();
        }

    }

    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    @Override
    public String getImageFileName() {

        if (this.isAlive()) {

            return getClass().getSimpleName().toLowerCase() + ".gif";

        }

        return getClass().getSimpleName().toLowerCase() + "_dead.gif";
    }

    @Override
    public Direction getDirection() {

        return myDirection;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public boolean isAlive() {

        return myTimeToRevive == 0;
    }

    @Override
    public void poke() {
        if (myTimeToRevive > 0) {
            myTimeToRevive--;
        }
        if (myTimeToRevive == 0) {

            setDirection(Direction.random());
        }

    }

    @Override
    public void reset() {
        myX = myInitialX;
        myY = myInitialY;
        myDirection = myInitialDirection;
        myTimeToRevive = 0;
    }

    @Override
    public void setDirection(final Direction theDir) {

        myDirection = theDir;

    }

    @Override
    public void setX(final int theX) {

        myX = theX;

    }

    @Override
    public void setY(final int theY) {

        myY = theY;

    }

    protected ArrayList<Direction> getStraightLeftRight() {
        ArrayList<Direction> dirs = new ArrayList<Direction>();
        dirs.add(getDirection()); // straight
        dirs.add(getDirection().left());
        dirs.add(getDirection().right());
        return dirs;
    }

    protected Direction getDirectionCheck(final ArrayList<Direction> dirs,
                                          final Map<Direction, Terrain> theNeighbors,
                                          final ArrayList<Terrain> legalTerrains) {

        for (Direction directionToCheck : dirs) {

            Terrain terrainToCheck = theNeighbors.get(directionToCheck);

            if (legalTerrains.contains(terrainToCheck)) {

                return directionToCheck;
            }

        }
        return getDirection().reverse();

    }

}
