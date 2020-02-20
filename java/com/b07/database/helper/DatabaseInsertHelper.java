package com.b07.database.helper;

import android.content.Context;

import com.b07.database.DatabaseDriverAndroid;
import com.b07.inventory.ItemTypes;
import com.b07.users.Roles;

import java.math.BigDecimal;

public class DatabaseInsertHelper extends DatabaseDriverAndroid {

  public DatabaseInsertHelper(Context context) {
    super(context);
  }

  /**
   * Inserts the role into the database.
   * @param name - the role to insert
   * @return The id of the inserted role, -1 if invalid input
   * @throws IllegalArgumentException - if name is not a valid role
   */
  public long insertRole(String name) throws IllegalArgumentException {
    if (name == null || !(validateRoleName(name))) {
      throw new IllegalArgumentException();
    }
    long roleId;
    roleId = super.insertRole(name);
    this.close();
    return roleId;
  }
  
  private static boolean validateRoleName(String name) {
    for(Roles role : Roles.values()) {
      if(name.equals(role.toString())) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Inserts a new user into the database using the given user details.
   * @param name - User's name
   * @param age - User's age
   * @param address - User's address
   * @param password - User's password
   * @return The id of the inserted user, -1 if invalid input
   * @throws IllegalArgumentException - if address is over 100 characters
   */
  public long insertNewUser(String name, int age, String address, String password) throws IllegalArgumentException {
    long userId;
    if (name != null && age >= 0 && address != null
        && address.length() <= 100 && password.length() > 0) {
      userId = super.insertNewUser(name, age, address, password);
      this.close();
    } else {
      throw new IllegalArgumentException();
    }
    return userId;
  }

  /**
   * Inserts the given user and role ids into the USERROLE table.
   * @param userId - id of the user from the USERS table
   * @param roleId - id of the role from the ROLES table
   * @return The id of the inserted ids in the USERROLE table, -1 if invalid input
   * @throws IllegalArgumentException - either of the ids are invalid
   */
  public long insertUserRole(int userId, int roleId) throws IllegalArgumentException {
    DatabaseSelectHelper databaseSelect = new DatabaseSelectHelper(null);
    long userRoleId;
    if (!(databaseSelect.checkUserExists(userId))
        || !(databaseSelect.getRoleIds().contains(roleId))) {
      throw new IllegalArgumentException();
    }
    userRoleId = super.insertUserRole(userId, roleId);
    databaseSelect.close();
    this.close();
    return userRoleId;
  }

  /**
   * Inserts the item into the database.
   * @param name - name of the item
   * @param price - price of the item
   * @return The id of the item in the ITEMS table, -1 if invalid input
   * @throws IllegalArgumentException - if name is null, >= 64 characters, price is <= 0, 
   *                                    or price does not have 2 decimal points of precision
   */
  public long insertItem(String name, BigDecimal price) throws IllegalArgumentException {
    long itemId;
    if (name != null && name.length() < 64 && 
        (validateItemName(name)) && price != null 
        && price.compareTo(BigDecimal.ZERO) > 0) {
      price = price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
      itemId = super.insertItem(name, price);
      this.close();
    } else {
      throw new IllegalArgumentException();
    }
    return itemId;
  }

  private static boolean validateItemName(String name) {
    for(ItemTypes type : ItemTypes.values()) {
      if(name.equals(type.toString())) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Inserts the item into the inventory table.
   * @param itemId id of item in ITEMS table
   * @param quantity amount of this item currently available
   * @return The id of the item in the inventory table, -1 if invalid input
   * @throws IllegalArgumentException - if itemId is not in ITEMS or quantity < 0
   */
  public long insertInventory(int itemId, int quantity) throws IllegalArgumentException {
    DatabaseSelectHelper databaseSelect = new DatabaseSelectHelper(null);
    if (!(databaseSelect.getItem(itemId) != null && quantity >= 0)) {
      throw new IllegalArgumentException();
    }
    long inventoryId = super.insertInventory(itemId, quantity);
    this.close();
    databaseSelect.close();
    return inventoryId;
  }

  /**
   * Inserts the sale into the sale table.
   * @param userId  id of the user in the USERS table
   * @param totalPrice  total price of the sale
   * @return The id of the item in the sale table, -1 if invalid input
   * @throws IllegalArgumentException - if totalPrice is null or less than 0
   */
  public long insertSale(int userId, BigDecimal totalPrice) throws IllegalArgumentException {
    DatabaseSelectHelper databaseSelect = new DatabaseSelectHelper(null);
    if (totalPrice == null ||
        totalPrice.compareTo(BigDecimal.ZERO) <= 0 || 
            !(databaseSelect.checkUserExists(userId))) {
      throw new IllegalArgumentException();
    }
    long saleId = super.insertSale(userId, totalPrice);
    this.close();
    databaseSelect.close();
    return saleId;
  }

  /**
   * Inserts the itemized sale into the ITEMIZEDSALES table.
   * @param saleId  id of the sale in the SALES table
   * @param itemId  id of the item in the ITEMS table
   * @param quantity amount of this item currently available
   * @return The id of the item in the sale table, -1 if invalid input
   * @throws IllegalArgumentException - if quantity< 0 or sale/item id don't already exist
   */
  public long insertItemizedSale(int saleId, int itemId, int quantity) throws IllegalArgumentException {
    DatabaseSelectHelper databaseSelect = new DatabaseSelectHelper(null);
    if (quantity <= 0 || databaseSelect.getSaleById(saleId) == null
        || databaseSelect.getItem(itemId) == null) {
      throw new IllegalArgumentException();
    }
    long itemizedId = super.insertItemizedSale(saleId, itemId, quantity);
    this.close();
    databaseSelect.close();
    return itemizedId;
  }
  
  public long insertAccount(int userId) throws IllegalArgumentException {
    DatabaseSelectHelper databaseSelect = new DatabaseSelectHelper(null);
    if(!(databaseSelect.checkUserExists(userId))) {
      throw new IllegalArgumentException();
    }
    long accountId = super.insertAccount(userId, true);
    super.close();
    databaseSelect.close();
    return accountId;
  }
  
  public long insertAccountLine(int accountId, int itemId, int quantity) throws IllegalArgumentException {
    DatabaseSelectHelper databaseSelect = new DatabaseSelectHelper(null);
    if (quantity <= 0 ||
            databaseSelect.getItem(itemId) == null ||
        !(databaseSelect.checkAccountExists(accountId))) {
      throw new IllegalArgumentException();
    }
    long accountLine = super.insertAccountLine(accountId, itemId, quantity);
    this.close();
    databaseSelect.close();
    return accountLine;
  }
  
}
