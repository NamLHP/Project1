package vti.com.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String field;
    private String message;



    public NotFoundException(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public NotFoundException(String message, String field, String message1) {
        super(message);
        this.field = field;
        this.message = message1;
    }

    public NotFoundException(String message, Throwable cause, String field, String message1) {
        super(message, cause);
        this.field = field;
        this.message = message1;
    }

    public NotFoundException(Throwable cause, String field, String message) {
        super(cause);
        this.field = field;
        this.message = message;
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace, String field, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.field = field;
        this.message = message1;
    }
}
