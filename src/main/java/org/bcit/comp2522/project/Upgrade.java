package org.bcit.comp2522.project;

import processing.core.PVector;

import static processing.awt.ShimAWT.loadImage;

/**
 * Class for player upgrade items.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class Upgrade extends AbstractCharacter {
  private float verticaldirection;

  /**
   * Upgrade Item constructor
   *
   * @param position the position
   * @param type the upgrade type
   * @param window the window
   */
  public Upgrade(PVector position, String type, Window window) {
    this.window = window;
    this.position = position;
    this.type = type;
    this.verticaldirection = (float) (Math.random() * (1.5 - (-1.5)) + (-1.5));
    setUpgrade();
  }

  /**
   * Sets the sprite image for the item depending on type.
   */
  public void setUpgrade() {
    switch (type) {
      // restores health
      case "health" -> {
        this.img = loadImage(window, "assets/health.png");
        this.width = img.width;
        this.height = img.height;
      }
      // slows down enemies
      case "speed" -> {
        this.img = loadImage(window, "assets/speed.png");
        this.width = img.width;
        this.height = img.height;
      }
      // increases upgrade level
      case "upgrade" -> {
        this.img = loadImage(window, "assets/upgrade.png");
        this.width = img.width;
        this.height = img.height;
      }
    }
  }

  public void draw() {
    window.push();
    window.image(img, position.x - (width / 2f), position.y - (height / 2f));
    window.pop();
  }

  public void move() {
    this.position.x -= 2f;
    this.position.y += verticaldirection;
    if (this.position.y <= 0) {
      verticaldirection = -(verticaldirection);
    } else if (this.position.y >= window.height) {
      verticaldirection = -(verticaldirection);
    }
  }

  /**
   * Gets the bounding box for collision detection.
   *
   * @return an on-the-fly BoundingBox
   */
  public BoundingBox getBoundingBox() {return new BoundingBox(position, width, height, window);
  }
}
