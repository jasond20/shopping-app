package com.b07.store;

import java.math.BigDecimal;
import java.util.HashMap;
import com.b07.inventory.Item;
import com.b07.users.User;

public class SaleImpl implements Sale{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1779410060347376955L;
  private int id;
  private User user;
  private BigDecimal totalPrice;
  private HashMap<Item, Integer> itemMap;//Item to quantity sold
  
  public SaleImpl (int id, User user, BigDecimal total) {
    this.id = id;
    this.user = user;
    this.totalPrice = total;
    this.itemMap = new HashMap<Item, Integer>();
  }
  
  public SaleImpl (int id, User user, BigDecimal total, HashMap<Item, Integer> itemMap) {
    this.id = id;
    this.user = user;
    this.totalPrice = total;
    this.itemMap = itemMap;
  }
  
  /**
   * get the sale's id.
   * @return the sale's id.
   */
  @Override
  public int getId() {
    return this.id;
  }

  /**
   * set the sale's id.
   * @param id the id to be set.
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }
  
  /**
   * get the sale's user.
   * @return the sale's user.
   */
  @Override
  public User getUser() {
    return this.user;
  }

  /**
   * set the sale's user.
   * @param user the user to be set.
   */
  @Override
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * get the sale's price.
   * @return the sale's price.
   */
  @Override
  public BigDecimal getTotalPrice() {
    return this.totalPrice;
  }
  
  /**
   * set the sale's price.
   * @param price the price to be set.
   */
  @Override
  public void setTotalPrice(BigDecimal price) {
    this.totalPrice = price;
  }
  
  /**
   * get the sale's item map.
   * @return the sale's item map.
   */
  @Override
  public HashMap<Item, Integer> getItemMap() {
    return this.itemMap;
  }

  /**
   * set the sale's item map.
   * @param itemMap the item map to be set.
   */
  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    this.itemMap = itemMap;
  }

}
