package vti.com.entity.dto;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import vti.com.entity.Account;
import vti.com.entity.enumerated.DepartmentType;

public class DepartmentDTO {

  private String name;

  private Integer total_member;

  private DepartmentType type;

  private Date createDate;

//  private List<Account> accountList;


  public DepartmentDTO() {
  }

//  public List<Account> getAccountList() {
//    return accountList;
//  }
//
//  public void setAccountList(List<Account> accountList) {
//    this.accountList = accountList;
//  }


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

  public DepartmentType getType() {
    return type;
  }

  public void setType(DepartmentType type) {
    this.type = type;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
