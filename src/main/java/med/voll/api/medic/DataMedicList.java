package med.voll.api.medic;

public record DataMedicList(String name, String specilicity, String document, String email) {
 public DataMedicList(Medic medic) {
  this(medic.getName(), medic.getSpecialicity().toString(), medic.getDocument(), medic.getEmail());
 }
}
