package com.b07.inventory;

import java.math.BigDecimal;

public class ItemImpl implements Item{
  
  private static final long serialVersionUID = 8899235020568624869L;
  
  private int id;
  private String name;
  private BigDecimal price;

  public ItemImpl(int id, String name, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }
  /**
   * get the item's id.
   * @return the item's id.
   */
  @Override
  public int getId() {
    return this.id;
  }
  /**
   * set the item's id.
   * @param id the id to be set.
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }
  /**
   * get the item's name.
   * @return the item's name.
   */
  @Override
  public String getName() {
    return this.name;
  }
  /**
   * set the item's name.
   * @param name the name to be set.
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }
  /**
   * get the item's price.
   * @return the item's price.
   */
  @Override
  public BigDecimal getPrice() {
    return this.price;
  }
  /**
   * set the item's price.
   * @param price the price to be set.
   */
  @Override
  public void setPrice(BigDecimal price) {
    this.price = price;

  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    ItemImpl other = (ItemImpl) obj;
    if (this.id != other.getId()) {
      return false;
    }
    if (this.name == null) {
      if (other.getName() != null) {
        return false;
      }
    } else if (!this.name.equals(other.getName())) {
      return false;
    }
    if (this.price == null) {
      if (other.getPrice() != null) {
        return false;
      }
    } else if (!this.price.equals(other.getPrice())) {
      return false;
    }
    return true;
  }
  

}
