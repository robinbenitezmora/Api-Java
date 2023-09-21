package med.voll.api.domain.medic;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRepository extends JpaRepository<Medic, Long> {

 Object findByActiveTrue = null;

 Page<Medic> findByActiveTrue(org.springframework.data.domain.Pageable pagination);

}
