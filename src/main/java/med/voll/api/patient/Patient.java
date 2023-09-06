package med.voll.api.patient;

import jakarta.annotation.Generated;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patient")
@Table(name = "patients")

public class Patient {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String name;
 private String email;
 private String identityDocument;
 private String phone;

 @Embedded
 private Address address;

 public Patient(DataRegiterPatient data) {
  this.name = data.name();
  this.email = data.email();
  this.phone = data.phone();
  this.identityDocument = data.identityDocument();
  this.address = new Address(data.address());
 }
}
