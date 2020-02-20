package com.b07.users;

import com.b07.database.helper.DatabaseSelectHelper;

public class UserFactory {
  
  /**
   * Constructs and returns the User object based on its role.
   * @param userId
   * @param name
   * @param age
   * @param address
   * @param roleName
   * @return the User object based on the specifications given. Null if contains invalid parameters.
   */
  public static User userFactory(int userId, String name, int age, String address, String roleName) {
    if (name == null || address==null || roleName==null) {
      return null;
    }
    if (roleName.equals(Roles.ADMIN.toString())) {
      User admin = new Admin(userId, name, age, address);
      admin.setRoleId(DatabaseSelectHelper.getRoleId(Roles.ADMIN.toString()));
      return admin;
    } else if (roleName.equals(Roles.EMPLOYEE.toString())) {
      User employee = new Employee(userId, name, age, address);
      employee.setRoleId(DatabaseSelectHelper.getRoleId(Roles.EMPLOYEE.toString()));
      return employee;
    } else if (roleName.equals(Roles.CUSTOMER.toString())) {
      User customer = new Customer(userId, name, age, address);
      customer.setRoleId(DatabaseSelectHelper.getRoleId(Roles.CUSTOMER.toString()));
      return customer;
    } else {
      System.out.println("Not a valid role name");
      return null;
    }
  }
  
}
