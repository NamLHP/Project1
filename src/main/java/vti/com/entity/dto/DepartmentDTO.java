package vti.com.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;
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

  private LocalDate createDate;

}
