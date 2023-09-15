package med.voll.api.domain.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.DataAddress;

public record DataUpdateMedic(@NotNull Long id, String name, String document, DataAddress address) {

}
