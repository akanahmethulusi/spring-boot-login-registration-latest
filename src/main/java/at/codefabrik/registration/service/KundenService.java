package at.codefabrik.registration.service;

import at.codefabrik.registration.exception.NotFoundIdException;
import at.codefabrik.registration.model.Kunde;
import at.codefabrik.registration.repo.KundenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KundenService {

    private KundenRepository kundeRepository;
    public List<Kunde> showKundenList() {
        return (List<Kunde>) kundeRepository.findAll();
    }



    public void saveKunde(Kunde kunde) {
        kundeRepository.save(kunde);
    }

    public Kunde showEditForm(Integer id) {
        Optional<Kunde> findId = kundeRepository.findById(id);
        if(findId.isPresent()){
            return findId.get();
        }
        throw new NotFoundIdException(String.format("Die Kunde %s wurde nicht gefunden!", id));
    }

    public void deleteKunde(Integer id) {
        Long count = kundeRepository.countById(id);
        if(count == null || count == 0){
            throw new NotFoundIdException(String.format("Die Kunde %s wurde nicht gefunden!"));
        }
        kundeRepository.deleteById(id);
    }
}
