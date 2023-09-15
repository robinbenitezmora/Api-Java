package med.voll.api.domain.medic;

import med.voll.api.domain.address.DataAddress;

public record DataResponseMedic(Long id, String name, String email, String phone, String document,
    DataAddress address) {
}
