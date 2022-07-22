package vti.com.filter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringFilter extends Filter<String> {

    private String contains;

    private String notContains;


}
