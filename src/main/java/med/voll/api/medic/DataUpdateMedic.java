package med.voll.api.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.DataAddress;

public record DataUpdateMedic(@NotNull Long id, String name, String document, DataAddress address) {

}
