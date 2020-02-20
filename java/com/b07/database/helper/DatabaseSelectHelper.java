package com.b07.database.helper;

import android.content.Context;
import android.database.Cursor;

import com.b07.database.DatabaseDriverAndroid;
import com.b07.inventory.Inventory;
import com.b07.inventory.InventoryImpl;
import com.b07.inventory.Item;
import com.b07.inventory.ItemImpl;
import com.b07.store.Account;
import com.b07.store.AccountImpl;
import com.b07.store.Sale;
import com.b07.store.SaleImpl;
import com.b07.store.SalesLog;
import com.b07.store.SalesLogImpl;
import com.b07.store.ShoppingCart;
import com.b07.users.Customer;
import com.b07.users.Roles;
import com.b07.users.User;
import com.b07.users.UserFactory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * TODO: Complete the below methods to be able to get information out of the database.
 * TODO: The given code is there to aide you in building your methods.  You don't have
 * TODO: to keep the exact code that is given (for example, DELETE the System.out.println())
 * TODO: and decide how to handle the possible exceptions
 */
public class DatabaseSelectHelper extends DatabaseDriverAndroid {

  public DatabaseSelectHelper(Context context) {
    super(context);
  }

  //methods return an empty list or null if the result is empty
  
  public List<Integer> getRoleIds() {
    List<Integer> ids = new ArrayList<>();
    Cursor cursor = this.getRoles();
    while (cursor.moveToNext()) {
      ids.add(cursor.getInt(cursor.getColumnIndex("ID")));
    }
    cursor.close();
    return ids;
  }
  
  //returns -1 if name doesn't exist, else return the id of the name
  public int getRoleId(String name) {
    DatabaseSelectHelper dsh = new DatabaseSelectHelper(null);
    for(Integer roleId : dsh.getRoleIds()) {
      if(name.equals(dsh.getRoleName(roleId))){
        dsh.close();
        return roleId;
      }
    }
    dsh.close();
    return -1;
  }
  
  public String getRoleName(int roleId) {
    return this.getRole(roleId);
  }
  
  public int getUserRoleId(int userId) {
    return this.getUserRole(userId);
  }
  
  public List<Integer> getUsersByRoleHelper(int roleId) {
    List<Integer> userIds = new ArrayList<>();
    Cursor cursor = this.getUsersByRole(roleId);

    while (cursor.moveToNext()) {
      //System.out.println(results.getInt("USERID"));
      userIds.add(cursor.getInt(cursor.getColumnIndex("USERID")));
    }
    cursor.close();
    return userIds;
  }

  public List<User> getUsersDetailsHelper() {
    List<User> users = new ArrayList<>();
    Cursor cursor = this.getUsersDetails();
    int roleId = -1;
    String roleName = null;
    DatabaseSelectHelper dsh = new DatabaseSelectHelper(null);
    while (cursor.moveToNext()) {
      roleId = dsh.getUserRoleId(cursor.getInt(cursor.getColumnIndex("ID")));
      roleName = dsh.getRoleName(roleId);
      users.add(UserFactory.userFactory(cursor.getInt(cursor.getColumnIndex("ID")),
          cursor.getString(cursor.getColumnIndex("NAME")), cursor.getInt(cursor.getColumnIndex("AGE")),
          cursor.getString(cursor.getColumnIndex("ADDRESS")), roleName));
    }
    cursor.close();
    return users;
  }
  
