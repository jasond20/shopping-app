package com.b07.store;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import com.b07.inventory.Item;

public interface SalesLog extends Serializable{
  
  /**
   * get the salesLog list.
   * @return the salesLog list.
   */
  public List<Sale> getSalesLog();
  /**
   * set the salesLog list.
   * @param sales the list of sales to be set.
   */
  public void setSalesLog(List<Sale> sales);
  /**
   * get the item to total items sold map.
   * @return the map from item to total items sold.
   */
  public HashMap<Item, Integer> getItemToTotalSold();
  /**
   * set the item to total items sold map.
   * @param itemMap the map of items to total sold to be set.
   */
  public void setItemToTotalSold(HashMap<Item, Integer> itemMap);

}
