package com.b07.store;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.b07.inventory.Item;

public class SalesLogImpl implements SalesLog{

  /**
   * 
   */
  private static final long serialVersionUID = -7353510699902243922L;
  private List<Sale> saleList;
  private HashMap<Item, Integer> itemToTotalSold;
  
  public SalesLogImpl() {
    this.saleList = new ArrayList<Sale>();
    this.itemToTotalSold = new HashMap<Item, Integer>();
  }
  
  public SalesLogImpl(List<Sale> saleList, HashMap<Item, Integer> itemMap) {
    this.saleList = saleList;
    this.itemToTotalSold = itemMap;
  }
  
  /**
   * get the salesLog list.
   * @return the salesLog list.
   */
  @Override
  public List<Sale> getSalesLog() {
    return this.saleList;
  }
  /**
   * set the salesLog list.
   * @param sales the list of sales to be set.
   */
  @Override
  public void setSalesLog(List<Sale> sales) {
    saleList = new ArrayList<Sale>(sales);

  }
  
  /**
   * get the item to total items sold map.
   * @return the map from item to total items sold.
   */
  @Override
  public HashMap<Item, Integer> getItemToTotalSold() {
    return this.itemToTotalSold;
  }
  /**
   * set the item to total items sold map.
   * @param itemMap the map of items to total sold to be set.
   */
  @Override
  public void setItemToTotalSold(HashMap<Item, Integer> itemMap) {
    this.itemToTotalSold = itemMap;

  }
  
  /**
   * Methods to override default serialization with custom serialization since List<> is
   * not serializable, but still want to serialize saleList
   */
  
  private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
    // Default deserialization of SalesLogsImpl
    ois.defaultReadObject();
    saleList = (ArrayList<Sale>)(ois.readObject());
    //itemToTotalSold = (HashMap<Item, Integer>)(ois.readObject());
  }
  
  private void writeObject(ObjectOutputStream oos) throws IOException{
    // Default serialization of SalesLogImpl
    oos.defaultWriteObject();
    oos.writeObject((ArrayList<Sale>)(saleList));
    //oos.writeObject(itemToTotalSold);
  }

}
