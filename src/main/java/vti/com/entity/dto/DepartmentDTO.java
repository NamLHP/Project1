package vti.com.entity.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vti.com.entity.Account;
import vti.com.entity.enumerated.DepartmentType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

  private String name;

  private Integer totalMember;

  private DepartmentType type;

  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private Date createDate;

// private List<Account> accountList;


}
