package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * Mouse class used to click buttons.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class Mouse {
  private static Mouse mouse;
  private final Window window;
  private PVector position;
  private final float width;
  private final float height;

  public Mouse(PVector position, Window window) {
    this.window = window;
    this.position = position;
    this.width = 1;
    this.height = 1;
  }

  public static Mouse getInstance(PVector position, Window window) {
    if (mouse == null) {
      mouse = new Mouse(position, window);
    }

    return mouse;
  }

  public void move(Window window) {
    this.position = new PVector(window.mouseX, window.mouseY);
  }

  public BoundingBox getBoundingBox() {
    return new BoundingBox(position, width, height, window);
  }
}
