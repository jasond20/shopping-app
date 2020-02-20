package com.b07.database.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import com.b07.inventory.Inventory;
import com.b07.store.Account;
import com.b07.store.Sale;
import com.b07.store.SalesLog;
import com.b07.users.User;

public interface DatabaseObject extends Serializable{
  
  public HashMap<Integer, String> getRolesIdandName();

  public HashMap<User, String> getUserandPassword();
  
  public Inventory getInventory();
  
  public ArrayList<Sale> getSalesList();
  
  public ArrayList<Account> getAccountList();
  
}
