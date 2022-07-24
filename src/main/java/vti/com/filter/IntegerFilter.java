package vti.com.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegerFilter extends Filter<Integer> {

    private Integer greaterThan;

    private Integer greaterThanOrEquals;

    private Integer lessThan;

    private Integer lessThanOrEquals;
}
