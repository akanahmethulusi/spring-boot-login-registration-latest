package at.codefabrik.registration.repo;

import at.codefabrik.registration.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Long countById(Integer id);
}
