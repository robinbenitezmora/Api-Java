package med.voll.api.controller;

import med.voll.api.medic.DataMedicList;
import med.voll.api.medic.DataRegisterMedic;
import med.voll.api.medic.Medic;
import med.voll.api.medic.MedicRepository;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medics")
public class MedicController {

 @Autowired
 private MedicRepository medicRepository;

 @PostMapping
 public void registerMedic(@RequestBody @Valid DataRegisterMedic dataRegisterMedic) {
  medicRepository.save(new Medic(dataRegisterMedic));
 }

 @GetMapping
 public Page<DataMedicList> listMedics(@PageableDefault(size = 2) Pageable pageable) {
  return medicRepository.findAll(pageable).map(DataMedicList::new);
 }

 @PutMapping
 public void updateMedic() {

 }
}