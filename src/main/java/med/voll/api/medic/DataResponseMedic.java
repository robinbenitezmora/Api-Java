package med.voll.api.medic;

import med.voll.api.address.DataAddress;

public record DataResponseMedic(Long id, String name, String email, String phone, String document,
  DataAddress address) {
}
