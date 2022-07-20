package vti.com.entity.dto;

import vti.com.entity.Department;
import vti.com.entity.enumerated.Role;

import javax.persistence.*;

public class AccountDTO {

  private Long id;

  private String userName;

  private String firstName;

  private String lastName;

  private Role role;

  private String departmentName;

  public AccountDTO() {
  }

  public AccountDTO(Long id, String userName, String firstName, String lastName,
      Role role, String departmentName) {
    this.id = id;
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.departmentName = departmentName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }
}
