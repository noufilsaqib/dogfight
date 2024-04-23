package org.bcit.comp2522.project;

import processing.core.PVector;


/**
 * Interface for all the sprites that can shoot.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public interface IShootable {
  /**
   * Creates the bullets for the sprite to shoot.
   *
   * @return a single bullet
   */
  Bullet shoot();
}
