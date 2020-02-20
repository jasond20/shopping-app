package com.b07.database.helper;

import com.b07.inventory.Inventory;
import com.b07.store.Account;
import com.b07.store.Sale;
import com.b07.users.User;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseObjectImpl implements DatabaseObject{

  /**The serialVersionUID of the DatabaseObjectImpl.
   * 
   */
  private static final long serialVersionUID = -1560237155211876583L;
  private HashMap<Integer, String> rolesIdAndName;
  private HashMap<User, String> userAndPassword;
  private Inventory inventory;
  private ArrayList<Sale> salesList;
  private ArrayList<Account> accountList;
  
  /**The constructor for the DatabaseObjectImpl.
   * 
   * @param rolesIdAndName a hashmap of role id's and corresponding role name
   * @param userAndPass a hashmap of users and corresponding hashed passwords
   * @param inventory the inventory of the store
   * @param salesList the list of Sales
   * @param accountList the list of accounts
   */
  public DatabaseObjectImpl(HashMap<Integer, String> rolesIdAndName, HashMap<User, String> userAndPass, 
      Inventory inventory, ArrayList<Sale> salesList, ArrayList<Account> accountList) {
    this.rolesIdAndName = rolesIdAndName;
    this.userAndPassword = userAndPass;
    this.inventory = inventory;
    this.salesList = salesList;
    this.accountList = accountList;
  }
  
  @Override
  public HashMap<Integer, String> getRolesIdandName() {
    return this.rolesIdAndName;
  }

  @Override
  public HashMap<User, String> getUserandPassword() {

    return this.userAndPassword;
  }

  @Override
  public Inventory getInventory() {

    return this.inventory;
  }

  @Override
  public ArrayList<Sale> getSalesList() {

    return this.salesList;
  }

  @Override
  public ArrayList<Account> getAccountList() {

    return this.accountList;
  }

}
