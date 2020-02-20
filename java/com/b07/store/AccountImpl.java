package com.b07.store;

import java.util.List;
import java.util.Map;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.inventory.Item;

public class AccountImpl implements Account{
  /**
   * 
   */
  private static final long serialVersionUID = 913078410054905895L;
  private int id;
  private int userId;
  private ShoppingCart shoppingCart;
  private boolean active;
  
  public AccountImpl(int id, int userId, ShoppingCart shoppingCart, boolean active) {
    this.id = id;
    this.userId = userId;
    this.shoppingCart = shoppingCart;
    this.active = active;
  }
  
  /**
   * Returns the id of the account.
   * @return the account id
   */
  @Override
  public int getId() {
    return this.id;
  }

  /**
   * Sets the id of the account.
   * @param id - the account id to set
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Returns the user id of the account.
   * @return the user id
   */
  @Override
  public int getUserId() {
    return this.userId;
  }

  /**
   * Sets the user id of the account.
   * @param userId - the user id to set
   */
  @Override
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * Returns the shopping cart associated with the account.
   * @return the shopping cart
   */
  @Override
  public ShoppingCart getShoppingCart() {
    return this.shoppingCart;
  }

  /**
   * Sets the shopping cart associated with the account.
   * @param the shopping cart to set
   */
  @Override
  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }
  
  /**
   * Returns if account is active.
   * @return 1 if active and 0 if not active
   */
  @Override
  public boolean getActive() {
    return this.active;
  }
  
  /**
   * Sets whether account is active or not.
   * @param active - the activeness of the account (1 for active and 0 for inactive)
   */
  @Override
  public void setActive(boolean active) {
    this.active = active;
  }
  
  /**
   * Saves the current account to the database.
   * @return true if successfully saved, false if failed
   */
  @Override
  public boolean saveAccount() {
    boolean success = true;
    Account currentAccountInDB = DatabaseSelectHelper.getAccountDetails(id);
    if(currentAccountInDB != null) {
      ShoppingCart currentShoppingCartInDB = currentAccountInDB.getShoppingCart();
      List<Item> currentItemsInDB = currentShoppingCartInDB.getItems();
      //Iterate through items in the current shopping cart and either insert or update them
      //in the database
      for (Map.Entry<Item, Integer> entry : shoppingCart.getItemsQuantity().entrySet()) {
        Item currentItem = entry.getKey();
        if(currentItemsInDB.contains(currentItem)) {
          //Current Item already in database, update it
          if(!DatabaseUpdateHelper.updateAccountLine(id, currentItem.getId(), entry.getValue())){
            success = false;
          }
        }else {
          //Current item is not in database, insert it
          if(DatabaseInsertHelper.insertAccountLine(id, currentItem.getId(), entry.getValue()) == -1) {
            success = false;
          }
        }
        
      }
    }else {
      success = false;
    }
    return success;
  }
  
}
