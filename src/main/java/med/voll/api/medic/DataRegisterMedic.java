package med.voll.api.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.address.DataAddress;

public record DataRegisterMedic(
                @NotBlank String name,
                @NotBlank @Email String email,
                @NotBlank @Pattern(regexp = "\\d{11}") String phone,
                @NotBlank @Pattern(regexp = "\\d{4,6}") String document,
                @NotNull Specialicity specialicity,
                @NotNull @Valid DataAddress address) {

}
