package com.b07.store;

import java.util.Map;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.inventory.Inventory;
import com.b07.inventory.InventoryImpl;
import com.b07.inventory.Item;
import com.b07.users.Employee;

public class EmployeeInterface {
  
  private Employee currentEmployee;
  private Inventory inventory;
  
  public EmployeeInterface(Employee employee, Inventory inventory) {
    this.currentEmployee = employee;
    if(inventory != null) {
      this.inventory = inventory;
    }else {
      this.inventory = new InventoryImpl(null);
    }
  }
  
  public EmployeeInterface(Inventory inventory){
    this.inventory = inventory;

  }
  
  public void setCurrentEmployee(Employee employee) {
    this.currentEmployee = employee;
  }
  
  public boolean hasCurrentEmployee() {
    if (currentEmployee != null) {
      return true;
    }
    return false;
  }
  
  public boolean restockInventory(Item item, int quantity){
    if(item == null) {
      return false;
    }
    int currentQuantity = DatabaseSelectHelper.getInventoryQuantity(item.getId());
    boolean success = false;
    try{
      success = DatabaseUpdateHelper.updateInventoryQuantity(currentQuantity+quantity, item.getId());
    }catch(IllegalArgumentException e) {
      //e.printStackTrace();
      success = false;
    }
    return success;
  }
  
  public void refreshInventory() {
    this.inventory = DatabaseSelectHelper.getInventory();
  }
  
  public void viewInventory() {
    if(this.inventory != null) {
      this.inventory.viewInventory();
    }
  }
  
  public int createCustomer (String name, int age, String address, String password){    
    int userId = -1;
    try{
      userId = DatabaseInsertHelper.insertNewUser(name, age, address, password);
      int roleId = DatabaseInsertHelper.insertRole("CUSTOMER");
      DatabaseInsertHelper.insertUserRole(userId, roleId);
    }catch(IllegalArgumentException e) {
      //e.printStackTrace();
      userId = -1;
    }
    return userId;
  }
  
  public int createEmployee (String name, int age, String address, String password){
    int userId = -1;
    try {
      userId = DatabaseInsertHelper.insertNewUser(name, age, address, password);
      int roleId = DatabaseInsertHelper.insertRole("EMPLOYEE");
      DatabaseInsertHelper.insertUserRole(userId, roleId);
    }catch(IllegalArgumentException e) {
      //e.printStackTrace();
      userId = -1;
    }
    return userId;
  }
  
  public int createAccount(int userId) {
    int accountId = -1;
    try {
      accountId = DatabaseInsertHelper.insertAccount(userId);
    }catch(IllegalArgumentException e) {
      //e.printStackTrace();
      accountId = -1;
    }
    return accountId;
  }

}
