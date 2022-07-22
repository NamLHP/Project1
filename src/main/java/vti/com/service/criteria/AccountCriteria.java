package vti.com.service.criteria;

import vti.com.filter.LongFilter;
import vti.com.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCriteria {
    private LongFilter id;

    private StringFilter userName;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter role;

}
