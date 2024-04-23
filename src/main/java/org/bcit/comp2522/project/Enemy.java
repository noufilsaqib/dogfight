package org.bcit.comp2522.project;

import processing.core.PVector;

import static processing.awt.ShimAWT.loadImage;

/**
 * The enemy in the game.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class Enemy extends AbstractCharacter implements IShootable {
  private float speed;
  private final String eType;
  private int health;
  private int startTime;

  /**
   * Enemy constructor.
   *
   * @param position the center-point position
   * @param window the window
   */
  public Enemy(PVector position, String eType, Window window) {
    this.window = window;
    this.position = position;
    this.type = "enemy";
    this.eType = eType;
    setEnemy();
  }

  /**
   * Sets the enemy properties depending on its type.
   */
  public void setEnemy() {
    switch (eType) {
      case "enemy1" -> {
        this.img = loadImage(window, "assets/enemy1.png");
        this.width = img.width;
        this.height = img.height;
        this.health = 10;
        this.speed = 2f;
      }
      case "enemy2" -> {
        this.img = loadImage(window, "assets/enemy2.png");
        this.width = img.width;
        this.height = img.height;
        this.health = 25;
        this.speed = 2.5f;
      }
      case "enemy3" -> {
        this.img = loadImage(window, "assets/enemy3.png");
        this.width = img.width;
        this.height = img.height;
        this.health = 75;
        this.speed = 3f;
      }
    }
  }

  public void draw(Window window) {
    window.image(img, position.x - (width / 2f), position.y - (height / 2f));
  }

  public void move() {
    this.position.x = position.x - speed;
  }

  /**
   * Gets the bounding box for collision detection.
   *
   * @return an on-the-fly BoundingBox
   */
  public BoundingBox getBoundingBox() {
    return new BoundingBox(position, width, height, window);
  }

  /**
   * Checks if the enemy is inside the window.
   *
   * @return true
   */
  public boolean isInWindow() {
    this.startTime = window.millis();
    return (this.getBoundingBox().getLeft() < window.width);
  }

  /**
   * Creates the bullets for the sprite to shoot.
   *
   * @return a single bullet
   */
  @Override
  public Bullet shoot() {
    PVector bPos = new PVector(this.getPosition().x, this.getPosition().y);
    return new Bullet(bPos, type, eType, window);
  }

  /**
   * Getter for sprite position.
   *
   * @return the position
   */
  public PVector getPosition() {
    return this.position;
  }

  /**
   * Getter for enemy health.
   *
   * @return the position
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Getter for sprite type.
   *
   * @return the type
   */
  public String getType() {
    return this.eType;
  }

  /**
   * Getter for current time.
   *
   * @return the time
   */
  public int getStartTime() {
    return startTime;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  /**
   * Setter for enemy health.
   */
  public void setHealth(int dmg) {
    this.health -= dmg;
  }

  /**
   * Setter for current time.
   *
   * @param currTime the new time
   */
  public void setStartTime(int currTime) {
    this.startTime = currTime;
  }

  /**
   * Setter for enemy position.
   * Used in EnemyTest.
   *
   * @param pos the new enemy position
   */
  public void setPosition(PVector pos) {
    this.position = pos;
  }
}
