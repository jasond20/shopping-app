//package com.b07.database.helper;
//
//import com.b07.inventory.Inventory;
//import com.b07.store.Account;
//import com.b07.store.Sale;
//import com.b07.users.User;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.nio.file.Files;
//import java.nio.file.InvalidPathException;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class DatabaseSerialize {
//
//  /**Creates a database object and serializes it. Returns true on success and false otherwise.
//   *
//   * @param filePath The specified file path.
//   * @return success True if successful and false otherwise.
//   * @throws InvalidPathException Exception thrown if path doesn't exist.
//   */
//  public static boolean serializeDatabase(String filePath) throws InvalidPathException {
//    if (!(Files.isDirectory(Paths.get(filePath)))) {
//      throw new InvalidPathException(filePath, "The Path does not exist.");
//    }
//
//    boolean success = false;
//
//    HashMap<Integer, String> roleIdsandName = new HashMap<>();
//    List<Integer> roleIds = DatabaseSelectHelper.getRoleIds();
//    for (Integer id : roleIds) {
//      roleIdsandName.put(id, DatabaseSelectHelper.getRoleName(id));
//    }
//
//    HashMap<User, String> userAndPass = new HashMap<>();
//
//    List<User> users = DatabaseSelectHelper.getUsersDetails();
//
//    for (User individual : users) {
//      userAndPass.put(individual, DatabaseSelectHelper.getPassword(individual.getId()));
//    }
//
//    ArrayList<Account> accounts = (ArrayList<Account>) DatabaseSelectHelper.getAllAccounts();
//    Inventory inventory = DatabaseSelectHelper.getInventory();
//    ArrayList<Sale> salesList = (ArrayList<Sale>) DatabaseSelectHelper.getItemizedSales().getSalesLog();
//
//    DatabaseObject databasetoSerial = new DatabaseObjectImpl(roleIdsandName, userAndPass,
//        inventory, salesList, accounts);
//
//    File file = new File(filePath + File.separator + "database_copy.ser");
//
//    try {
//      FileOutputStream fileOut = new FileOutputStream(file);
//      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
//
//      objectOut.writeObject(databasetoSerial);
//
//      objectOut.close();
//      fileOut.close();
//
//      success = true;
//
//    } catch (IOException ioEx) {
//      ioEx.printStackTrace();
//    }
//
//    return success;
//
//  }
//
//}
