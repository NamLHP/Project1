package vti.com.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Errors {
    private HttpStatus status;
    private String message;
    private String field;




    public Errors field(String field) {
        this.field = field;
        return this;
    }

    public Errors status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public Errors message(String message) {
        this.message = message;
        return this;
    }
}
