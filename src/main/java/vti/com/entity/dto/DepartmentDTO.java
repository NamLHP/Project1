package vti.com.entity.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import vti.com.entity.enumerated.DepartmentType;

public class DepartmentDTO {
  private Long id;

  private String name;

  private Integer total_member;

  private DepartmentType type;

  private Date createDate;

  public DepartmentDTO(Long id, String name, Integer total_member,
      DepartmentType type, Date createDate) {
    this.id = id;
    this.name = name;
    this.total_member = total_member;
    this.type = type;
    this.createDate = createDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
