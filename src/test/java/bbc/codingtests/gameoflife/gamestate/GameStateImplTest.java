package bbc.codingtests.gameoflife.gamestate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameStateImplTest {

    @Test
    public void testParsing() {
        String input = ".*.\n*.*\n...";
        GameState testState = new GameStateImpl(input);
        assertTrue("Row 0, col 1 should be alive", testState.isCellAliveAt(0,1));
        assertFalse("Row 2, col 2 should not be alive", testState.isCellAliveAt(2,2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingErrorCharacter() throws IllegalArgumentException {
        String input = "...\n*.!\n...";
        new GameStateImpl(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsingErrorNonRectangularBoard() {
        String input = "...\n*.\n...";
        new GameStateImpl(input);
    }

    @Test
    public void testRowColCounts() {
        String input = "...\n***\n..*";
        GameState testState = new GameStateImpl(input);
        assertEquals("The game should have 3 columns", 3, testState.getCols());
        assertEquals("The game should have 3 rows", 3, testState.getRows());
    }

    @Test
    public void testAllAliveCells() {
        String input = "...\n***\n..*";
        List<CellLocation> inputAliveCells = new ArrayList<CellLocation>();
        inputAliveCells.add(new CellLocation(1, 0));
        inputAliveCells.add(new CellLocation(1, 1));
        inputAliveCells.add(new CellLocation(1, 2));
        inputAliveCells.add(new CellLocation(2, 2));

        GameState testState = new GameStateImpl(input);

        List<CellLocation> aliveCells = testState.getAllAliveCells();

        assertEquals("There should be 4 alive cells.", aliveCells.size(), 4);
        for (CellLocation aliveCell : aliveCells) {
            assertTrue("One alive cell is not in the input", inputAliveCells.contains(aliveCell));
        }
    }

    @Test
    public void testIsCellAliveAt() {
        String input = "...\n***\n..*";
        List<CellLocation> inputAliveCells = new ArrayList<CellLocation>();
        inputAliveCells.add(new CellLocation(1, 0));
        inputAliveCells.add(new CellLocation(1, 1));
        inputAliveCells.add(new CellLocation(1, 2));
        inputAliveCells.add(new CellLocation(2, 2));

        GameState testState = new GameStateImpl(input);

        for (CellLocation inputAliveCell : inputAliveCells) {
            assertTrue("One alive cell in the input is not alive", testState.isCellAliveAt(inputAliveCell));
            assertTrue("One alive cell in the input is not alive", testState.isCellAliveAt(
                    inputAliveCell.getRow(),
                    inputAliveCell.getColumn())
            );

        }
    }

    @Test
    public void testSetCellAliveAt() {
        GameState testState = new GameStateImpl(1000, 1000);

        testState.setCellAliveAt(0, 0, true);
        testState.setCellAliveAt(13, 29, true);

        testState.setCellAliveAt(999, 999, false);
        testState.setCellAliveAt(7, 50, false);

        assertTrue("cell is alive", testState.isCellAliveAt(0, 0));
        assertTrue("cell is alive", testState.isCellAliveAt(13, 29));

        assertTrue("cell is alive", testState.isCellAliveAt(new CellLocation(0, 0)));
        assertTrue("cell is alive", testState.isCellAliveAt(new CellLocation(13, 29)));

        assertFalse("cell is not alive", testState.isCellAliveAt(999, 999));
        assertFalse("cell is not alive", testState.isCellAliveAt(7, 50));

        // Test that calls to set cells alive outside the bounds are ignored. JUnit
        // will fail the test if an exception is thrown.
        testState.setCellAliveAt(1001, 2000, true);
        testState.setCellAliveAt(Integer.MAX_VALUE, 0, true);
        testState.setCellAliveAt(Integer.MIN_VALUE, Integer.MIN_VALUE, false);

    }
}
