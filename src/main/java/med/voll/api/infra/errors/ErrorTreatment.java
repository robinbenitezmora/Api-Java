package med.voll.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorTreatment {

 @ExceptionHandler(EntityNotFoundException.class)
 public ResponseEntity<Void> handleError404() {
  return ResponseEntity.notFound().build();
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<List<DataErrorValidation>> handleError400(MethodArgumentNotValidException exception) {
  var dataErrors = exception.getFieldErrors().stream().map(DataErrorValidation::new).toList();
  return ResponseEntity.badRequest().body(dataErrors);
 }

 private record DataErrorValidation(String field, String error) {
  public DataErrorValidation(FieldError error) {
   this(error.getField(), error.getDefaultMessage());
  }
 }
}
