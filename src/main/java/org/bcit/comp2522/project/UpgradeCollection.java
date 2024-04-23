package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A collection of all the upgrades.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class UpgradeCollection<Upgrade> implements Iterable<Upgrade> {
  private final ArrayList<Upgrade> upgrades;
  private final ArrayList<Upgrade> removeUpgradeQueue;
  private int count;

  /**
   * UpgradeCollection constructor.
   */
  public UpgradeCollection() {
    this.upgrades = new ArrayList<>();
    this.removeUpgradeQueue = new ArrayList<>();
    this.count = 0;
  }

  public int getCount(){
    return this.count;
  }

  /**
   * Adds an upgrade to the arraylist.
   *
   * @param i the upgrade
   */
  public void addUpgrade(Upgrade i) {
    this.upgrades.add(i);
    this.count++;
  }

  /**
   * Adds an upgrade to the remove arraylist.
   *
   * @param i the upgrade
   */
  public void removeUpgrade(Upgrade i) {
    this.removeUpgradeQueue.add(i);
    this.count--;
  }

  /**
   * Removes the upgrades from the arraylist.
   */
  public void removeAllUpgrades() {
    this.upgrades.removeAll(this.removeUpgradeQueue);
    this.removeUpgradeQueue.clear();
  }

  /**
   * Clear all upgrades from upgrades and removeUpgradeQueue and set count to 0.
   */
  public void clearAllUpgrades() {
    this.upgrades.clear();
    this.removeUpgradeQueue.clear();
    this.count = 0;
  }

  /**
   * Returns an iterator over elements of type Item.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<Upgrade> iterator() {
    return new UpgradeIterator<Upgrade>(upgrades);
  }
}

