package med.voll.api.infra;

import javax.swing.text.html.parser.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice

public class ErrorTreatment {
 @ExceptionHandler(EntityNotFoundException.class)
 public ResponseEntity handleError404() {
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
 }
}
