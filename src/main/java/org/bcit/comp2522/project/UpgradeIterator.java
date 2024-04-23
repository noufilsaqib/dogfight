package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The iterator for the UpgradeCollection.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class UpgradeIterator<Upgrade> implements Iterator<Upgrade> {
  protected ArrayList<Upgrade> list;
  protected int index;

  /**
   * UpgradeIterator constructor.
   *
   * @param items the list of upgrades
   */
  public UpgradeIterator(ArrayList<Upgrade> items) {
    this.list = items;
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
  public Upgrade next() {
    Upgrade i = list.get(index);
    index++;
    return i;
  }
}