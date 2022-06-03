package group.siip.userapi.user.web;

import group.siip.userapi.user.usecase.RequestPayloadMissingException;
import group.siip.userapi.user.usecase.UserNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ErrorResponse onConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        return new ErrorResponse(ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex), status);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public final ResponseEntity<Object> onUserNotFound(UserNotFoundException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public final ResponseEntity<Object> onDuplicateKeyException(DuplicateKeyException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {RequestPayloadMissingException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public final ResponseEntity<Object> onDuplicateKeyException(RequestPayloadMissingException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex), HttpStatus.NOT_ACCEPTABLE);
    }

}
