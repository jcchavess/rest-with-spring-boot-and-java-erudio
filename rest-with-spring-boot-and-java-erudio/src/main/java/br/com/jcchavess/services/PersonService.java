package br.com.jcchavess.services;

import br.com.jcchavess.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findByID(String id){

        logger.info("Finding one person.");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("João Carlos");
        person.setLastName("Chaves");
        person.setAddress("João Pessoa - Paraíba - Brasil");
        person.setGender("Masculino");
        return person;

    }

    public List<Person> findAll(){

        logger.info("Finding all people.");

        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++){
            Person person  = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person){

        logger.info("Creating a person.");

        return person;
    }

    public Person update(Person person){

        logger.info("Updating a person.");

        return person;
    }

    public void delete(String id){

        logger.info("Deleting a person.");

    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName: " + i);
        person.setLastName("LastName: " + i);
        person.setAddress("João Pessoa - Paraíba - Brasil");
        person.setGender("Masculino");
        return person;
    }
}
