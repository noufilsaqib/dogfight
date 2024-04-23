package org.bcit.comp2522.project;

import processing.core.PVector;
import java.awt.*;
import java.util.Objects;

/**
 * Class for player and enemy projectiles.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class Bullet extends AbstractCharacter {
  private final String pType;
  private final String bType;
  private Color color;
  private Color strokeColor;
  private PVector speed;
  private int damage;

  /**
   * Bullet constructor.
   *
   * @param position the position
   * @param pType the parent type
   * @param bType the bullet type
   * @param window the window
   */
  public Bullet(PVector position, String pType, String bType, Window window) {
    this.window = window;
    this.position = position;
    this.pType = pType;
    this.bType = bType;
    setBullet();
  }

  /**
   * Sets the properties of the bullet depending on its type.
   */
  public void setBullet() {
    if (Objects.equals(bType, "p1")) {
      this.width = 15f;
      this.height = 4f;
      this.speed = new PVector(3f, 0);
      this.color = new Color(255, 225, 0);
      this.strokeColor = new Color(255, 0, 0);
      this.damage = 10;
    } else if (Objects.equals(bType, "p2")) {
      this.width = 20f;
      this.height = 6f;
      this.speed = new PVector(5f, 0);
      this.color = new Color(255, 175, 50);
      this.strokeColor = new Color(255, 0, 0);
      this.damage = 15;
    } else if (Objects.equals(bType, "p3")) {
      this.width = 25f;
      this.height = 8f;
      this.speed = new PVector(7f, 0);
      this.color = new Color(255, 125, 100);
      this.strokeColor = new Color(255, 0, 0);
      this.damage = 20;
    } else if (Objects.equals(bType, "p4")) {
      this.width = 25f;
      this.height = 8f;
      this.speed = new PVector(9f, 0);
      this.color = new Color(255, 50, 150);
      this.strokeColor = new Color(255, 0, 0);
      this.damage = 25;
    } else if (Objects.equals(bType, "enemy1")) {
      this.width = 4f;
      this.height = 4f;
      this.speed = new PVector(-3f, 0);
      this.color = new Color(255, 255, 255);
      this.strokeColor = new Color(255, 0, 214);
      this.damage = 10;
    } else if (Objects.equals(bType, "enemy2")) {
      this.width = 4f;
      this.height = 4f;
      this.speed = new PVector(-4f, window.random(-1, 1));
      this.color = new Color(255, 255, 255);
      this.strokeColor = new Color(34, 171, 0);
      this.damage = 15;
    } else if (Objects.equals(bType, "enemy3")) {
      this.width = 4f;
      this.height = 4f;
      this.speed = new PVector(-5f, 0);
      this.color = new Color(255, 199, 0);
      this.strokeColor = new Color(255, 0, 0);
      this.damage = 20;
    }
  }

  public void draw() {
    window.strokeWeight(1f);
    window.stroke(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue());
    window.fill(color.getRed(), color.getGreen(), color.getBlue());
    window.ellipse(this.position.x, this.position.y, this.width, this.height);

    if (bType.equals("enemy3")) {
      this.width += 0.2;
      this.height += 0.2;
    }
    if (bType.equals("p4")) {
      this.width += 0.1;
      this.height += 0.1;
    }
  }

  public void move() {
    this.setPosition(this.getPosition().add(speed));
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
   * Getter for bullet position.
   *
   * @return the position
   */
  public PVector getPosition() {
    return this.position;
  }

  /**
   * Getter for parent type.
   *
   * @return the type
   */
  public String getParentType() {
    return this.pType;
  }

  /**
   * Getter for bullet type.
   *
   * @return the type
   */
  public String getBulletType() {
    return this.bType;
  }

  /**
   * Getter for the damage value of the bullet.
   *
   * @return the damage
   */
  public int getDamage() {
    return this.damage;
  }


  /**
   * Setter for bullet position.
   *
   * @param position the parent's position
   */
  public void setPosition(PVector position) {
    this.position = position.copy();
  }
}
