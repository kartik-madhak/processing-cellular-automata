package processing.conwaysgameoflife;

import processing.lib.PhysicsMiddleware;
import processing.lib.grid.Cell;

import java.util.Arrays;

public class MyPhysicsMiddleware extends PhysicsMiddleware {
  public MyPhysicsMiddleware(long every) {
    super(every);
  }

  @Override
  public Cell calculate(int i, int j) {
    Cell[][] neighbors = getNeighbors(i, j, 1);

    int whiteNeighbors =
        (int)
            Arrays.stream(neighbors)
                .flatMap(Arrays::stream)
                .filter(WhiteCell.class::isInstance)
                .count();

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
