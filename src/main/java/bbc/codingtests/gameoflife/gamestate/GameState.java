package bbc.codingtests.gameoflife.gamestate;

import java.util.List;

public interface GameState {
    /**
     * Whether the cell at the given row and column is alive or not
     * Should return false if the coordinates are outside the grid
     * @param row row to check
     * @param col column to check
     * @return a boolean if the cell is alive
     */
    boolean isCellAliveAt(int row, int col);
    boolean isCellAliveAt(CellLocation cellLocation);

    /**
     * Sets the cell at the given coordinates is alive or not.
     * @param row row to check
     * @param col column to check
     * @param alive a boolean if the cell is to be alive
     */
    void setCellAliveAt(int row, int col, boolean alive);
    void setCellAliveAt(CellLocation cellLocation, boolean alive);

    /**
     * Gets a list of all alive cells, returning their locations.
     * @return a list of locations of alive cells, where long index = (row index * number of rows) + col index.
     */
    List<CellLocation> getAllAliveCells();

    /**
     * @return Number of rows the game has
     */
    int getRows();

    /**
     * @return Number of columns the game has
     */
    int getCols();
}
