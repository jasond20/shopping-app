package com.b07.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.inventory.ItemTypes;
import com.b07.security.PasswordHelpers;
import com.b07.store.Account;

public class UserInput {
  public static final int INVALID_INT = -1;
  public static final String INVALID_STRING = null;
  public static final BigDecimal INVALID_BIG_DECIMAL = new BigDecimal("-1");
  public static final int PASSWORD_ATTEMPT_LIMIT = 3;
  
  /**
   * Returns an integer entered in the console.
   * @param br the BufferedReader to use for input
   * @return the integer entered by the user, or -1 if an exception occurs
   */
  public static int getIntInput(BufferedReader br) {
    int input = INVALID_INT;
    try {
      input = Integer.parseInt(br.readLine());
    }catch(IOException e) {
      //e.printStackTrace();
      input = INVALID_INT;
    }catch(NumberFormatException e) {
      //e.printStackTrace();
      input = INVALID_INT;
    }
    return input;
  }
  
  /**
   * Returns a string entered in the console.
   * @param br the BufferedReader to use for input
   * @return the integer entered by the user, or null if an exception occurs
   */
  public static String getStringInput(BufferedReader br) {
    String input = INVALID_STRING;
    try {
      input = br.readLine();
    }catch(IOException e) {
      //e.printStackTrace();
      input = INVALID_STRING;
    }
    return input;
  }
  
  /**
   * Returns a decimal number entered in the console.
   * @param br the BufferedReader to use for input
   * @return a BigDecimal representing the decimal entered by the user, or
   * a BigDecimal representing -1 if an exception occurs
   */
  public static BigDecimal getDecimalInput(BufferedReader br) {
    BigDecimal input = INVALID_BIG_DECIMAL;
    try {
      input = new BigDecimal(br.readLine());
    }catch(IOException e) {
      //e.printStackTrace();
      input = INVALID_BIG_DECIMAL;
    }catch(NumberFormatException e) {
      //e.printStackTrace();
      input = INVALID_BIG_DECIMAL;
    }
    return input;
  }
  
  /**
   * Returns input after checking input for basic validity and re-prompting user
   * for new input if input is invalid
   * @param br the BufferedReader to use for input
   * @param input the input to validate
   * @return the validated input
   */
  public static int validateIntInput(BufferedReader br, int input) {
    while(input == INVALID_INT) {
      System.out.println("Invalid input. Try again:");
      input = getIntInput(br);
    }
    return input;
  }
  
  /**
   * Returns input after checking input for basic validity and re-prompting user
   * for new input if input is invalid
   * @param br the BufferedReader to use for input
   * @param input the input to validate
   * @return the validated input
   */
  public static String validateStringInput(BufferedReader br, String input) {
    while(input == null || input.equals(INVALID_STRING)) {
      System.out.println("Invalid input. Try again:");
      input = getStringInput(br);
    }
    return input;
  }
  
  /**
   * Returns input after checking input for basic validity and re-prompting user
   * for new input if input is invalid
   * @param br the BufferedReader to use for input
   * @param input the input to validate
   * @return the validated input
   */
  public static BigDecimal validateDecimalInput(BufferedReader br, BigDecimal input) {
    while(input.equals(INVALID_BIG_DECIMAL)) {
      System.out.println("Invalid input. Try again:");
      input = getDecimalInput(br);
    }
    return input;
  }
  
  /**
   * Returns a user-inputted integer after basic validation
   * @param br the BufferedReader to use for input
   * @return the validated input entered by the user
   */
  public static int getValidatedIntInput(BufferedReader br) {
    int input = getIntInput(br);
    return validateIntInput(br, input);
  }
  
  /**
   * Returns a user-inputted string after basic validation
   * @param br the BufferedReader to use for input
   * @return the validated input entered by the user
   */
  public static String getValidatedStringInput(BufferedReader br) {
    String input = getStringInput(br);
    return validateStringInput(br, input);
  }
  
  /**
   * Returns a user-inputted decimal after basic validation
   * @param br the BufferedReader to use for input
   * @return the validated input entered by the user
   */
  public static BigDecimal getValidatedDecimalInput(BufferedReader br) {
    BigDecimal input = getDecimalInput(br);
    return validateDecimalInput(br, input);
  }
  
