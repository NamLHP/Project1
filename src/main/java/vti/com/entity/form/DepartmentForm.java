package vti.com.entity.form;

import vti.com.entity.Department;

public class DepartmentForm  {
  private String name;

  private Integer total_member;

  public DepartmentForm() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getTotal_member() {
    return total_member;
  }

  public void setTotal_member(Integer total_member) {
    this.total_member = total_member;
  }

}
