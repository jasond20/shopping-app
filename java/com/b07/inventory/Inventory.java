package com.b07.inventory;

import java.io.Serializable;
import java.util.HashMap;

public interface Inventory extends Serializable{
  
  /**
   * get the inventory's item map.
   * @return the inventory's item map.
   */
  public HashMap<Item, Integer> getItemMap();
  /**
   * set the iventory's item map.
   * @param itemMap the item map to be set.
   */
  public void setItemMap(HashMap<Item, Integer> itemMap);
  /**
   * updates inventory's item map.
   * @param item the item to be added.
   * @param value the value of the item added.
   */
  public void updateMap(Item item, Integer value);
  /**
   * get the inventory's number of items.
   * @return the inventory's number of items.
   */
  public int getTotalItems();
  /**
   * set the inventory's number of items.
   * @param total the total number of items in inventory.
   */
  public void setTotalItems(int total);
  /**
   * Prints the current inventory to console.
   */
  public void viewInventory();
}
