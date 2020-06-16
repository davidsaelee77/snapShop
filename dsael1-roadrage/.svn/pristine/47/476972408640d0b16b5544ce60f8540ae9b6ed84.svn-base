/*
 * TCSS 305 - Road Rage
 */

package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Direction;
import model.Truck;
import model.Light;
import model.Terrain;
import org.junit.Test;

/**
 * Unit tests for class Truck.
 * 
 * @author Alan Fowler (acfowler@uw.edu)
 * @version Spring 2016
 */
public class TruckTest {

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /** Test method for Truck constructor. */
    @Test
    public void testTruckConstructor() {
        final Truck t = new Truck(10, 11, Direction.NORTH);

        assertEquals("Truck x coordinate not initialized correctly!", 10, t.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 11, t.getY());
        assertEquals("Truck direction not initialized correctly!", Direction.NORTH,
                     t.getDirection());
        assertEquals("Truck death time not initialized correctly!", 0, t.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", t.isAlive());
    }

    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(10, 11, Direction.NORTH);

        t.setX(12);
        assertEquals("Truck setX failed!", 12, t.getX());
        t.setY(13);
        assertEquals("Truck setY failed!", 13, t.getY());
        t.setDirection(Direction.SOUTH);
        assertEquals("Truck setDirection failed!", Direction.SOUTH, t.getDirection());
    }

    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {

        // Trucks can move to STREET, LIGHT or to CROSSWALKS
        // so we need to test both of those conditions

        // Trucks should NOT choose to move to other terrain types
        // so we need to test that Trucks never move to other terrain types

        // Trucks should only reverse direction if no other option is available
        // so we need to be sure to test that requirement also

        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.LIGHT);

        final Truck truck = new Truck(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {

                    // trucks can pass STREET under any light condition
                    assertTrue("Truck should be able to pass STREET" + ", with light "
                               + currentLightCondition,
                               truck.canPass(destinationTerrain, currentLightCondition));
                }
                else if (destinationTerrain == Terrain.CROSSWALK) {
                    // trucks can pass CROSSWALK
                    // if the light is YELLOW or GREEN

                    if (currentLightCondition == Light.RED) {
                        assertFalse("Truck should NOT be able to pass " + destinationTerrain
                                    + ", with light " + currentLightCondition,
                                    truck.canPass(destinationTerrain, currentLightCondition));
                    }
                    else { // light is yellow or green
                        assertTrue("Truck should be able to pass " + destinationTerrain
                                   + ", with light " + currentLightCondition,
                                   truck.canPass(destinationTerrain, currentLightCondition));
                    }
                }
                else if (destinationTerrain == Terrain.LIGHT) {

                    // trucks can pass LIGHT under any light condition
                    assertTrue("Truck should be able to pass LIGHT" + ", with light "
                               + currentLightCondition,
                               truck.canPass(destinationTerrain, currentLightCondition));

                }
                else if (!validTerrain.contains(destinationTerrain)) {

                    assertFalse("Truck should NOT be able to pass " + destinationTerrain
                                + ", with light " + currentLightCondition,
                                truck.canPass(destinationTerrain, currentLightCondition));
                }
            }
        }
    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByValidTerrain() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        neighbors.put(Direction.EAST, Terrain.CROSSWALK);
        neighbors.put(Direction.SOUTH, Terrain.STREET);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        final Truck truck = new Truck(0, 0, Direction.NORTH);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = truck.chooseDirection(neighbors);

            if (d == Direction.WEST) {
                seenWest = true;
            }
            else if (d == Direction.NORTH) {
                seenNorth = true;
            }
            else if (d == Direction.EAST) {
                seenEast = true;
            }
            else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }

        assertTrue("Truck chooseDirection() fails to select randomly "
                   + "among all possible valid choices!", seenWest && seenNorth && seenEast);

        assertFalse("Truck chooseDirection() reversed direction when not necessary!",
                    seenSouth);
    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnInValidTerrainMustReverse() {

        for (final Terrain t : Terrain.values()) {
        if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {

                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);

                final Truck truck = new Truck(0, 0, Direction.NORTH);

                // the Truck must reverse and go SOUTH
                assertEquals("Truck chooseDirection() failed "
                             + "when reverse was the only valid choice!", Direction.SOUTH,
                             truck.chooseDirection(neighbors));
            }

        }
    }

}
