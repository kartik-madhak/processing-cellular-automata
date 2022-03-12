package processing.lib.grid;

import java.util.Arrays;

public class Grid {
  private int rows;
  private int cols;
  private Cell[][] cells;

  public Grid(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.cells = new Cell[rows][cols];
    clear();
  }

  public Grid(Grid other) {
    this.rows = other.rows;
    this.cols = other.cols;
    this.cells = new Cell[rows][cols];
    for (int i = 0; i < other.rows; i++) {
      cells[i] = Arrays.copyOf(other.cells[i], other.cols);
    }
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  public Cell getAt(int i, int j) {
    return cells[i][j];
  }
  public void setAt(int i, int j, Cell cell) {
    cells[i][j] = cell;
  }

  public void clear() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        cells[i][j] = new Cell();
      }
    }
  }
}
