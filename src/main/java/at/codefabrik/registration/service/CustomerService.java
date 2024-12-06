package at.codefabrik.registration.service;

import at.codefabrik.registration.exception.NotFoundIdException;
import at.codefabrik.registration.model.Customer;
import at.codefabrik.registration.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public List<Customer> showKundenList() {
        return (List<Customer>) customerRepository.findAll();
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer showEditForm(Integer id) {
        Optional<Customer> findId = customerRepository.findById(id);
        if (findId.isPresent()) {
            return findId.get();
        }
        throw new NotFoundIdException(String.format("Der Kunde %s wurde nicht gefunden!", id));
    }

    public void deleteCustomer(Integer id) {
        Long count = customerRepository.countById(id);
        if(count == null || count == 0){
            throw new NotFoundIdException(String.format("Der Kunde %s wurde nicht gefunden!"));
        }
        customerRepository.deleteById(id);
    }
}
