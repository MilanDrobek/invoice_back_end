package cz.itnetwork.service;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.save(entity);
        return personMapper.toDTO(entity);
    }
    @Override
    public PersonDTO updatePerson(long id, PersonDTO personDTO) {
        removePerson(id);
        return addPerson(personDTO);
    }

    @Override
    public void removePerson(long id) {
        PersonEntity person = validatePerson(id);
        person.setHidden(true);
        personRepository.save(person);
    }

    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonById(long id) {
        return personMapper.toDTO(validatePerson(id));
    }
    @Override
    public List<PersonStatisticsDTO> getPersonStatistics() {
        return personRepository.findPersonStatistics();
    }

    // region: Private methods

    public PersonEntity validatePerson(long id) {
        PersonEntity person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));

        if (person.isHidden()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Person with id " + id + " is hidden and cannot be manipulated.");
        }

        return person;
    }
    // endregion
}