  /**
   * Returns the user's attempt for the password associated with the user
   * that has the id userId, with a certain limit of attempts allowed.
   * @param br the BufferedReader to use for input
   * @param userId the id of the user for which to guess the password of
   * @return the correct password if guessed correctly, the last incorrect
   * password entered if not guessed within the password attempt limit, and
   * null if the user entered invalid input as the last guess.
   */
  public static String getPasswordAttempt(BufferedReader br, int userId) {
    System.out.println("Please enter the password for this user:");
    String inputPassword = getValidatedStringInput(br);
    int passwordAttemptLimit = PASSWORD_ATTEMPT_LIMIT;
    while(passwordAttemptLimit > 0 && (inputPassword == null || 
        !PasswordHelpers.comparePassword(DatabaseSelectHelper.getPassword(userId),
        inputPassword))) {
      System.out.println("Incorrect: " + passwordAttemptLimit + " tries remaining.");
      System.out.println("Please try entering password again:");
      inputPassword = getValidatedStringInput(br);
      passwordAttemptLimit--;
    }
    return inputPassword;
  }
  
  /**
   * Prompts user to input a User's name, returning their input.
   * @param br the BufferedReader to use for input
   * @param userType the type of User being prompted for, should be lowercase
   * @return the name of the User entered by the user
   */
  public static String getUserNameInput(BufferedReader br, String userType) {
    System.out.println("Please enter name of " + userType + ":");
    return getValidatedStringInput(br);
  }
  
  /**
   * Prompts user to input a User's age, returning their input.
   * @param br the BufferedReader to use for input
   * @param userType the type of User being prompted for, should be lowercase
   * @return the age of the User entered by the user
   */
  public static int getUserAgeInput(BufferedReader br, String userType) {
    System.out.println("Please enter age of " + userType + " (must be positive):");
    return getValidatedIntInput(br);
  }
  
  /**
   * Prompts user to input a User's address, returning their input.
   * @param br the BufferedReader to use for input
   * @param userType the type of User being prompted for, should be lowercase
   * @return the address of the User entered by the user
   */
  public static String getUserAddressInput(BufferedReader br, String userType) {
    System.out.println("Please enter address of " + userType + " (100 character limit):");
    return getValidatedStringInput(br);
  }
  
  /**
   * Prompts user to input a User's password, returning their input.
   * @param br the BufferedReader to use for input
   * @param userType the type of User being prompted for, should be lowercase
   * @return the password of the User entered by the user
   */
  public static String getUserPasswordInput(BufferedReader br, String userType) {
    System.out.println("Please enter password of " + userType + ":");
    return getValidatedStringInput(br);
  }
  
  /**
   * Returns a valid user id for a User of type userType entered by the user,
   * or 0 if user decides to cancel input
   * @param br the BufferedReader to use for input
   * @param userType the type of User being prompted for, should be a valid user role
   * @return a valid user id matching the given userType
   */
  public static int getValidatedUserId(BufferedReader br, String userType) {
    userType = userType.toLowerCase();
    System.out.println("Please enter " + userType + " id (enter 0 to cancel):");
    int userId = UserInput.getValidatedIntInput(br);
    User tempUser = DatabaseSelectHelper.getUserDetails(userId);
    while(userId != 0 && !(checkUserType(tempUser, userType))) {
      System.out.println("Invalid " + userType + " id. Try again (enter 0 to cancel):");
      userId = UserInput.getValidatedIntInput(br);
      tempUser = DatabaseSelectHelper.getUserDetails(userId);
    }
    return userId;
  }
  
  /**
   * Returns if user is of type userType
   * @param user the user to check
   * @param userType the type to check if the user is, should be ADMIN,
   * EMPLOYEE, or CUSTOMER
   * @return true if user is of type userType, false otherwise
   */
  private static boolean checkUserType(User user, String userType) {
    if(userType == null) {
      return false;
    }
    try {
    switch(Roles.valueOf(userType.toUpperCase())) {
      case ADMIN:
        if(user instanceof Admin) {
          return true;
        }
        break;
      case EMPLOYEE:
        if(user instanceof Employee) {
          return true;
        }
        break;
      case CUSTOMER:
        if(user instanceof Customer) {
          return true;
        }
        break;
      default:
        return false;
    }
    }catch(IllegalArgumentException e) {
      return false;
    }catch(NullPointerException e) {
      return false;
    }
    return false;
  }
  
