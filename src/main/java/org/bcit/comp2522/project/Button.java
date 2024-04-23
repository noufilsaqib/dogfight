package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * Class for clickable buttons.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class Button {
  private final Window window;
  private final Mouse mouse;
  private final String str;
  private final PVector position;
  private final float width;
  private final float height;

  private int color;

  public Button(PVector position, int size, String str, Window window) {
    this.window = window;
    this.str = str;
    this.position = position;
    this.width = window.textWidth(str)/4;
    this.height = size;
    this.color = 255;

    // Initialize mouse
    this.mouse = Mouse.getInstance(new PVector(width / 2f, height / 2f), window);
  }

  public void draw() {
    // Mouse moving
    mouse.move(window);

    window.push();
    if (isHovered()) {
      window.fill(255, 255, 255, 35);
      window.noStroke();
      window.rect(position.x - (width / 2), position.y - (height / 2) - 4, width, height + 16);
    }

    window.fill(color, color, color);
    window.textAlign(window.CENTER);
    window.textSize(height);
    window.text(str, position.x, position.y + height / 2);
    window.pop();
  }

  /**
   * Checks if mouse is hovering over the button.
   */
  public boolean isHovered() {
    return this.getBoundingBox().collides(this.mouse.getBoundingBox());
  }

  public BoundingBox getBoundingBox() {
    return new BoundingBox(position, width, height, window);
  }

  /**
   * Getter for the color.
   *
   * @return the color
   */
  public int getColor() {
    return this.color;
  }

  /**
   * Setter for the color.
   *
   * @param color the new color
   */
  public void setColor(int color) {
    this.color = color;
  }
}