  public User getUserDetails(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    User user = null;
    try {
      ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
      if (!results.next()) {
        return null;
      }
      //results.next();
      int roleId = DatabaseSelectHelper.getUserRoleId(results.getInt("ID"));
      String roleName = DatabaseSelectHelper.getRoleName(roleId);
      user = UserFactory.userFactory(results.getInt("ID"), 
          results.getString("NAME"), results.getInt("AGE"), results.getString("ADDRESS"), roleName);
      //System.out.println(results.getInt("ID"));
      //System.out.println(results.getString("NAME"));
      //System.out.println(results.getInt("AGE"));
      //System.out.println(results.getString("ADDRESS"));
      results.close();
      //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return user;
  }
  
  public boolean checkUserExists(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = null;
    try {
      results = DatabaseSelector.getUserDetails(userId, connection);
      if (!results.next()) {//ResultSet is empty
        return false;
      }
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        results.close();
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return true;
  }
  
  public String getPassword(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String password = null;
    try {
      password = DatabaseSelector.getPassword(userId, connection);
      //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlEx1) {
        sqlEx1.printStackTrace();
      }
    }
    return password;
  }
  
  public List<Item> getAllItems() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<Item> items = new ArrayList<>();
    try {
      ResultSet results = DatabaseSelector.getAllItems(connection);
      
      while (results.next()) {
        //System.out.println(results.getInt("ID"));
        //System.out.println(results.getString("NAME"));
        //System.out.println(new BigDecimal(results.getString("PRICE")));
        Item newItem = new ItemImpl(results.getInt("ID"), results.getString("NAME"),
            new BigDecimal(results.getString("PRICE")));
        items.add(newItem);
      }
      results.close();
      //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return items;
  }
  
  public Item getItem(int itemId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Item newItem = null;
    try {
      ResultSet results = DatabaseSelector.getItem(itemId, connection);
      //System.out.println(results.getInt("ID"));
      //System.out.println(results.getString("NAME"));
      //System.out.println(new BigDecimal(results.getString("PRICE")));
      if (!results.next()) {
        return null;
      }
      newItem = new ItemImpl(results.getInt("ID"), results.getString("NAME"),
          new BigDecimal(results.getString("PRICE")));
      results.close();
      //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return newItem;
  }
  
  public Inventory getInventory() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Inventory inventory = null;
    try {
      ResultSet results = DatabaseSelector.getInventory(connection);
      HashMap<Item, Integer> itemsMap = new HashMap<Item, Integer>();
      while (results.next()) {
        //System.out.println(results.getInt("ITEMID"));
        //System.out.println(results.getInt("QUANTITY"));
        itemsMap.put(DatabaseSelectHelper.getItem(results.getInt("ITEMID")), 
            results.getInt("QUANTITY"));
      }
      inventory = new InventoryImpl(itemsMap);
      results.close();
      //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return inventory;
  }
  
  public int getInventoryQuantity(int itemId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int quantity = -1;
    try {
      quantity = DatabaseSelector.getInventoryQuantity(itemId, connection);
      //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return quantity;
  }
  
  public SalesLog getSales() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    SalesLog salesLog = null;
    try {
      ResultSet results = DatabaseSelector.getSales(connection);
      List<Sale> salesList = new ArrayList<>();
      while (results.next()) {
        //System.out.println(results.getInt("ID"));
        //System.out.println(results.getInt("USERID"));
        //System.out.println(new BigDecimal(results.getString("TOTALPRICE")));
        Sale sale = new SaleImpl(results.getInt("ID"), 
            DatabaseSelectHelper.getUserDetails(results.getInt("USERID")),
            new BigDecimal(results.getString("TOTALPRICE")));
        salesList.add(sale);
      }
      results.close();
      //connection.close();
      salesLog = new SalesLogImpl(salesList, null);
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return salesLog;
  }
  
  public Sale getSaleById(int saleId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Sale sale = null;
    try {
      ResultSet results = DatabaseSelector.getSaleById(saleId, connection);
      //System.out.println(results.getInt("ID"));
      //System.out.println(results.getInt("USERID"));
      //System.out.println(new BigDecimal(results.getString("TOTALPRICE")));
      if (!results.next()) {
        return null;
      }
      sale = new SaleImpl(results.getInt("ID"), 
          DatabaseSelectHelper.getUserDetails(results.getInt("USERID")),
          new BigDecimal(results.getString("TOTALPRICE")));
      results.close();
    //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return sale;
  }
  
  public List<Sale> getSalesToUser(int userId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<Sale> salesList = new ArrayList<>();
    try {
      ResultSet results = DatabaseSelectHelper.getSalesToUser(userId, connection);
      while (results.next()) {
        //System.out.println(results.getInt("ID"));
        //System.out.println(results.getInt("USERID"));
        //System.out.println(new BigDecimal(results.getString("TOTALPRICE")));
        Sale sale = new SaleImpl(results.getInt("ID"), 
            DatabaseSelectHelper.getUserDetails(results.getInt("USERID")),
            new BigDecimal(results.getString("TOTALPRICE")));
        salesList.add(sale);
      }
      results.close();
      //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return salesList;
  }
 
  public Sale getItemizedSaleById(int saleId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    Sale sale = null;
    try {
      ResultSet results = DatabaseSelector.getItemizedSaleById(saleId, connection);
      HashMap<Item, Integer> itemMap = new HashMap<>();
      while (results.next()) {
        //System.out.println(results.getInt("SALEID"));
        //System.out.println(results.getInt("ITEMID"));
        //System.out.println(results.getInt("QUANTITY"));
        Item newItem = DatabaseSelectHelper.getItem(results.getInt("ITEMID"));
        itemMap.put(newItem, results.getInt("QUANTITY"));
      }
      results.close();
      //connection.close();
      sale = DatabaseSelectHelper.getSaleById(saleId);
      sale.setItemMap(itemMap);
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return sale;
  }
  
  public SalesLog getItemizedSales() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    SalesLog salesLog = null;
    try {
      ResultSet results = DatabaseSelector.getSales(connection);
      HashMap<Item, Integer> itemToTotalSold = new HashMap<>();
      List<Sale> salesList = new ArrayList<>();
      while (results.next()) {
        //System.out.println(results.getInt("SALEID"));
        //System.out.println(results.getInt("ITEMID"));
        //System.out.println(results.getInt("QUANTITY"));
        Sale sale = DatabaseSelectHelper.getItemizedSaleById(results.getInt("ID"));
        salesList.add(sale);
        
        HashMap<Item, Integer> temp = sale.getItemMap();
        
        for(Item item : temp.keySet()) {
          if(itemToTotalSold.containsKey(item) == false) {
            itemToTotalSold.put(item, temp.get(item));
          } else {
            itemToTotalSold.put(item, temp.get(item) + itemToTotalSold.get(item));
          }
        }
      }
      results.close();
      //connection.close();
      salesLog = new SalesLogImpl(salesList, itemToTotalSold);
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
      connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return salesLog;
  }
  
  public List<Account> getAllAccounts() {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<Account> accounts = new ArrayList<>();
    try {
      ResultSet results = DatabaseSelector.getAllAccounts(connection);
      
      while (results.next()) {
        accounts.add(DatabaseSelectHelper.getAccountDetails(results.getInt("ID")));
      }
      results.close();
      //connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return accounts;
  }
  
  public List<Integer> getUserAccounts(int userId) {
    List<Integer> userAccounts = new ArrayList<>();
    if(!(DatabaseSelectHelper.checkUserExists(userId))) {
      return null;
    }
    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getUserAccounts(userId, connection);
      while(results.next()) {
        userAccounts.add(results.getInt("ID"));
      }
      results.close();
      connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    }
    return userAccounts;
  }
  
  public List<Integer> getUserActiveAccounts(int userId) {
    List<Integer> userActiveAccounts = new ArrayList<>();
    if(!(DatabaseSelectHelper.checkUserExists(userId))) {
      return null;
    }
    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getUserActiveAccounts(userId, connection);
      while(results.next()) {
        userActiveAccounts.add(results.getInt("ID"));
      }
      results.close();
      connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    }
    return userActiveAccounts;
  }
  
  public List<Integer> getUserInactiveAccounts(int userId) {
    List<Integer> userInactiveAccounts = new ArrayList<>();
    if(!(DatabaseSelectHelper.checkUserExists(userId))) {
      return null;
    }
    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getUserInactiveAccounts(userId, connection);
      while(results.next()) {
        userInactiveAccounts.add(results.getInt("ID"));
      }
      results.close();
      connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    }
    return userInactiveAccounts;
  }
  
  public Account getAccountDetails(int accountId) {
    Account account = null;
    int userId = DatabaseSelectHelper.getUserIdByAccountId(accountId);
    if(userId == -1) {
      return null;
    }
    User user = DatabaseSelectHelper.getUserDetails(userId);
    int roleId = DatabaseSelectHelper.getUserRoleId(userId);
    String roleName = DatabaseSelectHelper.getRoleName(roleId);
    if(user == null || !roleName.equals(Roles.CUSTOMER.toString())) {
      return null;
    }
    try {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      //get account active status
      ResultSet results = DatabaseSelector.getAccountInfoByAccountId(accountId, connection);
      if(!results.next()) {
        return null;
      }
      boolean active = false;
      if (results.getInt("ACTIVE") == 1) {
        active = true;
      }
      results.close();
      //get account shopping cart
      results = DatabaseSelector.getAccountDetails(accountId, connection);
      //if(!results.isBeforeFirst()) {//isBeforeFirst will return false if results is empty
      //  return null;
      //}//commented as a shopping cart can be empty
      ShoppingCart shoppingCart = new ShoppingCart((Customer)(user));
      while(results.next()) {
        int itemId = results.getInt("ITEMID");
        shoppingCart.addItem(DatabaseSelectHelper.getItem(itemId), results.getInt("QUANTITY"));
      }
      results.close();
      //create the account
      account = new AccountImpl(accountId, userId, shoppingCart, active);
      connection.close();
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    }
    
    return account;
  }
  
  public int getUserIdByAccountId(int accountId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = null;
    int userId = -1;
    try {
      results = DatabaseSelector.getAccountInfoByAccountId(accountId, connection);
      if (results.next()) {//ResultSet is not empty
        userId = results.getInt("USERID");
      }
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        results.close();
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return userId;
  }
  
  public boolean checkAccountExists(int accountId) {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = null;
    try {
      results = DatabaseSelector.getAccountInfoByAccountId(accountId, connection);
      if (!results.next()) {//ResultSet is empty
        return false;
      }
    } catch (SQLException sqlEx) {
      sqlEx.printStackTrace();
    } finally {
      try {
        results.close();
        connection.close();
      } catch (SQLException sqlClose) {
        sqlClose.printStackTrace();
      }
    }
    return true;
  }
  
}
