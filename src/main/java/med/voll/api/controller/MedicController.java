package med.voll.api.controller;

import med.voll.api.domain.address.DataAddress;
import med.voll.api.domain.medic.DataMedicList;
import med.voll.api.domain.medic.DataRegisterMedic;
import med.voll.api.domain.medic.DataResponseMedic;
import med.voll.api.domain.medic.DataUpdateMedic;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.medic.MedicRepository;

import org.apache.coyote.Response;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import javax.xml.crypto.Data;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medics")
public class MedicController {

  @Autowired
  private MedicRepository medicRepository;

  @PostMapping
  public ResponseEntity<DataResponseMedic> registerMedic(@RequestBody @Valid DataRegisterMedic dataRegisterMedic,
      UriComponentsBuilder uriComponentsBuilder) {
    Medic medic = medicRepository.save(new Medic(dataRegisterMedic));
    DataRegisterMedic dataResponseMedic = new DataRegisterMedic(medic.getId(), medic.getName(), medic.getEmail(),
        medic.getPhone(), medic.getSpecialicity().toString(), new DataAddress(medic.getAddress().getStreet(),
            medic.getAddress().getDistrit(), medic.getAddress().getCity(), medic.getAddress().getNumber(),
            medic.getAddress().getComplement()));

    URI url = uriComponentsBuilder.path("/medics/{id}").buildAndExpand(medic.getId()).toUri();
    return ResponseEntity.created(url).body(dataResponseMedic);
  }

  @GetMapping
  public ResponseEntity<Page<DataMedicList>> listMedics(@PageableDefault(size = 2) Pageable pagination) {
    return medicRepository.findAll(pagination).map(DataMedicList::new);
    return ResponseEntity.ok(medicRepository.findByActiveTrue(pagination).map(DataMedicList::new));
  }

  @PutMapping
  @Transactional
  public ResponseEntity updateMedic(@RequestBody @Valid DataUpdateMedic dataUpdateMedic) {
    Medic medic = medicRepository.getReferenceById(dataUpdateMedic.getId());
    medic.updateMedic(dataUpdateMedic);
    return ResponseEntity.ok(new DataResponseMedic(medic.getId(), medic.getName(), medic.getEmail(),
        medic.getPhone(), medic.getSpecialicity().toString(), new DataAddress(medic.getAddress().getStreet(),
            medic.getAddress().getDistrit(), medic.getAddress().getCity(), medic.getAddress().getNumber(),
            medic.getAddress().getCompliment())));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deleteMedic(@PathVariable Long id) {
    Medic medic = medicRepository.getReferenceById(id);
    medic.unactiveMedic();
    return ResponseEntity.ok().build();
  }

  // DELETE in Database
  // public void deleteMedic(Medic medic) {
  // Medic medic = medicRepository.getReferenceById(id);
  // medic.deleteMedic(medic);
  // }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponseMedic> returnDatemedic(@PathVariable Long id) {
    Medic medic = medicRepository.getReferenceById(id);
    var dataMedic = new DataResponseMedic(medic.getId(), medic.getName(), medic.getEmail(), medic.getPhone(),
        medic.getSpecialicity().toString(), new DataAddress(medic.getAddress().getStreet(),
            medic.getAddress().getDistrit(), medic.getAddress().getCity(), medic.getAddress().getNumber(),
            medic.getAddress().getCompliment()));
    return ResponseEntity.ok(dataMedic);
  }
}