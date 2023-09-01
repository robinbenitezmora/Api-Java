package med.voll.api.medic;

import med.voll.api.address.DataAddress;

public record DataRegisterMedic(String name, String email, String document, Specialicity specialicity,
        DataAddress address) {
}