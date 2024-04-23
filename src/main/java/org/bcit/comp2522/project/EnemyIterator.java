package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The iterator for the EnemyCollection.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class EnemyIterator<Enemy> implements Iterator<Enemy> {
  protected ArrayList<Enemy> list;
  protected int index;

  /**
   * EnemyIterator constructor.
   *
   * @param enemies the list of enemies
   */
  public EnemyIterator(ArrayList<Enemy> enemies) {
    this.list = enemies;
    this.index = 0;
  }

  /**
   * Returns true if the iteration has more elements.
   *
   * @return true if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return (list.size() >= index + 1);
  }

  /**
   * Returns the next element in the iteration.
   *
   * @return the next element in the iteration
   */
  @Override
  public Enemy next() {
    Enemy e = list.get(index);
    index++;
    return e;
  }
}
