package processing.conwaysgameoflife;

import processing.core.PApplet;
import processing.lib.GridRenderer;

import java.awt.*;

public class Main extends GridRenderer {
  public Main() {
    super(10, new Color(50, 50, 50), new MyPhysicsMiddleware(10));
  }

  public static void main(String[] args) {
    PApplet.main(Main.class);
  }

  @Override
  public void mouseDragged() {
    if (mouseButton == LEFT) {
      int i = (int) map(mouseX, 0, width, 0, width / 10.0f);
      int j = (int) map(mouseY, 0, height, 0, height / 10.0f);
      for (int k = -2; k <= 2; k++) {
        for (int l = -2; l <= 2; l++) {
          setAt(i + k, j + l, new WhiteCell());
        }
      }
    }
  }

  @Override
  public void draw() {
    super.draw();

    int mouseI = (int) map(mouseX, 0, width, 0, width / 10.0f);
    int mouseJ = (int) map(mouseY, 0, height, 0, height / 10.0f);

    fill(30);
    rect(mouseI * 10, mouseJ * 10, 10, 10);

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
