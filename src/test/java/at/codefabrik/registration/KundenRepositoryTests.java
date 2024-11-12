package at.codefabrik.registration;

import at.codefabrik.registration.model.Kunde;
import at.codefabrik.registration.repo.KundenRepository;
import lombok.AllArgsConstructor;
//import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

//@DataJpaTest
@SpringBootTest(classes = SpringBootRegistrationApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
//@AllArgsConstructor
public class KundenRepositoryTests {

    @Autowired
    private KundenRepository kundenRepository;

    @Test
    public void testAddNew(){
        Kunde kunde = new Kunde();
        kunde.setEmail("kiliaan@lebensvoll.at");
        kunde.setPassword("12345");
        kunde.setFirstName("Kilian");
        kunde.setLastName("Oerk");
        //kunde.isEnabled();

        Kunde kundeSaved = kundenRepository.save(kunde);

        Assertions.assertThat(kundeSaved).isNotNull();

        Assertions.assertThat(kundeSaved.getId()).isGreaterThan(0);
    }

    @Test
    public void testListKunden(){
        Iterable<Kunde> listKunden = kundenRepository.findAll();

        Assertions.assertThat(listKunden).hasSizeGreaterThan(0);

        for (Kunde kunde : listKunden) {
            System.out.println(kunde);
        }

    }

    @Test
    public void testUpdate(){

        Integer kundenId = 1;
        Optional<Kunde> byId = kundenRepository.findById(kundenId);

        Kunde kunde = byId.get();
        kunde.setPassword("123456");
        kundenRepository.save(kunde);

        Kunde updatedKunde = kundenRepository.findById(kundenId).get();
        Assertions.assertThat(updatedKunde.getPassword()).isEqualTo("123456");

    }

    @Test
    public void testGet(){
        Integer kundenId = 2;
        Optional<Kunde> optionalKunde = kundenRepository.findById(kundenId);
        Assertions.assertThat(optionalKunde).isPresent();
        System.out.println(optionalKunde.get());
    }

    @Test
    public void testDelete(){
        Integer kundenId=2;
        kundenRepository.deleteById(kundenId);

        Optional<Kunde> optionalKunde = kundenRepository.findById(kundenId);
        Assertions.assertThat(optionalKunde).isNotPresent();
    }

}
