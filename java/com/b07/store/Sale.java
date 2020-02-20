package com.b07.store;

import com.b07.inventory.Item;
import com.b07.users.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

public interface Sale extends Serializable{
  
  /**
   * get the sale's id.
   * @return the sale's id.
   */
  public int getId();
  /**
   * set the sale's id.
   * @param id the id to be set.
   */
  public void setId(int id);
  /**
   * get the sale's user.
   * @return the sale's user.
   */
  public User getUser();
  /**
   * set the sale's user.
   * @param user the user to be set.
   */
  public void setUser(User user);
  /**
   * get the sale's price.
   * @return the sale's price.
   */
  public BigDecimal getTotalPrice();
  /**
   * set the sale's price.
   * @param price the price to be set.
   */
  public void setTotalPrice(BigDecimal price);
  /**
   * get the sale's item map.
   * @return the sale's item map.
   */
  public HashMap<Item, Integer> getItemMap();
  /**
   * set the sale's item map.
   * @param itemMap the item map to be set.
   */
  public void setItemMap(HashMap<Item, Integer> itemMap);

}
