package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A collection of all the enemies.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class EnemyCollection<Enemy> implements Iterable<Enemy> {
  private final ArrayList<Enemy> enemies;
  private final ArrayList<Enemy> removeEnemyQueue;
  private int count;

  /**
   * EnemyCollection constructor.
   */
  public EnemyCollection() {
    this.enemies = new ArrayList<>();
    this.removeEnemyQueue = new ArrayList<>();
    this.count = 0;
  }

  /**
   * Adds an enemy to the arraylist.
   *
   * @param e the enemy
   */
  public void addEnemy(Enemy e) {
    this.enemies.add(e);
    this.count++;
  }

  /**
   * Adds an enemy to the remove arraylist.
   *
   * @param e the enemy
   */
  public void removeEnemy(Enemy e) {
    this.removeEnemyQueue.add(e);
    this.count--;
  }

  /**
   * Removes the enemies from the arraylist.
   */
  public void removeAllEnemies() {
    this.enemies.removeAll(this.removeEnemyQueue);
    this.removeEnemyQueue.clear();
  }

  /**
   * Clear all enemies.
   */
  public void clearAllEnemies() {
    this.enemies.clear();
    this.removeEnemyQueue.clear();
    this.count = 0;
  }

  /**
   * Getter method for number of enemies in the collection.
   */
  public int getCount() {
    return this.count;
  }

  /**
   * Setter method for number of enemies in the collection.
   */
  public void setCount(int c) {
    this.count = c;
  }

  /**
   * Returns an iterator over elements of type Enemy.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<Enemy> iterator() {
    return new EnemyIterator<Enemy>(enemies);
  }


}
