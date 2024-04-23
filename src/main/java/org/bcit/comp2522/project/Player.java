package org.bcit.comp2522.project;

import processing.core.PVector;

import static java.lang.Math.*;
import static processing.awt.ShimAWT.loadImage;

/**
 * The player in the game.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class Player extends AbstractCharacter implements IShootable {
  private static Player player;
  private float rotAng;
  private int lives;
  private int upgrade;
  /**
   * Player constructor.
   *
   * @param position the center-point position
   * @param window the window
   */
  private Player(PVector position, Window window) {
    this.window = window;
    this.img = loadImage(window, "assets/player.png");
    this.position = position;
    this.width = img.width;
    this.height = img.height;
    this.type = "player";
    this.rotAng = 0;
    this.health = 100;
    this.lives = 3;
    this.upgrade = 1;
  }

  /**
   * Singleton player.
   *
   * @param position the center-point position
   * @param window the window
   * @return a Player
   */
  public static Player getInstance(PVector position, Window window) {
    if (player == null) {
      player = new Player(position, window);
    }

    return player;
  }

  public void draw(Window window) {
    window.push();
    // rotate does not work yet
//    window.rotate(PApplet.radians(rotAng));
    window.image(img, position.x - (width / 2f), position.y - (height / 2f));
    window.pop();

    if (this.health <= 100) {
      this.health += 0.01;
    }
  }

  public void move(Window window) {
    this.rotAng = max(min((window.mouseY - this.position.y) * 0.2f, 45f), -45f);
    this.position.y = (this.position.y * 0.95f) + (window.mouseY * 0.05f);
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
   * Regenerates the player when it loses a life.
   */
  public void regen() {
    this.position = new PVector(50, 0);
    this.lives--;
    this.health = 100;
  }

  /**
   * Creates the bullets for the sprite to shoot.
   *
   * @return a single bullet
   */
  @Override
  public Bullet shoot() {
    String type = "player";
    String btype;

    // set bullet type depending on current upgrade level
    switch (upgrade) {
      case 1:
        btype = "p1";
        break;
      case 2:
        btype = "p2";
        break;
      case 3:
        btype = "p3";
        break;
      default:
        btype = "p4";
        break;
    }
    PVector bPos = new PVector(this.getPosition().x, this.getPosition().y);
    return new Bullet(bPos, type, btype, window);
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
   * Getter for lives.
   *
   * @return the current number of lives
   */
  public int getLives() {
    return this.lives;
  }

  /**
   * Getter for current health.
   *
   * @return the current health
   */
  public float getHealth() {
    return this.health;
  }

  /**
   * Getter for current upgrade level.
   *
   * @return the current upgrade level
   */
  public int getUpgrade() { return this.upgrade; }

  /**
   * Setter for player position.
   * Used in PlayerTest.
   *
   * @param pos the new player position
   */
  public void setPosition(PVector pos) {
    this.position = pos;
  }

  /**
   * Setter for health.
   *
   * @param h the new health value
   */
  public void setHealth(float h) {
    this.health = h;
  }

  /**
   * Setter for lives.
   *
   * @param l the number of lives
   */
  public void setLives(int l) {
    this.lives = l;
  }

  /**
   * Setter for upgrades.
   *
   * @param u the upgrade level
   */
  public void setUpgrade(int u) {
    this.upgrade = u;
  }
}
