package med.voll.api.controller;

import med.voll.api.medic.DataRegisterMedic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medics")
public class MedicController {
 @PostMapping
 public void registerMedic(@RequestBody DataRegisterMedic dataRegisterMedic) {
  System.out.println("Medic registered!");
  System.out.println(dataRegisterMedic);
 }
}