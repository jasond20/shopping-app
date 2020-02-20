package com.b07.users;

import java.io.Serializable;
import java.sql.SQLException;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.security.PasswordHelpers;

public abstract class User implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = -1044664717857215500L;
  private int id;
  private String name;
  private int age;
  private String address;
  private int roleId;
  private boolean authenticated;
  
  /**
   * get the user's id.
   * @return the id of the user.
   */
  public int getId() {
    return this.id;
  }
  
  /**
   * set the user's id.
   * @param id the id to set.
   */
  public void setId(int id) {
    this.id = id;
  }
  
  /**
   * get the user's name.
   * @return the name of the user.
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * set the user's name.
   * @param name the name to set.
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * get the user's age.
   * @return the age of the user.
   */
  public int getAge() {
    return this.age;
  }
  
  /**
   * set the user's age.
   * @param age the age to set.
   */
  public void setAge(int age) {
    this.age = age;
  }
  
  /**
   * get the user's address.
   * @return the address of the user.
   */
  public String getAddress() {
    return this.address;
  }
  
  /**
   * set the user's address.
   * @param address the address to set.
   */
  public void setAddress(String address) {
    this.address = address;
  }
  
  /**
   * get the user's roleId.
   * @return the roleId of the user.
   */
  public int getRoleId() {
    return this.roleId;
  }
  
  /**
   * set the user's roleId.
   * @param roleId the roleId to set.
   */
  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }
  
  /**
   * validate password input matches password stored in the database. 
   * If matches, set authenticated to true.
   * @param password the password to be checked.
   * @throws SQLException 
   */
  public final void authenticate(String password) throws SQLException {
    this.authenticated = PasswordHelpers.comparePassword(
        DatabaseSelectHelper.getPassword(this.id), password);
  }
  
  /**
   * Returns the authentication status of this user.
   * @return The user's authentication status.
   */
  public boolean getAuthenticated() {
    return this.authenticated;
  }
  
  /**
   * Sets authenticated status of this user.
   * @param authenticated - The authentication state to set.
   */
  protected void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }
}
