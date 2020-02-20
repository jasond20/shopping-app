package com.b07.inventory;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Item extends Serializable{
  
  /**
   * get the item's id.
   * @return the item's id.
   */
  public int getId();
  /**
   * set the item's id.
   * @param id the id to be set.
   */
  public void setId(int id);
  /**
   * get the item's name.
   * @return the item's name.
   */
  public String getName();
  /**
   * set the item's name.
   * @param name the name to be set.
   */
  public void setName(String name);
  /**
   * get the item's price.
   * @return the item's price.
   */
  public BigDecimal getPrice();
  /**
   * set the item's price.
   * @param price the price to be set.
   */
  public void setPrice(BigDecimal price);
  
}
