package med.voll.api.domain.medic;

public record DataMedicList(Long id, String name, String specilicity, String document, String email) {
 public DataMedicList(Medic medic) {
  this(medic.getId(), medic.getName(), medic.getSpecialicity().toString(), medic.getDocument(), medic.getEmail());
 }
}
