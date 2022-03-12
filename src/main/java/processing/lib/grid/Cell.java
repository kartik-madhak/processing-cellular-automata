package processing.lib.grid;

import java.awt.*;

public class Cell {
  Color color;

  public Cell() {
    this.color = new Color(0, 0, 0, 0);
  }

  protected Cell(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }
}
