package at.codefabrik.registration.repo;

import at.codefabrik.registration.model.Kunde;
import org.springframework.data.repository.CrudRepository;

public interface KundenRepository extends CrudRepository<Kunde, Integer> {
    public Long countById(Integer id);
}
