package vti.com.entity.form;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vti.com.entity.enumerated.DepartmentType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentForm {

    private Long id;

    private String name;

    private Integer totalMember;

    private DepartmentType type;

    private Date createDate;
}
