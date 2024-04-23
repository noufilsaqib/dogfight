package org.bcit.comp2522.project;

import processing.core.PVector;
import java.awt.*;

/**
 * Class for character and button bounding boxes for collision
 * detection.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class BoundingBox {
  private final float top;
  private final float right;
  private final float bottom;
  private final float left;
  private final PVector topRight;
  private final PVector topLeft;
  private final PVector bottomRight;
  private final PVector bottomLeft;
  private final Color color;
  private static final float TWO = 2f;
  private static final int COLOR_HEX = 255;

  /**
   * BoundingBox constructor.
   *
   * @param pos centre point position
   * @param width width of object
   * @param height height of object
   */
  public BoundingBox(PVector pos, float width, float height, Window window) {
    this.top = pos.y - (height / TWO);
    this.bottom = pos.y + (height / TWO);
    this.right = pos.x + (width / TWO);
    this.left = pos.x - (width / TWO);

    this.topRight = new PVector(this.right, this.top);
    this.topLeft = new PVector(this.left, this.top);
    this.bottomRight = new PVector(this.right, this.bottom);
    this.bottomLeft = new PVector(this.left, this.bottom);

    this.color = new Color(0, COLOR_HEX, 0);
  }

  /**
   * Gets the top boundary of the bounding box.
   *
   * @return top
   */
  public float getTop() {
    return this.top;
  }

  /**
   * Gets the bottom boundary of the bounding box.
   *
   * @return bottom
   */
  public float getBottom() {
    return this.bottom;
  }

  /**
   * Gets the left boundary of the bounding box.
   *
   * @return left
   */
  public float getLeft() {
    return this.left;
  }

  /**
   * Gets the right boundary of the bounding box.
   *
   * @return right
   */
  public float getRight() {
    return right;
  }

  /**
   * Processing coordinates are "top-left", i.e., Y increases in the
   * down direction. If the BoundingBox of the other character is
   * outside the current BoundingBox, then a collision is not
   * happening. We return the negation of that for collides.
   *
   * @param b the other bounding box
   * @return true if other bounding box is outside this one
   */
  public boolean collides(BoundingBox b) {
    return !(this.getBottom() < b.getTop() || this.getTop() > b.getBottom()
            || this.getRight() < b.getLeft() || this.getLeft() > b.getRight());
  }

  /**
   * If needed, the bounding box can be drawn.
   *
   * @param window current scene window
   */
  public void draw(Window window) {
    window.stroke(color.getRed(), color.getGreen(), color.getBlue());
    window.line(this.topLeft.x, this.topLeft.y, this.topRight.x, this.topRight.y);
    window.line(this.topRight.x, this.topRight.y, this.bottomRight.x, this.bottomRight.y);
    window.line(this.bottomRight.x, this.bottomRight.y, this.bottomLeft.x, this.bottomLeft.y);
    window.line(this.topLeft.x, this.topLeft.y, this.bottomLeft.x, this.bottomLeft.y);
  }
}
