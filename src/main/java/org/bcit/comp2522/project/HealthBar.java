package org.bcit.comp2522.project;

/**
 * Class for health bar display.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class HealthBar {
  private float health;
  private final int width;
  private final int height;

  public HealthBar(float health) {
    this.health = health;
    this.width = 180;
    this.height = 20;
  }

  public void draw(float health, Window window) {
    window.fill(0);
    window.stroke(155);
    window.rect(40, window.height - 50, width, height, 25, 25, 25, 25);

    // Draw health
    this.health = health;
    drawHealth(window);
  }

  public void drawHealth(Window window) {
    window.fill(34, 171, 0);
    window.stroke(0);
    window.rect(41, window.height - 49, ((health / 100f) * width) - 2, height - 2, 25, 25, 25, 25);
  }
}
