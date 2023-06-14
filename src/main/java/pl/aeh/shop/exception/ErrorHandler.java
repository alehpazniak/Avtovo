package pl.aeh.shop.exception;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(ServiceNoDataException.class)
  public Map<String, String> handleException(ServiceNoDataException e) {
    log.error("Not found: ", e);
    Map<String, String> errors = new HashMap<>();
    errors.put("message", e.getMessage());
    return errors;
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public Map<String, String> handle(ConstraintViolationException e) {
    log.error("Not found: ", e);
    Map<String, String> errors = new HashMap<>();
    errors.put("message", e.getMessage());
    return errors;
  }

}
