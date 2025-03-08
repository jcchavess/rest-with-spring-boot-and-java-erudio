package br.com.jcchavess.services;

import br.com.jcchavess.data.dto.PersonDTO;
import br.com.jcchavess.exception.ResourceNotFoundException;
import br.com.jcchavess.models.Person;
import br.com.jcchavess.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.jcchavess.mapper.ObjectMapper.parseListObjects;
import static br.com.jcchavess.mapper.ObjectMapper.parseObject;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;


    public List<PersonDTO> findAll() {

        logger.info("Finding all people.");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findByID(Long id) {

        logger.info("Finding one person.");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID."));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {

        logger.info("Creating a person.");

        Person entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {

        logger.info("Updating a person.");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public ResponseEntity<?> delete(Long id) {

        logger.info("Deleting a person.");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID."));

        repository.delete(entity);
        return ResponseEntity.noContent().build();
    }

}
