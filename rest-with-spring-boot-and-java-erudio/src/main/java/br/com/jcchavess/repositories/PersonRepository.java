package br.com.jcchavess.repositories;

import br.com.jcchavess.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
