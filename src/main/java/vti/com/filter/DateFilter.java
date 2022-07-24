package vti.com.filter;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
public class DateFilter extends Filter<Date>  {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date greaterThan;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date greaterThanOrEquals;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date lessThan;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date lessThanOrEquals;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date getGreaterThan() {
        return greaterThan;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date getEquals() {
        return equals;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public void setEquals(Date equals) {
        this.equals = equals;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date getNotEquals() {
        return notEquals;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public void setNotEquals(Date notEquals) {
        this.notEquals = notEquals;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public void setGreaterThan(Date greaterThan) {
        this.greaterThan = greaterThan;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date getGreaterThanOrEquals() {
        return greaterThanOrEquals;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public void setGreaterThanOrEquals(Date greaterThanOrEquals) {
        this.greaterThanOrEquals = greaterThanOrEquals;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date getLessThan() {
        return lessThan;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public void setLessThan(Date lessThan) {
        this.lessThan = lessThan;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date getLessThanOrEquals() {
        return lessThanOrEquals;
    }

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public void setLessThanOrEquals(Date lessThanOrEquals) {
        this.lessThanOrEquals = lessThanOrEquals;
    }
}
