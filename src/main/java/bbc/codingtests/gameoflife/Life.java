package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;

public interface Life {
    /**
     * Apply the rules of the Game of Life to the given state and return the resulting state
     */
    GameState evolve(GameState currentState);
}
