package vti.com.filter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LongFilter extends Filter<Long> {

    private Long greaterThan;

    private Long greaterThanOrEquals;

    private Long lessThan;

    private Long lessThanOrEquals;
}
