package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Abstract class for the player and enemy sprites.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class AbstractCharacter {
  protected Window window;
  protected PImage img;
  protected PVector position;
  protected float width;
  protected float height;
  protected String type;
  protected float health;
}