package com.b07.store;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Item;
import com.b07.users.Customer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = -453794851649227719L;
  private HashMap<Item, Integer> items;
  private Customer customer;
  private BigDecimal total;
  private final BigDecimal TAXRATE = BigDecimal.valueOf(1.13);
  
  public ShoppingCart(Customer customer) {
    items = new HashMap<Item, Integer>();
    //if(customer.getAuthenticated()) {
    this.customer = customer;
    //}
    total = BigDecimal.valueOf(0.0).setScale(2);
  }
  
  public void addItem(Item item, int quantity) {
    if (items.containsKey(item)) {
      items.put(item, items.get(item)+quantity);
    } else {
      items.put(item, quantity);
    }
    total = total.add(item.getPrice().multiply(new BigDecimal(quantity))).setScale(2);
  }
  
  public void removeItem(Item item, int quantity) {
    if (items.get(item) - quantity <= 0) {
      total = total.subtract(item.getPrice().multiply(new BigDecimal(items.get(item)))).setScale(2);
      items.remove(item);
    } else {
      total = total.subtract(item.getPrice().multiply(new BigDecimal(quantity))).setScale(2);
      items.put(item, items.get(item)-quantity);
    }
  }
  
  public List<Item> getItems() {
    List<Item> itemList = new ArrayList<Item>(items.keySet());
    return itemList;
  }
  
  public HashMap<Item, Integer> getItemsQuantity() {
    return this.items;
  }
  
  public Customer getCustomer() {
    return this.customer;
  }
  
  public BigDecimal getTotal() {
    //for (Map.Entry<Item, Integer> entry : items.entrySet()) {
    //  total = total.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(
    //      entry.getValue())).setScale(2));
    //}
    return this.total;
  }
  
  public BigDecimal getTaxRate() {
    return this.TAXRATE;
  }
  
  public void clearCart() {
    items.clear();
  }
  
  public void printShoppingCart() {
    if(items != null && items.size() > 0) {
      System.out.println("Current shopping cart:");
      System.out.printf("%-15s%-40s%-15s %-15s%n", "ITEM ID", "ITEM NAME", "ITEM PRICE", "ITEM QUANTITY");
      for (Map.Entry<Item, Integer> entry : items.entrySet()) {
        Item currentItem = entry.getKey();
        System.out.printf("%-15s%-40s$%-15s%-15d%n", currentItem.getId(), currentItem.getName(), currentItem.getPrice().toString(), 
            entry.getValue());
      }
    }else {
      System.out.println("Shopping cart is empty.");
    }
  }
  
  /**
   * Attempts to check out the customer's current shopping cart. 
   * @return null if successful, else return a HashMap consisting of the items
   * that are out of stock and their associated quantity in the shopping cart
   * @throws DatabaseInsertException
   * @throws SQLException
   */
  public HashMap<Item, Integer> checkOutCustomer() throws DatabaseInsertException, SQLException {
    HashMap<Item, Integer> oosItems = new HashMap<Item, Integer>();//stores the out-of-stock items
    if (this.customer != null) {//check cart has an associated customer
      //calculate total after tax
      BigDecimal totalPrice = this.getTotal().multiply(this.TAXRATE).setScale(2, BigDecimal.ROUND_HALF_UP);
      boolean enough = true;
      //check that there is enough stock in the inventory for every item in the cart,
      //else store the items over the stock limit in oosItems and returns that map at the end of the method
      for (Map.Entry<Item, Integer> entry : items.entrySet()) {
        if (DatabaseSelectHelper.getInventoryQuantity(
            entry.getKey().getId()) < entry.getValue()) {
          oosItems.put(entry.getKey(), DatabaseSelectHelper.getInventoryQuantity(entry.getKey()
              .getId()));
          enough = false;
        }
      }
      if (enough) {
        //insert the sale
        int saleId = DatabaseInsertHelper.insertSale(this.customer.getId(), totalPrice);
        //insert the itemized sales
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
          DatabaseInsertHelper.insertItemizedSale(saleId, entry.getKey().getId(), 
              entry.getValue());
          int originalQuantity = DatabaseSelectHelper.getInventoryQuantity(
              entry.getKey().getId());
          DatabaseUpdateHelper.updateInventoryQuantity(originalQuantity - entry.getValue(),
              entry.getKey().getId());
        }
        this.clearCart();
        return null;
      } 
    }
    return oosItems;
  }
  
}
