package org.bcit.comp2522.project;

/**
 * Life display for the player.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class Lives {
  private final int xPos;
  private final int yPos;

  public Lives(int x, int y) {
    this.xPos = x;
    this.yPos = y;
  }

  public void draw(int lives, Window window) {
    for (int i = 0; i < lives; i++) {
      drawSingle(xPos + (i * 40), yPos, window);
    }
  }

  public void drawSingle(int xPos, int yPos, Window window) {
    window.fill(255, 0, 0);
    window.noStroke();
    window.circle(xPos, yPos, 11.3f);
    window.circle(xPos + 10, yPos, 11.3f);
    window.triangle(xPos - 6.5f, yPos, xPos + 5, yPos + 15, xPos + 16.5f, yPos);
  }
}
