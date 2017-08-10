package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.CellLocation;
import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;

import java.util.List;

public class LifeImpl implements Life {

    private static int countLivingNeighbours(GameState gameState, CellLocation cellLocation) {
        List<CellLocation> neighbourLocations = CellLocation.calculateNeighbourLocations(cellLocation);

        int count = 0;
        for (CellLocation neighbour : neighbourLocations) {
            if (gameState.isCellAliveAt(neighbour)) {
                ++count;
            }
        }

        return count;
    }

	public GameState evolve(GameState currentState) {
		// The scenarios given in the document can be applied
        // by iterating over each position in the old
        // state and calculating its new state.
        GameState newState = new GameStateImpl(currentState.getRows(), currentState.getCols());
        List<CellLocation> currentAliveCells = currentState.getAllAliveCells();

        for (CellLocation aliveCell : currentAliveCells) {
            List<CellLocation> neighbourLocations = CellLocation.calculateNeighbourLocations(aliveCell);

            // Any cells that might be created will be neighbours of existing living cells. For each
            // non-living cell neighbour of this living cell, if it has 3 neighbours (in total) it
            // will become alive.
            for (CellLocation neighbour : neighbourLocations) {
                if (!currentState.isCellAliveAt(neighbour)) {
                    boolean neighbourBirth = (countLivingNeighbours(currentState, neighbour) == 3);

                    if (neighbourBirth) {
                        newState.setCellAliveAt(neighbour, true);
                    }
                }
            }

            // Under and over-population apply only to currently alive cells, so apply those rules.
            int numberOfLivingNeighbours = countLivingNeighbours(currentState, aliveCell);
            if (numberOfLivingNeighbours >= 2 && numberOfLivingNeighbours <= 3) {
                newState.setCellAliveAt(aliveCell, true);
            }
        }

        return newState;
	}
}
