package bbc.codingtests.gameoflife.gamestate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This class represents a cell location (not necessarily an actual cell).
 *
 * Created by michael on 10/08/17.
 */
public class CellLocationTest {
    @Test
    public void calculateNeighbourLocations() throws Exception {
        CellLocation cellLocation = new CellLocation(10,10);

        List<CellLocation> expectedNeighbourLocations = new ArrayList<CellLocation>();

        expectedNeighbourLocations.add(new CellLocation(9, 9));
        expectedNeighbourLocations.add(new CellLocation(10, 9));
        expectedNeighbourLocations.add(new CellLocation(11, 9));

        expectedNeighbourLocations.add(new CellLocation(9, 11));
        expectedNeighbourLocations.add(new CellLocation(10, 11));
        expectedNeighbourLocations.add(new CellLocation(11, 11));

        expectedNeighbourLocations.add(new CellLocation(11, 10));
        expectedNeighbourLocations.add(new CellLocation(9, 10));

        List<CellLocation> actualNeighbours = CellLocation.calculateNeighbourLocations(cellLocation);

        assertEquals("neighbour locations are correct",
                expectedNeighbourLocations,
                actualNeighbours);
    }

    @Test
    public void getRow() throws Exception {
        CellLocation cellLocation = new CellLocation(7, 5);
        assertEquals(cellLocation.getRow(), 7);
    }

    @Test
    public void getColumn() throws Exception {
        CellLocation cellLocation = new CellLocation(7, 5);
        assertEquals(cellLocation.getColumn(), 5);
    }

}