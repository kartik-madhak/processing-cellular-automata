package processing.lib;

import processing.lib.grid.Cell;
import processing.lib.grid.Grid;

public abstract class PhysicsMiddleware {
  private final long every;
  protected Grid renderGrid;
  protected Grid bufferedGrid;
  private long lastTime;

  protected PhysicsMiddleware(long every) {
    this.every = every;
    this.lastTime = System.currentTimeMillis();
  }

  public void clearGrids() {
    renderGrid.clear();
    updateBuffer();
  }

  // util function to get neighbor matrix
  protected Cell[][] getNeighbors(int i, int j, int size) {
    Cell[][] temp = new Cell[size * 2 + 1][size * 2 + 1];

    for (int k = i - size; k <= i + size; k++) {
      for (int l = j - size; l <= j + size; l++) {
        setNeighbor(temp, i, j, k, l, size);
      }
    }

    return temp;
  }

  private void setNeighbor(Cell[][] temp, int i, int j, int k, int l, int size) {
    int jj = l - j + size;
    int ii = k - i + size;

    if (k == i && l == j) {
      temp[ii][jj] = null; // You are not your own neighbor!
      return;
    }

    try {
      temp[ii][jj] = bufferedGrid.getAt(k, l);
    } catch (IndexOutOfBoundsException e) {
      temp[ii][jj] = null;
    }
  }

  public void updateBuffer() {
    bufferedGrid = new Grid(renderGrid);
  }

  public void update() {
    long currTime = System.currentTimeMillis();
    if (currTime - lastTime > every) {
      lastTime = currTime;
      execute();
    }
  }

  public void execute() {
    for (int i = 0; i < renderGrid.getRows(); i++) {
      for (int j = 0; j < renderGrid.getCols(); j++) {
        renderGrid.setAt(i, j, calculate(i, j));
      }
    }
    updateBuffer();
  }

  public void setAt(int i, int j, Cell cell) {
    int ii = Math.floorMod(i, bufferedGrid.getRows());
    int jj = Math.floorMod(j, bufferedGrid.getCols());
    renderGrid.setAt(ii, jj, cell);
    bufferedGrid.setAt(ii, jj, cell);
  }

  public Cell readBuffered(int i, int j) {
    return bufferedGrid.getAt(i, j);
  }

  // ONLY FOR READING FOR UPDATE PURPOSES
  public Grid getRenderGrid() {
    return renderGrid;
  }

  protected void setRenderGrid(Grid renderGrid) {
    this.renderGrid = renderGrid;
    bufferedGrid = new Grid(renderGrid);
  }

  // Must update render grid and then copy the updated render grid to buffered grid
  public abstract Cell calculate(int i, int j);
}