  /**
   * Returns a valid item name entered by the user. a valid item name
   * must be in the ItemTypes enum.
   * @param br the BufferedReader to use for input
   * @return a valid item name entered by the user
   */
  public static String getValidatedItemName(BufferedReader br) {
    System.out.println("Please enter item's name exactly:");
    String itemName = UserInput.getValidatedStringInput(br);
    while(!checkValidItemName(itemName)) {
      System.out.println("Invalid item name. Try again:");
      itemName = UserInput.getValidatedStringInput(br);
    }
    return itemName;
  }
  
  /**
   * Returns if the given itemName is a valid item name, which is true if
   * the itemName matches the name of an item in the ItemTypes enum and
   * false otherwise.
   * @param itemName the name of the item to check
   * @return true if itemName matches an item name in ItemTypes enum,
   * false otherwise
   */
  private static boolean checkValidItemName(String itemName) {
    for(ItemTypes itemType : ItemTypes.values()) {
      if(itemName.equals(itemType.name())) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Returns a valid item price (positive with 2 decimal places) entered by the user.
   * @param br the BufferedReader to use for input
   * @return a valid item price entered by the user
   */
  public static BigDecimal getValidatedItemPrice(BufferedReader br) {
    System.out.println("Please enter item's price (positive with 2 decimal places):");
    BigDecimal itemPrice = UserInput.getValidatedDecimalInput(br);
    while(!checkValidPriceScale(itemPrice) || itemPrice.compareTo(new BigDecimal("0")) <= 0) {
      System.out.println("Invalid item price. Try again:");
      itemPrice = UserInput.getValidatedDecimalInput(br);
    }
    try {
      itemPrice = itemPrice.setScale(2);
    }catch(ArithmeticException e) {
      //e.printStackTrace();
    }
    return itemPrice;
  }
  
  /**
   * Returns if the given itemPrice has a valid scale for a price, which is 2.
   * @param itemPrice the price of an item to check
   * @return true if itemPrice can be set to have a scale of 2, false otherwise
   */
  private static boolean checkValidPriceScale(BigDecimal itemPrice) {
    boolean valid = true;
    try {
      itemPrice = itemPrice.setScale(2);
    }catch(ArithmeticException e) {
      //e.printStackTrace();
      valid = false;
    }
    return valid;
  }
  
  /**
   * Returns the account id selected by the customer after displaying all of that customer's
   * active accounts.
   * @param br the BufferedReader to use for input
   * @param customerId the id of the customer to get an account for
   * @return the id of the account selected by the user, -1 if the customer has no accounts,
   * or 0 if the customer decides to not choose an account
   */
  public static int getUserSelectedActiveAccount(BufferedReader br, int customerId) {
    int selectedAccountId;
    List<Integer> customerAccounts = DatabaseSelectHelper.getUserActiveAccounts(customerId);
    if(customerAccounts != null && customerAccounts.size() > 0) {
      System.out.println(" ______________________________________ \n" +
                         "|                                      |\n" +
                         "| An active account(s) has been found. |\n" +
                         "|  Would you like to view/load them?   |\n" +
                         "|______________________________________|\n" +
                         "|1. Yes                                |\n" +
                         "|2. No                                 |\n" +
                         "|______________________________________|\n" +
                         "Choose an option:");
      int accountOption = UserInput.getValidatedIntInput(br);
      while(accountOption > 2 || accountOption < 1) {
        System.out.println("Invalid option. Please try again:");
        accountOption = UserInput.getValidatedIntInput(br);
      }
      if(accountOption == 1) {
        for (int i = 0; i < customerAccounts.size(); i++) {
          int currentAccountId = customerAccounts.get(i);
          Account currentAccount = DatabaseSelectHelper.getAccountDetails(currentAccountId);
          System.out.println("\nAccount id: " + currentAccountId);
          if(currentAccount.getShoppingCart() != null) {
            currentAccount.getShoppingCart().printShoppingCart();
          }
        }
        System.out.println("Please enter the id of the account to restore (enter 0 to cancel):");
        selectedAccountId = UserInput.getValidatedIntInput(br);
        while (selectedAccountId != 0 && !(customerAccounts.contains(selectedAccountId))) {
          System.out.println("Invalid account id. Please try again (enter 0 to cancel):");
          selectedAccountId = UserInput.getValidatedIntInput(br);
        }
      }else {
        selectedAccountId = 0;
      }
    }else {
      System.out.println("No active accounts found.");
      selectedAccountId = -1;
    }
    return selectedAccountId;
  }
  
}
