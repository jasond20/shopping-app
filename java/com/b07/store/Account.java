package com.b07.store;

import java.io.Serializable;

public interface Account extends Serializable{
  /**
   * Returns the id of the account.
   * @return the account id
   */
  public int getId();
  /**
   * Sets the id of the account.
   * @param id - the account id to set
   */
  public void setId(int id);
  /**
   * Returns the user id of the account.
   * @return the user id
   */
  public int getUserId();
  /**
   * Sets the user id of the account.
   * @param userId - the user id to set
   */
  public void setUserId(int userId);
  /**
   * Returns the shopping cart associated with the account.
   * @return the shopping cart
   */
  public ShoppingCart getShoppingCart();
  /**
   * Sets the shopping cart associated with the account.
   * @param shoppingCart the shopping cart to set
   */
  public void setShoppingCart(ShoppingCart shoppingCart);
  /**
   * Returns if account is active.
   * @return true if active and false if not active
   */
  public boolean getActive();
  /**
   * Sets whether account is active or not.
   * @param active - True if active and False otherwise
   */
  public void setActive(boolean active);
  /**
   * Saves the current account to the database.
   * @return true if successfully saved, false if failed
   */
  public boolean saveAccount();
  
}
