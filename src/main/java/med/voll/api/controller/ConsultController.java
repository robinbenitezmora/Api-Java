package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consults")
@SecurityRequirement(name = "bearerAuth")
@SuppressWarnings("all")

public class ConsultController {

 @Autowired
 private ConsultService consultService;

 @GetMapping
 public ResponseEntity<Object> findAll() {
  return ResponseEntity.ok(consultService.findAll());
 }

 @GetMapping("/{id}")
 public ResponseEntity<Object> findById(@PathVariable Long id) {
  return ResponseEntity.ok(consultService.findById(id));
 }

 @PostMapping
 public ResponseEntity<Object> save(@RequestBody Consult consult) {
  return ResponseEntity.ok(consultService.save(consult));
 }

 @PutMapping("/{id}")
 public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Consult consult) {
  return ResponseEntity.ok(consultService.update(id, consult));
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteById(@PathVariable Long id) {
  consultService.deleteById(id);
  return ResponseEntity.ok().build();
 }
}
