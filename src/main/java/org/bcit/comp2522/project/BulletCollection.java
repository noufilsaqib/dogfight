package org.bcit.comp2522.project;

import java.util.HashMap;
import java.util.Iterator;

/**
 * A collection of all the bullets.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class BulletCollection<Bullet> implements Iterable<Bullet> {
  private final HashMap<Bullet, String> bullets;
  private final HashMap<Bullet, String> removeBulletQueue;

  /**
   * BulletCollection constructor.
   */
  public BulletCollection() {
    this.bullets = new HashMap<>();
    this.removeBulletQueue = new HashMap<>();
  }

  /**
   * Adds a bullet to the hashmap.
   *
   * @param b the bullet
   * @param s the type of bullet
   */
  public void addBullet(Bullet b, String s) {
    this.bullets.put(b, s);
  }

  /**
   * Adds a bullet to the remove hashmap.
   *
   * @param b the bullet
   * @param s the type of bullet
   */
  public void removeBullet(Bullet b, String s) {
    this.removeBulletQueue.put(b, s);
  }

  /**
   * Removes the bullets from the hashmap.
   */
  public void removeAllBullets() {
    this.bullets.entrySet().removeAll(this.removeBulletQueue.entrySet());
    this.removeBulletQueue.clear();
  }

  /**
   * Clear all bullets.
   */
  public void clearAllBullets() {
    this.bullets.clear();
    this.removeBulletQueue.clear();
  }

  /**
   * Returns an iterator over elements of type Bullet.
   *
   * @return an Iterator
   */
  @Override
  public Iterator<Bullet> iterator() {
    return bullets.keySet().iterator();
  }
}
