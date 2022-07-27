package vti.com.service.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vti.com.filter.LocalDateFilter;
import vti.com.filter.IntegerFilter;
import vti.com.filter.LongFilter;
import vti.com.filter.StringFilter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCriteria {

    private LongFilter id;

    private StringFilter name;

    private IntegerFilter totalMember;

    //    private DepartmentType type;
//
    private LocalDateFilter createDate;
}
