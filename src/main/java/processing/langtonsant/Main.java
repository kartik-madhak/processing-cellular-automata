package processing.langtonsant;

import processing.core.PApplet;
import processing.lib.GridRenderer;
import processing.lib.grid.Cell;

import java.awt.*;

public class Main extends GridRenderer {
  private static final int TILE_SIZE = 8;

  public Main() {
    super(TILE_SIZE, new Color(50, 50, 50), new MyPhysicsMiddleware(1));
  }

  public static void main(String[] args) {
    PApplet.main(Main.class);
  }

  @Override
  public void mouseDragged() {
    if (mouseButton == LEFT) {

    }
  }

  @Override
  public void draw() {
    super.draw();

    int mouseI = (int) map(mouseX, 0, width, 0, width / TILE_SIZE);
    int mouseJ = (int) map(mouseY, 0, height, 0, height / TILE_SIZE);

    fill(30);
    rect(mouseI * TILE_SIZE, mouseJ * TILE_SIZE, TILE_SIZE, TILE_SIZE);

    fill(220);
    text("(" + mouseI + ", " + mouseJ + ")", width - 50, 15);
  }

  @Override
  public void keyPressed() {
    if (key == 'c') {
      clearGrid();
    } else if (key == 'e') {
      System.exit(0);
    }
  }
}
