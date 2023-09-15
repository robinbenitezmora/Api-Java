package med.voll.api.infra;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.text.html.parser.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice

public class ErrorTreatment {
 private static final String List = null;

 @ExceptionHandler(EntityNotFoundException.class)
 public ResponseEntity handleError404() {
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity handleError400(MethodArgumentNotValidException exception) {
  ArrayList<DataErrorValidation> errors = new ArrayList<>();
  exception.getBindingResult().getFieldErrors().forEach(e -> {
   errors.add(new DataErrorValidation(e.getField(), e.getDefaultMessage()));
  });
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
 }

 private record DataErrorValidation(String field, String message) {
  public DataErrorValidation(FieldError error) {
   this(error.getField(), error.getDefaultMessage());
  }
 }
}
