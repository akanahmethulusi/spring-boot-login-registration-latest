package at.codefabrik.registration;

import at.codefabrik.registration.model.Customer;
import at.codefabrik.registration.repo.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SpringBootRegistrationApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testAddNew(){
        Customer customer = new Customer();
        customer.setEmail("kiliaan@lebensvoll.at");
        customer.setPassword("12345");
        customer.setFirstName("Kilian");
        customer.setLastName("Oerk");

        Customer customerSaved = customerRepository.save(customer);

        assertThat(customerSaved).isNotNull();
        assertThat(customerSaved.getId()).isGreaterThan(0);
    }

    @Test
    public void testListKunden(){
        Iterable<Customer> customers = customerRepository.findAll();

        assertThat(customers).hasSizeGreaterThan(0);

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void testUpdate(){
        Optional<Customer> foundCustomer = customerRepository.findById(1);

        Customer customer = foundCustomer.get();
        customer.setPassword("123456");
        customerRepository.save(customer);

        Customer updatedCustomer = customerRepository.findById(1).get();
        assertThat(updatedCustomer.getPassword()).isEqualTo("123456");

    }

    @Test
    public void testGet(){
        Optional<Customer> optionalKunde = customerRepository.findById(2);
        assertThat(optionalKunde).isPresent();
        System.out.println(optionalKunde.get());
    }

    @Test
    public void testDelete(){
        customerRepository.deleteById(2);

        Optional<Customer> optionalKunde = customerRepository.findById(2);
        assertThat(optionalKunde).isNotPresent();
    }
}
