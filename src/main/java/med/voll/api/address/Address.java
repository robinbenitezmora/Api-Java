package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Address {
 private String street;
 private String number;
 private String compliment;
 private String distrit;
 private String city;

 public Address(DataAddress address) {
  this.street = address.street();
  this.number = address.number();
  this.compliment = address.compliment();
  this.distrit = address.distrit();
  this.city = address.city();
 }
}
