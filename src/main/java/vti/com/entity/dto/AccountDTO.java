package vti.com.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vti.com.entity.Department;
import vti.com.entity.enumerated.Role;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

  private Long id;

  private String userName;

  private String firstName;

  private String lastName;

  private Role role;

  private String departmentName;

  private Integer departmentTotalMember;

}
