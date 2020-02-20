package com.b07.inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryImpl implements Inventory{
  
  private static final long serialVersionUID = 5696126867711310298L;
  private HashMap<Item, Integer> itemMap;
  private int totalItems;
  
  public InventoryImpl(HashMap<Item, Integer> itemMap) {
    if(itemMap != null) {
      this.itemMap = itemMap;
      this.totalItems = itemMap.size();
    }else {
      this.itemMap = new HashMap<Item, Integer>();
      this.totalItems = 0;
    }
  }
  /**
   * get the inventory's item map.
   * @return the inventory's item map.
   */
  @Override
  public HashMap<Item, Integer> getItemMap() {
    return this.itemMap;
  }
  /**
   * set the iventory's item map.
   * @param itemMap the item map to be set.
   */
  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
      this.itemMap.put(entry.getKey(), entry.getValue());
    }
  }
  /**
   * updates inventory's item map.
   * @param item the item to be added.
   * @param value the value of the item added.
   */
  @Override
  public void updateMap(Item item, Integer value) {
    if (itemMap.containsKey(item)) {
      this.itemMap.put(item, itemMap.get(item)+value);
    }else {
      this.itemMap.put(item, value);
    }
  }
  /**
   * get the inventory's number of items.
   * @return the inventory's number of items.
   */
  @Override
  public int getTotalItems() {
    return this.totalItems;
  }
  /**
   * set the inventory's number of items.
   * @param total the total number of items in inventory.
   */
  @Override
  public void setTotalItems(int total) {
    this.totalItems = total;
  }
  
  /**
   * Prints the current inventory to console.
   */
  @Override
  public void viewInventory() {
    if(this.itemMap != null && 
        this.itemMap.size() > 0) {
      System.out.println("Current inventory:");
      System.out.printf("%-15s%-40s%-15s %-15s%n", "ITEM ID", "ITEM NAME", "ITEM PRICE", "ITEM QUANTITY");
      for(Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
        Item currentItem = entry.getKey();
        System.out.printf("%-15s%-40s$%-15s%-15d%n", currentItem.getId(), currentItem.getName(), currentItem.getPrice().toString(), 
            entry.getValue());
      }
    }else {
      System.out.println("Inventory is empty.");
    }
  }
  
}
