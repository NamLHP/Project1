package vti.com.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ExceptionHandlerGlobal(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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


    @ExceptionHandler(BusinessErrorException.class)
    public ResponseEntity<Object> handleNotFoundException(BusinessErrorException ex,
        WebRequest webRequest) {
        Errors error = new Errors()
            .errorCode(ex.getBusinessError().getErrorCode())
            .message(
                Objects.requireNonNull(
                    populateMessage(ex, new Locale(
                        Objects.requireNonNull(webRequest.getHeader("lang"))))).getErrorMessage()
            )
            .status(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private BusinessError populateMessage(ICommonException ex, Locale locale) {
        if (locale == null) {
            locale = new Locale("vi", "VI");
        }
        BusinessError businessError = ex.getBusinessError();
        if (businessError != null) {
            String errorMessage = businessError.getErrorMessage();
            String errorCode = businessError.getErrorCode();
            Object params = businessError.getParams();

            if (errorMessage == null || errorMessage.isEmpty()) {
                businessError.errorMessage(
                    messageSource
                        .getMessage(errorCode, new Object[]{params}, "Has some errors!!", locale));
            }
            return businessError;
        } else {
            return null;
        }
    }
}
