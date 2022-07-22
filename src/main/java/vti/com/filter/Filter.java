package vti.com.filter;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter<FIELD_TYPE> implements Serializable {

    private FIELD_TYPE equals;

    private FIELD_TYPE notEquals;
}
