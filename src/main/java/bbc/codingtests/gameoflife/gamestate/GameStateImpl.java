package bbc.codingtests.gameoflife.gamestate;

import java.util.*;

public class GameStateImpl implements GameState {

    private int numberOfRows;
    private int numberOfColumns;

    // The set is keyed by the index of the cell as if the rows
    // were laid out consecutively in a 1D array: thisIndex = (rowIndex * rowLength) + columnIndex.
    private Set<Long> aliveCells;

    public String toString() {
        // The final string will have a character for each cell (i.e. rows * cols) and 'cols' number
        // of newline characters.
        StringBuilder stringBuilder = new StringBuilder(this.numberOfRows * (this.numberOfColumns + 1));
        long numberOfCells = ((long) numberOfColumns) * numberOfRows;

        for (int cellIndex = 0; cellIndex < numberOfCells; ++cellIndex) {
            boolean cellAlive = aliveCells.contains(cellIndex);
            stringBuilder.append(cellAlive ? '*' : '.');

            // Cells could be stored as consecutive rows, so if the cell index is the last in a row
            // (index + 1) will be divisible by the number of columns, so we should put a newline,
            // unless it's the last cell.
            if (((cellIndex + 1) % this.numberOfColumns) == 0 && (cellIndex != numberOfCells - 1)) {
                stringBuilder.append('\n');
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Creates an empty game state of the specified size, where
     * all cells are dead by default.
     *
     * @param numberOfRows the number of rows in the new state
     * @param numberOfColumns the number of columns the new state
     */
    public GameStateImpl(int numberOfRows, int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;

        this.aliveCells = new HashSet<Long>();
    }

    public GameStateImpl(String input) {
        int inputSize = input.length();

        int currentCellRow = 0;
        int currentCellColumn = 0;

        // The location of the first newline will tell us the length of the rows.
        int rowLength = input.indexOf('\n');
        if (rowLength == -1) {
            throw new IllegalArgumentException("at least one '\n' must be present in the string");
        }

        this.aliveCells = new HashSet<Long>();

        for (int stringIndex = 0; stringIndex < inputSize; ++stringIndex) {
            char currentChar = input.charAt(stringIndex);

            if (currentChar == '\n') {
                // Check the length of this row isn't empty.
                if (currentCellColumn == 0) {
                    throw new IllegalArgumentException("row index " + currentCellRow + " has zero length");
                }

                // Check this row has the same number of columns
                // as the previous row.
                if (currentCellColumn != rowLength) {
                        throw new IllegalArgumentException("row index " + currentCellRow
                                + " has length " + currentCellRow + " but row index 0 has length " + rowLength);
                }

                ++currentCellRow;
                currentCellColumn = 0;
            } else {
                if (currentChar == '*') {
                    aliveCells.add(((long) currentCellRow * rowLength) + currentCellColumn);
                }

                ++currentCellColumn;
            }
        }

        this.numberOfColumns = rowLength;
        this.numberOfRows = currentCellRow + 1;
    }

    public boolean isCellAliveAt(int row, int col) {
        if (row < 0 || row > this.numberOfRows || col < 0 || col > this.numberOfColumns) {
            return false;
        } else {
            long longIndex = (row * this.numberOfColumns) + col;
            return aliveCells.contains(longIndex);
        }
    }

    public boolean isCellAliveAt(CellLocation cellLocation) {
        return isCellAliveAt(cellLocation.getRow(), cellLocation.getColumn());
    }

    public void setCellAliveAt(int row, int col, boolean alive) {
        if (row < 0 || row > this.numberOfRows || col < 0 || col > this.numberOfColumns) {
            // If the cell location is beyond the bounds, ignore this call.
            return;
        } else {
            long longIndex = (row * this.numberOfColumns) + col;

            if (alive) {
                aliveCells.add(longIndex);
            } else {
                aliveCells.remove(longIndex);
            }
        }
    }

    public void setCellAliveAt(CellLocation cellLocation, boolean alive) {
        setCellAliveAt(cellLocation.getRow(), cellLocation.getColumn(), alive);
    }

    public List<CellLocation> getAllAliveCells() {
        ArrayList<CellLocation> locations = new ArrayList<CellLocation>();
        for (Long longIndex : aliveCells) {
            int columnIndex = (int) (longIndex % numberOfColumns);
            int rowIndex = (int) ((longIndex - columnIndex) / numberOfColumns);

            locations.add(new CellLocation(rowIndex, columnIndex));
        }

        return locations;
    }

    public int getRows() {
        return this.numberOfRows;
    }

    public int getCols() {
        return this.numberOfColumns;
    }
}
