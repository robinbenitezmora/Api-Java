package med.voll.api.domain.medic;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "medics")
@Entity(name = "Medic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medic {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private String email;
 private String phone;
 private String document;

 private Boolean active;
 @Enumerated(EnumType.STRING)
 private Specialicity specialicity;
 @Embedded
 private Address address;

 public Medic(DataRegisterMedic dataRegisterMedic) {
  this.active = true;
  this.name = dataRegisterMedic.name();
  this.email = dataRegisterMedic.email();
  this.phone = dataRegisterMedic.phone();
  this.document = dataRegisterMedic.document();
  this.specialicity = dataRegisterMedic.specialicity();
  this.address = new Address(dataRegisterMedic.address());
 }

 public void updateData(DataUpdateMedic dataUpdateMedic) {
  if (dataUpdateMedic.name() != null) {
   this.name = dataUpdateMedic.name();
  }
  if (dataUpdateMedic.document() != null) {
   this.document = dataUpdateMedic.document();
  }
  if (dataUpdateMedic.address() != null) {
   this.address = address.updateData(dataUpdateMedic.address());
  }
 }

 public void unactiveMedic() {
  this.active = false;
 }

}