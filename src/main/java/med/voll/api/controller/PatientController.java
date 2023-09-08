package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.DataRegisterPatient;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientRepository;

@RestController
@RequestMapping("/patients")
public class PatientController {

 @Autowired
 private PatientRepository patientRepository;

 @PostMapping
 @Transactional
 public void registerPatient(@RequestBody @Valid DataRegisterPatient data) {
  patientRepository.save(new Patient());
 }

}
