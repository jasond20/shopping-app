package com.b07.database.helper;

//import com.b07.exceptions.*;
import android.content.Context;

import com.b07.database.DatabaseDriverAndroid;
import com.b07.users.Roles;
import java.math.BigDecimal;

public class DatabaseUpdateHelper extends DatabaseDriverAndroid {

  public DatabaseUpdateHelper(Context context) {
    super(context);
  }
  
  public boolean updateRoleName(String name, int id) throws IllegalArgumentException{
    boolean complete = false;
    if (name != null && validateRoleName(name)) {
        complete = super.updateRoleName(name, id);
        this.close();
    }else {
      throw new IllegalArgumentException();
    }
    return complete;
  } 
  
  private boolean validateRoleName(String name) {
    for(Roles role : Roles.values()) {
      if(name.equals(role.toString())) {
        return true;
      }
    }
    return false;
  }
  
  public boolean updateUserName(String name, int userId) throws IllegalArgumentException{
    boolean complete = false;
    if (name != null && DatabaseSelectHelper.getUserDetails(userId) != null) {
        complete = super.updateUserName(name, userId);
        this.close();
    }else {
      throw new IllegalArgumentException();
    }
    return complete;
  }
  
  public boolean updateUserAge(int age, int userId) throws IllegalArgumentException{
    boolean complete = false;
    if (age > 0 && DatabaseSelectHelper.getUserDetails(userId) != null) {
      complete = super.updateUserAge(age, userId);
      this.close();
    }else {
      throw new IllegalArgumentException();
    }
    return complete;
  }
  
  public boolean updateUserAddress(String address, int userId) throws IllegalArgumentException{
    boolean complete = false;
    if (address != null && address.length() <= 100 
        && DatabaseSelectHelper.getUserDetails(userId) != null) {
        complete = super.updateUserAddress(address, userId);
        this.close();
    }else {
      throw new IllegalArgumentException();
    }
    return complete;
  }
  
  public boolean updateUserRole(int roleId, int userId) throws IllegalArgumentException{
    boolean complete = false;
    if (DatabaseSelectHelper.getUserDetails(userId) != null 
        && DatabaseSelectHelper.getRoleName(roleId) != null) {
        complete = super.updateUserRole(roleId, userId);
        this.close();
    }else {
      throw new IllegalArgumentException();
    }
    return complete;
  }
  
  /**Returns true if successfully updates the name of the itemId.
   * 
   * @param name the name you want to update the item to
   * @param itemId the item's id
   * @return complete true or false if successful
   */
  public boolean updateItemName(String name, int itemId) throws IllegalArgumentException{
    boolean complete = false;
    if (name != null && name.length() < 64 
        && DatabaseSelectHelper.getItem(itemId) != null) {
        complete = super.updateItemName(name, itemId);
        this.close();
    }else {
      throw new IllegalArgumentException();
    }
    return complete;
  }
  
  public boolean updateItemPrice(BigDecimal price, int itemId) throws IllegalArgumentException{
    boolean complete = false;
    if (price.compareTo(BigDecimal.ZERO) > 0 && DatabaseSelectHelper.getItem(itemId) != null) {
        complete = super.updateItemPrice(price, itemId);
        this.close();
    }else {
      throw new IllegalArgumentException();
    }
    return complete;
  }
  
  /**Returns true if successfully updates the inventory's quantity.
   * 
   * @param quantity Must be greater than or equal to 0
   * @param itemId The id of the item.
   * @return complete true if successful, false otherwise
   */
  public boolean updateInventoryQuantity(int quantity, int itemId) throws IllegalArgumentException{
    boolean complete = false;
    if (quantity >= 0 && DatabaseSelectHelper.getItem(itemId) != null) {
        complete = super.updateInventoryQuantity(quantity, itemId);
        this.close();
    }else {
      throw new IllegalArgumentException();
    }
    return complete;
  }
  
  public boolean updateAccountStatus(int accountId, boolean active) {
    boolean complete = false;
    complete = super.updateAccountStatus(accountId, active);
    this.close();
    return complete;
  }


  public boolean updateAccountLine(int accountId, int itemId, int quantity) {
    boolean complete = false;
    complete = super.updateAccountLine(accountId, itemId, quantity);
    this.close();
    return complete;
  }
  
}
