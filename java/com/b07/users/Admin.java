package com.b07.users;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Item;
import com.b07.store.Sale;
import com.b07.store.SalesLog;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Admin extends User {
  
  public Admin(int id, String name, int age, String address) {
    this.setId(id);
    this.setName(name);
    this.setAge(age);
    this.setAddress(address);
  }
  
  public Admin(int id, String name, int age, String address, boolean authenticated) {
    this.setId(id);
    this.setName(name);
    this.setAge(age);
    this.setAddress(address);
    this.setAuthenticated(authenticated);
  }
  
  /**
   * Promotes employee to an admin.
   * @param employee the employee to be promoted.
   * @return True if employee is promoted. False otherwise.
   * @throws SQLException
   * @throws DatabaseInsertException
   */
  public boolean promoteEmployee(Employee employee) throws SQLException, DatabaseInsertException {
    return DatabaseUpdateHelper.updateUserRole(
        DatabaseSelectHelper.getRoleId(Roles.ADMIN.toString()), employee.getId());
  }
  
  /**
   * Prints out historic sales records.
   * @param salesLog the log of sales to be printed.
   */
  public void viewBooks(SalesLog salesLog) {
    if(salesLog != null) {
      BigDecimal totalPrice = BigDecimal.valueOf(0.0).setScale(2);
      List<Sale> listOfSales = salesLog.getSalesLog();
      HashMap<Item, Integer> itemToTotalSold = salesLog.getItemToTotalSold();
      for (int i=0;i<listOfSales.size();i++) {
        System.out.println("Customer: " + listOfSales.get(i).getUser().getName());
        System.out.println("Purchase Number: " + listOfSales.get(i).getId());
        BigDecimal currentPrice = listOfSales.get(i).getTotalPrice().setScale(2);
        System.out.println("Total purchase price: $" + currentPrice);
        totalPrice = totalPrice.add(currentPrice);
        Sale itemizedSale = DatabaseSelectHelper.getItemizedSaleById(listOfSales.get(i).getId());
        int counter = 1;
        for (Map.Entry<Item, Integer> entry : itemizedSale.getItemMap().entrySet()) {
          if (counter == 1) {
            System.out.println("Itemized Breakdown: " + entry.getKey().getName() + ": " + entry.getValue());
            counter = 2;
          } else {
            System.out.println("                    " + entry.getKey().getName() + ": " + entry.getValue());
          }
        }
        System.out.println("--------------------------------------------------------------");
      }
      for (Map.Entry<Item, Integer> entry : itemToTotalSold.entrySet()) {
        System.out.println("Number of " + entry.getKey().getName() + " sold: " + entry.getValue());      
      }
      System.out.println("TOTAL SALES: $" + totalPrice.setScale(2));
    }
  }

}
