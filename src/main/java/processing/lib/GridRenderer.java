package processing.lib;

import processing.core.PApplet;
import processing.lib.grid.Cell;
import processing.lib.grid.Grid;

import java.awt.*;

public abstract class GridRenderer extends PApplet {
  private final PhysicsMiddleware physicsMiddleware;
  private final Color bgColor;
  private final int tileSize;

  protected GridRenderer(int tileSize, Color bgColor, PhysicsMiddleware physicsMiddleware) {
    this.tileSize = tileSize;
    this.bgColor = bgColor;
    this.physicsMiddleware = physicsMiddleware;
  }

  protected void createAndSetGrid() {
    int rows = width / tileSize;
    int cols = height / tileSize;
    physicsMiddleware.setRenderGrid(new Grid(rows, cols));
  }

  public void setAt(int i, int j, Cell cell) {
    physicsMiddleware.setAt(i, j, cell);
  }

  public void clearGrid() {
    physicsMiddleware.clearGrids();
  }

  @Override
  public void settings() {
    fullScreen();
  }

  @Override
  public void setup() {
    createAndSetGrid();
  }

  @Override
  public void draw() {
    update();
    renderGrid();
  }

  void update() {
    physicsMiddleware.update();
  }

  private void renderGrid() {
    Grid renderGrid = physicsMiddleware.getRenderGrid();

    background(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), bgColor.getAlpha());
    for (int i = 0; i < renderGrid.getRows(); i++) {
      for (int j = 0; j < renderGrid.getCols(); j++) {
        renderCell(renderGrid.getAt(i, j), i, j);
      }
    }
  }

  private void renderCell(Cell cell, int i, int j) {
    Color fillColor = cell.getColor();
    fill(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), fillColor.getAlpha());
    rect(i * tileSize, j * tileSize, tileSize, tileSize);
  }
}
