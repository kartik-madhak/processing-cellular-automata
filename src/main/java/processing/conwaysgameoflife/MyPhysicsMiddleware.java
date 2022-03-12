package processing.conwaysgameoflife;

import processing.lib.PhysicsMiddleware;
import processing.lib.grid.Cell;

public class MyPhysicsMiddleware extends PhysicsMiddleware {
  public MyPhysicsMiddleware(long every) {
    super(every);
  }

  @Override
  public Cell calculate(int i, int j) {
    Cell[][] neighbors = getNeighbors(i, j, 1);

    int whiteNeighbors = 0;

    for (int i1 = 0; i1 < neighbors.length; i1++) {
      Cell[] neighbor = neighbors[i1];
      for (int k = 0; k < neighbor.length; k++) {
        Cell cell = neighbor[k];
        if (cell instanceof WhiteCell) {
          whiteNeighbors++;
        }
      }
    }

    Cell cell = readBuffered(i, j);
    if (cell instanceof WhiteCell) {
      if (whiteNeighbors < 2 || whiteNeighbors > 3) {
        return new Cell();
      }
    } else {
      if (whiteNeighbors == 3) {
        return new WhiteCell();
      }
    }
    return cell;
  }
}
