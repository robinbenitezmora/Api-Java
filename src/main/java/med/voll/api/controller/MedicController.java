package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address.DataAddress;
import med.voll.api.domain.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medics")
public class MedicController {

  @Autowired
  private MedicRepository medicRepository;

  @PostMapping
  public ResponseEntity<DataResponseMedic> registerMedic(@RequestBody @Valid DataRegisterMedic dataRegisterMedic,
      UriComponentsBuilder uriComponentsBuilder) {
    Medic medic = medicRepository.save(new Medic(dataRegisterMedic));
    DataResponseMedic dataResponseMedic = new DataResponseMedic(medic.getId(), medic.getName(), medic.getEmail(),
        medic.getPhone(), medic.getSpecialicity().toString(), new DataAddress(medic.getAddress().getStreet(),
            medic.getAddress().getDistrit(), medic.getAddress().getCity(), medic.getAddress().getNumber(),
            medic.getAddress().getCompliment()));

    URI uri = uriComponentsBuilder.path("/medics/{id}").buildAndExpand(medic.getId()).toUri();
    return ResponseEntity.created(uri).body(dataResponseMedic);
  }

  @GetMapping
  public ResponseEntity<Page<DataMedicList>> listMedics(@PageableDefault(size = 2) Pageable pagination) {
    return medicRepository.findAll(pagination).map(DataMedicList::new).isEmpty() ? ResponseEntity.noContent().build()
        : ResponseEntity.ok(medicRepository.findAll(pagination).map(DataMedicList::new));
  }

  @PutMapping
  @Transactional
  public ResponseEntity updateMedic(@RequestBody @Valid DataUpdateMedic dataUpdateMedic) {
    Medic medic = medicRepository.getReferenceById(dataUpdateMedic.id());
    medic.updateData(dataUpdateMedic);
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
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponseMedic> returnDatamedic(@PathVariable Long id) {
    Medic medic = medicRepository.getReferenceById(id);
    var dataMedic = new DataResponseMedic(medic.getId(), medic.getName(), medic.getEmail(), medic.getPhone(),
        medic.getSpecialicity().toString(), new DataAddress(medic.getAddress().getStreet(),
            medic.getAddress().getDistrit(), medic.getAddress().getCity(), medic.getAddress().getNumber(),
            medic.getAddress().getCompliment()));
    return ResponseEntity.ok(dataMedic);
  }
}