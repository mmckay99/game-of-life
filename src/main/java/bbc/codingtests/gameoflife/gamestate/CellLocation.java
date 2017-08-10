package bbc.codingtests.gameoflife.gamestate;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an immutable cell location.
 *
 * Created by michael on 09/08/17.
 */
public class CellLocation implements Comparable<CellLocation> {
    private final int row;
    private final int column;

    public static List<CellLocation> calculateNeighbourLocations(CellLocation location) {
        ArrayList<CellLocation> neighbours = new ArrayList<CellLocation>(8);

        neighbours.add(new CellLocation(location.getRow() - 1, location.getColumn() - 1));
        neighbours.add(new CellLocation(location.getRow(), location.getColumn() - 1));
        neighbours.add(new CellLocation(location.getRow() + 1, location.getColumn() - 1));

        neighbours.add(new CellLocation(location.getRow() - 1, location.getColumn() + 1));
        neighbours.add(new CellLocation(location.getRow(), location.getColumn() + 1));
        neighbours.add(new CellLocation(location.getRow() + 1, location.getColumn() + 1));

        neighbours.add(new CellLocation(location.getRow() + 1, location.getColumn()));
        neighbours.add(new CellLocation(location.getRow() - 1, location.getColumn()));

        return neighbours;
    }

    public CellLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int compareTo(CellLocation cellLocation) {
        return (cellLocation.getRow() - row) + (cellLocation.getColumn() - column);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CellLocation) {
            CellLocation other = (CellLocation) object;

            return (other.getRow() == row && other.getColumn() == column);
        }

        return false;
    }
}
