package processing.langtonsant;

import processing.lib.PhysicsMiddleware;
import processing.lib.grid.Cell;

public class MyPhysicsMiddleware extends PhysicsMiddleware {
  int i = 100;
  int j = 60;
  Direction direction = Direction.UP;
  public MyPhysicsMiddleware(long every) {
    super(every);
  }

  @Override
  public void execute() {
    int ii = i;
    int jj = j;
    setAt(ii, jj, calculate(i, j));
  }

  @Override
  public Cell calculate(int i, int j) {
    Cell cell = readBuffered(i, j);
    if (cell instanceof WhiteCell) {
      direction = direction.rotateClockwise();
      moveInDirection();
      return new Cell();
    } else {
      direction = direction.rotateCounterClockwise();
      moveInDirection();
      return new WhiteCell();
    }
  }

  void moveInDirection(){
    switch (direction){
      case UP -> {
        j -= 1;
      }
      case LEFT -> {
        i -= 1;
      }
      case DOWN -> {
        j += 1;
      }
      case RIGHT -> {
        i += 1;
      }
    }
    i = Math.floorMod(i, bufferedGrid.getRows());
    j = Math.floorMod(j, bufferedGrid.getCols());
  }

  enum Direction {
    UP,
    LEFT,
    DOWN,
    RIGHT;

    Direction rotateClockwise() {
      int rotatedDirectionIndex = (ordinal() + 1) % 4;
      return values()[rotatedDirectionIndex];
    }

    Direction rotateCounterClockwise() {
      int rotatedDirectionIndex = (ordinal() + 3) % 4;
      return values()[rotatedDirectionIndex];
    }
  }
}
