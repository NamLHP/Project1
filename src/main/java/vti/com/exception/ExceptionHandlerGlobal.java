package vti.com.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
        HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        List<Errors> errorsList = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()
        ) {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorsList
                .add(new Errors()
                    .errorCode("error.MethodArgumentNotValidException")
                    .field(field)
                    .status(HttpStatus.BAD_REQUEST)
                    .message(message));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsList);
    }


    protected ResponseEntity<Object> handleMethodNotFoundException(NotFoundException e) {
        Errors error = new Errors()
            .errorCode("error.NotFoundException")
            .field(e.getField())
            .message(e.getMessage())
            .status(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
