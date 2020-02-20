//package com.b07.database.helper;
//
//import com.b07.exceptions.ConnectionFailedException;
//import com.b07.inventory.Item;
//import com.b07.store.Account;
//import com.b07.store.Sale;
//import com.b07.users.User;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.nio.file.Files;
//import java.nio.file.InvalidPathException;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DatabaseDeserialize {
//
//  /**Deserialize a database_copy.ser file and convert it to a database object and
//   * insert the information from the object into the application's database.
//   *
//   * @param filePath The path of the database_copy.ser file.
//   * @return success True if successful and false otherwise.
//   * @throws InvalidPathException Exception thrown if path doesn't exist.
//   */
//  public static boolean deserializeDatabase(String filePath) throws InvalidPathException {
//    if (!(Files.isDirectory(Paths.get(filePath)))) {
//      throw new InvalidPathException(filePath, "The Path does not exist.");
//    }
//
//    boolean success = false;
//    File file = new File(filePath + File.separator + "database_copy.ser");
//    try {
//      FileInputStream fileIn = new FileInputStream(file);
//      ObjectInputStream objectIn = new ObjectInputStream(fileIn);
//      DatabaseObject databaseObject = (DatabaseObjectImpl) objectIn.readObject();
//      objectIn.close();
//      fileIn.close();
//      //cleaning old database
//      try {
//        DatabaseDriverHelper.reInitialize();
//      } catch (ConnectionFailedException connectEx) {
//        connectEx.printStackTrace();
//        System.out.println("Connection to the new database failed");
//        return false;
//      }
//      //Inserting the roles in to the new database
//      for (Map.Entry<Integer, String> entry : databaseObject.getRolesIdandName().entrySet()) {
//        try {
//          DatabaseInsertHelper.insertRole(entry.getValue());
//        } catch (IllegalArgumentException e) {
//          System.out.println("Invalid information. Failed to add Role");
//          return success;
//        }
//      }
//
//      //Inserting users into the new database
//      HashMap<User, String> userMapCopy = new HashMap<>(databaseObject.getUserandPassword());
//      for (int i=0; i<databaseObject.getUserandPassword().size();i++) {
//        for (Map.Entry<User, String> entry : userMapCopy.entrySet()) {
//          if (entry.getKey().getId() == i+1) {
//            try {
//              DatabaseInsertHelper.insertNewUserHashed(entry.getKey().getName(),
//                  entry.getKey().getAge(), entry.getKey().getAddress(),
//                  entry.getValue());
//            } catch (IllegalArgumentException e) {
//              System.out.println("Invalid information. Failed to add User");
//              return success;
//            }
//            try {
//              DatabaseInsertHelper.insertUserRole(entry.getKey().getId(),
//                  entry.getKey().getRoleId());
//            } catch (IllegalArgumentException e) {
//              System.out.println("Invalid information. Failed to add User to UserRole table");
//              return success;
//            }
//            userMapCopy.remove(entry.getKey());
//            break;
//          }
//        }
//      }
//
//      //Inserting inventory into the new database
//      HashMap<Item, Integer> itemMapCopy = new HashMap<>(databaseObject.getInventory().getItemMap());
//      for (int i=0; i<databaseObject.getInventory().getItemMap().size();i++) {
//        for (Map.Entry<Item, Integer> entry : itemMapCopy.entrySet()) {
//          if (entry.getKey().getId() == i+1) {
//            try {
//              DatabaseInsertHelper.insertItem(entry.getKey().getName(),
//                  entry.getKey().getPrice());
//            } catch (IllegalArgumentException e) {
//              System.out.println("Invalid information. Failed to add Item");
//              return success;
//            }
//            try {
//              DatabaseInsertHelper.insertInventory(entry.getKey().getId(),
//                  entry.getValue());
//            } catch (IllegalArgumentException e) {
//              System.out.println("Invalid information. Failed to add Item in inventory");
//              return success;
//            }
//            itemMapCopy.remove(entry.getKey());
//            break;
//          }
//        }
//      }
//
//      //Inserting sale and itemized sale into the new database
//      ArrayList<Sale> saleList = new ArrayList<Sale>(databaseObject.getSalesList());
//      for (int i=0; i<databaseObject.getSalesList().size();i++) {
//        for (Sale sale:saleList) {
//          if (sale.getId() == i+1) {
//            try {
//              DatabaseInsertHelper.insertSale(sale.getUser().getId(), sale.getTotalPrice());
//            } catch (IllegalArgumentException e) {
//              System.out.println("Invalid information. Failed to add Sale in database");
//              return success;
//            }
//            for (Map.Entry<Item, Integer> entry : sale.getItemMap().entrySet()) {
//              try {
//                DatabaseInsertHelper.insertItemizedSale(sale.getId(), entry.getKey().getId(), entry.getValue());
//              } catch (IllegalArgumentException e) {
//                System.out.println("Invalid information. Failed to add itemized sale in database");
//                return success;
//              }
//            }
//            saleList.remove(sale);
//            break;
//          }
//        }
//      }
//
//      //Inserting accounts into the new database
//      ArrayList<Account> accountList = new ArrayList<>(databaseObject.getAccountList());
//      for (int i=0; i<databaseObject.getAccountList().size();i++) {
//        for (Account account: accountList) {
//          if (account.getId() == i+1) {
//            try {
//              DatabaseInsertHelper.insertAccount(account.getUserId());
//            } catch (IllegalArgumentException e) {
//              System.out.println("Invalid information. Failed to add Sale in database");
//              return success;
//            }
//            for (Map.Entry<Item, Integer> entry : account.getShoppingCart().getItemsQuantity().entrySet()) {
//              try {
//                DatabaseInsertHelper.insertAccountLine(account.getId(), entry.getKey().getId(),
//                    entry.getValue());
//              } catch (IllegalArgumentException e) {
//                System.out.println("Invalid information. Failed to add Sale in database");
//                return success;
//              }
//            }
//            accountList.remove(account);
//            break;
//          }
//        }
//      }
//      success = true;
//    } catch (IOException ioEx) {
//      ioEx.printStackTrace();
//    } catch (ClassNotFoundException classException) {
//      System.out.println("Class not found. Deserializing process failed");
//      classException.printStackTrace();
//    }
//    return success;
//  }
//}
//
